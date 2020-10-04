import { ShopService } from './shop.service';
import { Router } from '@angular/router';
import { MessengerService } from './../../../services/messenger.service';
import { Subject, Observable, Subscription } from 'rxjs';
import { Component, OnInit, AfterViewInit } from '@angular/core';

declare const $: any;
interface Select {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
})

export class ShopComponent implements OnInit, AfterViewInit {
  //start pagination
  subscription: Subscription;
  selected:string = '';
  oksize: string = '12';
  currentRouter: string;
  selectedOption: string;
  userId: any;
  itemsPerPage: Select[] = [
    {value: '12', viewValue: '12'},
    {value: '18', viewValue: '18'},
    {value: '24', viewValue: '24'},
    {value: '30', viewValue: '30'}
  ];


  sortBy: Select[] = [
    {value: 'productName low', viewValue: 'Sort By Name'},
    {value: 'unitPrice high', viewValue: 'Sort By High Price'},
    {value: 'unitPrice low', viewValue: 'Sort By Low Price'},

  ];

  pageSubject: Subject<any> = new Subject<any>();
  config = {
    id: 'custom',
    itemsPerPage: 12,
    currentPage: 1,
    totalItems: 0,
    numberOfElements:0,
    sortBy: 'productName low',
    code: 1,
    filter: null,
    sType: '',
  };
  pageSize: number;
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
  //end pagination
  constructor(
    private msg: MessengerService,
    private router: Router,
    private shopService: ShopService
    ) {
      this.currentRouter = this.config.sType = this.router.url.replace("/products/","");
    }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.msg.getMsgPage().subscribe(
      x=>{
        this.onPageChange(x);
      });

  }

  getPageMetadata(pageMetadata) {
    this.config.currentPage = pageMetadata.page;
    this.config.itemsPerPage = pageMetadata.size;
    this.config.numberOfElements = pageMetadata.numberOfElements;
    this.config.totalItems = pageMetadata.totalElements;
  }

  onPageChange(event) {
    this.config.currentPage = event;
    this.pageSubject.next();
    window.scrollTo(0, 0)
  }

  changeItemPerPage(event){
    this.config.itemsPerPage = event.value;
    this.onPageChange(1);
  }

  changeSort(event){
    this.config.sortBy = event.value;
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

}
