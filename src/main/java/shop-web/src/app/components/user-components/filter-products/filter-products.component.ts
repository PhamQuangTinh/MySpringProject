import { SessionStorageService } from './../../../services/session-storage.service';
import { Router } from '@angular/router';
import { TokenStorageService } from './../../../services/token-storage.service';
import { MessengerService } from './../../../services/messenger.service';
import { Subject } from 'rxjs';
import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-filter-products',
  templateUrl: './filter-products.component.html',
  styleUrls: ['./filter-products.component.css']
})
export class FilterProductsComponent implements OnInit, AfterViewInit {

  pageSubject: Subject<any> = new Subject<any>();

  public maxSize: number = 20;
  public directionLinks: boolean = true;
  public autoHide: boolean = false;
  public responsive: boolean = true;
  public labels: any = {
    previousLabel: '<--',
    nextLabel: '-->',
    screenReaderPaginationLabel: 'Pagination',
    screenReaderPageLabel: 'page',
    screenReaderCurrentLabel: `You're on page`,
  };
  config = {
    id: 'filter',
    itemsPerPage: 24,
    currentPage: 1,
    totalItems: 0,
    numberOfElements:0,
    sortBy: 'productName low',
    userId: null,
    keyword: null,
    filter: null
  };
  pageSize: number;

  constructor(
    private msg: MessengerService,
    private token: TokenStorageService,
    private router: Router,
    private session: SessionStorageService
  ) { }

  ngOnInit(): void {
    try{
      this.config.userId = this.token.getUser().id;
    }catch{
      this.config.userId = null;
    }
    this.config.keyword = this.session.getKeyword();
    this.config.filter = this.session.getFilter();
  }
  

  getPageMetadata(pageMetadata) {
    this.config.currentPage = pageMetadata.page;
    this.config.itemsPerPage = pageMetadata.size;
    this.config.numberOfElements = pageMetadata.numberOfElements;
    this.config.totalItems = pageMetadata.totalElements;
  }

  onPageChange(event) {
    this.config.currentPage = event;
    this.pageSubject.next(event);
    window.scrollTo(0, 0)
  }

  changeItemPerPage(event){
    this.config.itemsPerPage = event.value;
    this.onPageChange(1);
  }

  currentItem(): any{
    var firstIndex = 0;
    if(this.config.numberOfElements > 0){
      firstIndex = (this.config.currentPage - 1) * this.config.itemsPerPage + 1;
    }
    var lastIndex = 0;
    if(firstIndex > 0){
      lastIndex = firstIndex + this.config.numberOfElements - 1;
    }
    var res = firstIndex + ' - ' + lastIndex;
    return res;
  }


  ngAfterViewInit(): void{
    this.session.removeKeyword();
    this.session.removeFilter();
  }

}
