import { Subject } from 'rxjs';
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
  typeOfItem: String;
  listRole: any;

  itemData: any;

  deleteDataId: any;
  user: any = {
    id: 0,
    username: "",
    password: "",
    email:"",
    phone:"",
    lastName:"",
    firstName:"",
    roleString:""
  }

  product: any = {
    id: 0,

    productName: "",

    unitPrice: 0,

    description: "",

    discount: 0,

    sexType: "",
  }

  productCopy: any = {
    id: 0,
    
    productName: "",

    unitPrice: "",

    description: "",

    discount: "",

    sexType: "",
  }

  userCopy: any = {
    id: 0,
    username: "",
    password: "",
    email:"",
    phone:"",
    lastName:"",
    firstName:"",
    roleString:""
  }
  item = "2340";
  constructor(
    private service: DashBoardService,
  ) {
  }

  ngOnInit(): void {
    this.findProductPagination(1,this.currentPageDisplay, this.adminChild);
  }

  editItem(isEdit, type, data){
    this.isEdit = isEdit;
    this.typeOfItem = type;
    if(this.typeOfItem == 'user'){
      if(data != null){
        this.user = data;
      }else{
        this.user = this.userCopy;
        this.service.findAllRole().subscribe(
          res =>{
            if(res.success && res.data.body != null){
              this.listRole = res.data.body;
            }
          },
          err=>{console.log(err)}
        )
      }
    }else if(this.typeOfItem == 'product'){
      if(data != null){
        this.product = data;
      }else{
        this.product = this.productCopy;
      }
    }


    $("#editItem").modal('show');
  }

  deleteItem(dataId){
    this.deleteDataId = dataId;
    $("#deleteConfirm").modal('show');
  }

  deleteItemFinal(){
    if(this.adminChild == 'user'){
      this.service.deleteUser(this.deleteDataId).subscribe(
        res=>{
          $('#deleteConfirm').modal('hide');
          this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
        },err=>{
          $('#deleteConfirm').modal('hide');
          this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
        }
      );

    }else if(this.adminChild == 'product'){
      this.service.deleteProduct(this.deleteDataId).subscribe(
        res=>{
          $('#deleteConfirm').modal('hide');
          this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
        },err=>{
          alert("err");
          $('#deleteConfirm').modal('hide');
          this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
        }
      );
    } else if(this.adminChild == 'order'){
      this.service.deleteOrder(this.deleteDataId).subscribe(
        res=>{
          $('#deleteConfirm').modal('hide');
          this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
        },err=>{
          alert("err");
          $('#deleteConfirm').modal('hide');
          this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
        }
      )
    }
  }

  editComfirm(){
    //Kiểm tra điều kiện
    $("#editComfirm").modal('show');
  }

  editItemFinal(){
    if(this.isEdit){
      if(this.adminChild == 'user'){
        this.service.updateUser(this.user).subscribe(
          res=>{
            if(res.success && res.data.body != null){
              alert("Update success");
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
              this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
            }else{
              alert("Update failed")
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
            }
          },err=>{
            $("#editComfirm").modal('hide');
            $("#editItem").modal('hide');
          }
        )
      }else if(this.adminChild == 'product'){
        this.service.updateProduct(this.product).subscribe(
          res=>{
            if(res.success && res.data.body != null){
              alert("Update success");
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
              this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
            }else{
              alert("Update failed")
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
            }
          },err=>{
            $("#editComfirm").modal('hide');
            $("#editItem").modal('hide');
          }
        )
      }
    }
    else{
      if(this.adminChild == 'user'){
        this.service.createUser(this.user).subscribe(
          res=>{
            if(res.success && res.data.body != null){
              alert("Create success");
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
              this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
            }else{
              alert("Create failed")
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
            }
          },err=>{
            alert("Error");
            $("#editComfirm").modal('hide');
            $("#editItem").modal('hide');
          }
        )
      }else if(this.adminChild == 'product'){
        this.service.createProduct(this.product).subscribe(
          res=>{
            if(res.success && res.data.body != null){
              alert("Create success");
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
              this.findProductPagination(1,this.currentPageDisplay, this.adminChild)
            }else{
              alert("Create failed")
              $("#editComfirm").modal('hide');
              $("#editItem").modal('hide');
            }
          },err=>{
            alert("Error");
            $("#editComfirm").modal('hide');
            $("#editItem").modal('hide');
          }
        )
      }
    }

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
    this.goToPageIndex();  
  }


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

  addNewItem(edit,shopObject){
    this.isEdit = edit;
    if(shopObject == null){
    }else{

    }
    $('#addNewItem').modal('show')
  }

  goToPageIndex(){
    this.findProductPagination(this.currentPage,this.currentPageDisplay, this.adminChild);
  }


}
