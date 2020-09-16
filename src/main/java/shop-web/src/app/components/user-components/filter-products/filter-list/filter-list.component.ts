import { FilterItemComponent } from './filter-item/filter-item.component';
import { Router } from '@angular/router';
import { MessengerService } from './../../../../services/messenger.service';
import { Observable } from 'rxjs';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FilterListService } from './filter-list.service';

@Component({
  selector: 'app-filter-list',
  templateUrl: './filter-list.component.html',
  styleUrls: ['./filter-list.component.css'],
})
export class FilterListComponent implements OnInit {
  @Input() configPagination: any;
  @Input() eventPagination: Observable<any>;
  @Output() itemsNumber = new EventEmitter();
  listProducts: any = [];
  pageMetaData: any = {
    page: 0,
    size: 0,
    totalElements: 0,
    totalPages: 0,
    totalPerPage: 0,
  };
  constructor(
    private service: FilterListService,
    private msg: MessengerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (
      this.configPagination.keyword === null && 
      this.configPagination.filter === null
    ) {
      if(this.configPagination.userId != null){
        this.findListLikeProduct(
          this.configPagination.currentPage,
          this.configPagination.itemsPerPage,
          this.configPagination.sortBy,
          this.configPagination.userId
        );
      }else{
        alert("You have to login first");
      }
    }
    else if (
      this.configPagination.keyword !== null ||
      this.configPagination.filter !== null
    ) {
      if(this.configPagination.keyword !== null){
        this.findProductsByKeyword(
          this.configPagination.currentPage,
          this.configPagination.itemsPerPage,
          this.configPagination.sortBy,
          this.configPagination.keyword
        );
      }else{
        this.findProductsByFilter(
          this.configPagination.currentPage,
          this.configPagination.itemsPerPage,
          this.configPagination.sortBy,
          this.configPagination.filter.fPrice,
          this.configPagination.filter.lPrice,
          this.configPagination.filter.color,
          this.configPagination.filter.sexTypes
        );
      }
      
    }
    
    
  }

  findListLikeProduct(page, size, sortBy, userId) {
    this.service.findProductsByListLike(page, size, sortBy, userId).subscribe(
      (res) => {
        if (res.success && res.data.body != null) {
          this.listProducts = res.data.body.listResponse;
          this.pageMetaData = res.data.body.pageMetadata;
          this.itemsNumber.emit(this.pageMetaData);
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  findProductsByKeyword(page, size, sortBy, keyword) {
    this.service.findProductsByKeyword(page, size, sortBy, keyword).subscribe(
      (res) => {
        if (res.success && res.data.body != null) {
          this.listProducts = res.data.body.listResponse;
          this.pageMetaData = res.data.body.pageMetadata;
          this.itemsNumber.emit(this.pageMetaData);
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  findProductsByFilter(page, size, sortBy, fPrice, lPrice, color, sexType) {
    this.service
      .findProductsFilter(page, size, sortBy, fPrice, lPrice, color, sexType)
      .subscribe(
        (res) => {
          if (res.success && res.data.body != null) {
            this.listProducts = res.data.body.listResponse;
            this.pageMetaData = res.data.body.pageMetadata;
            this.itemsNumber.emit(this.pageMetaData);
          }
        },
        (err) => {
          console.log(err);
        }
      );
  }

  getImageProduct(product): any {
    return product.productImagesEntities[1].imageLink;
  }
}
