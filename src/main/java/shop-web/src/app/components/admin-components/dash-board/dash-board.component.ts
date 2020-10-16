import { Component, OnInit } from '@angular/core';
import { DashBoardService} from './dash-board.service'
declare const $:any;
@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {

  data: [];
  adminChild: String = 'product';
  pageMetadata: any;
  currentPage: number = 1;
  columnChoosing = 1;
  currentPageDisplay = 40;
  isEdit = false;
  user: any = {
    id: 0,
    username: "",
    password: "",
    email:"",
    phone:"",
    lastName:"",
    firstName:"",
    roles:[]
  }
  constructor(
    private service: DashBoardService,
  ) { }

  ngOnInit(): void {
    this.findProductPagination(1,this.currentPageDisplay, this.adminChild);
  }

  productAdmin(){
    this.adminChild = 'product';
    this.currentPage = 1;
    this.goToPageIndex();
  }

  userAdmin(){
    this.adminChild = 'user';
    this.currentPage = 1;
    this.goToPageIndex();  }

  orderAdmin(){
    this.adminChild = 'order';
    this.currentPage = 1;
    this.goToPageIndex();  }


  findProductPagination(page, size, type){
    this.service.findProductPaginationService(page,size,type).subscribe(
      (res:any)=>{
        if(res.success && res.data.body != null){
          this.data = res.data.body.listResponse;
          this.pageMetadata = res.data.body.pageMetadata;
        }
      },err=>{console.log(err)}
    )
    
  }

  counter(i: number) {
    
    return new Array(i);
  }

  goPre(){
    if(!this.isFirstPage()){
      this.currentPage -= 1;
      this.goToPageIndex(); 

    }
    
  }

  goNext(){
    if(!this.isLastPage()){
      this.currentPage += 1;
      this.goToPageIndex(); 
    }
  }
  
  goToPage(page){
    this.currentPage = page;
    this.goToPageIndex(); 
  }

  isCurrentPage(page){
    return page == this.currentPage;
  }

  isFirstPage(){
    return this.currentPage == 1;
  }

  isLastPage(){
    return this.currentPage == this.pageMetadata.totalPages;
  }

  isChoosing(index){
    return index == this.columnChoosing;
  }

  chooseColumn(index){
    this.columnChoosing = index;
  }

  roleUser(roles:[]): string{

    return '1';
  }

  addNewItem(shopObject){
    if(shopObject == null){
      
    }else{

    }
    $('#addNewItem').modal('show')
  }

  goToPageIndex(){
    this.findProductPagination(this.currentPage,this.currentPageDisplay, this.adminChild);
  }

}
