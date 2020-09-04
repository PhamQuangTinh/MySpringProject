import { Observable, Subscription } from 'rxjs';
import { MessengerService } from './../../../../services/messenger.service';
import { TokenStorageService } from './../../../../services/token-storage.service';
import { ProductListService } from './product-list.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';



@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  // private eventsSubscription: Subscription;
  @Input() configPagination: any;
  @Input() eventPagination: Observable<any>;
  @Output() itemsNumber = new EventEmitter();
  listProducts: any = [];

  pageMetaData: any = {
    page: 0,
    size: 0,
    totalElements: 0,
    totalPages: 0
  };

  constructor(
    private service: ProductListService,
    private token: TokenStorageService,
    private msg: MessengerService,

  ) { }

  ngOnInit(): void {
    this.findAllProductsPagination(this.configPagination.currentPage,this.configPagination.itemsPerPage);
    this.eventPagination.subscribe(
      page=>{
        this.findAllProductsPagination(page,12);
      });

  }

  // find all products to show
  // tslint:disable-next-line:typedef no-shadowed-variable
  findAllProductsPagination(page,size){
    this.service.findAllProductsPaginationService(page,size).subscribe(
      res => {
        if (res.success && res.data.body != null){
          this.listProducts = res.data.body.listResponse;
          this.pageMetaData = res.data.body.pageMetadata;
          this.itemsNumber.emit(this.pageMetaData);
        }
      },
      err => {
        console.log(err);
      }
    );
  }

  getImageProduct(product): any{
    return product.productImagesEntities[1].imageLink;
  }

}
