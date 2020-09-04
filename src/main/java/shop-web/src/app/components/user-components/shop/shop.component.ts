import { MessengerService } from './../../../services/messenger.service';
import { Subject, Observable } from 'rxjs';
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
  selected:string = '';
  oksize: string = '12';
  selectedOption: string;
  itemsPerPage: Select[] = [
    {value: '12', viewValue: '12'},
    {value: '18', viewValue: '18'},
    {value: '24', viewValue: '24'},
    {value: '30', viewValue: '30'}
  ];


  sortBy: Select[] = [
    {value: 'productName', viewValue: 'Sort By Name'},
    {value: 'unitPrice', viewValue: 'Sort By Price'},
  ];

  pageSubject: Subject<any> = new Subject<any>();
  config = {
    id: 'custom',
    itemsPerPage: 12,
    currentPage: 1,
    totalItems: 0,
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
  constructor(private msg: MessengerService) {}

  ngOnInit(): void {

  }

  selectOption(id: number) {
    //getted from event
    console.log(id);
    //getted from binding
    console.log(this.selected)
  }
  ngAfterViewInit() {
    $('.sorting, .p-show').niceSelect();
    console.log("css work")
    var x = $('p-show').val();
    console.log(x);
  }

  getPageMetadata(pageMetadata) {
    this.config.currentPage = pageMetadata.page;
    this.config.itemsPerPage = pageMetadata.size;
    this.config.totalItems = pageMetadata.totalElements;
  }

  onPageChange(event) {
    this.config.currentPage = event;
    this.pageSubject.next(event);
    window.scrollTo(0, 0)
  }

  onChange(ok: any) {
    console.log('work');
    console.log(ok);
  }
}