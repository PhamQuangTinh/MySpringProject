import { RemoveCartItem } from './../../../models/reomve-cart-item';
import { SessionStorageService } from './../../../services/session-storage.service';
import { CartItem } from './../../../models/cart-item';
import { MessengerService } from './../../../services/messenger.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { TokenStorageService } from '../../../services/token-storage.service';
import { HeaderService } from './header.service';
import { Subscription, Subject } from 'rxjs';


declare const $: any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit{
  lastName = '';
  isLoggedIn = false;
  userId: any;

  keyword: any ='';

  sexType: string = 'home';
  value = '';
  cartItems = [];

  ListProductsSearching = [];
  cartTotal = 0;

  subscription: Subscription;

  hasAdminRole:boolean = false;

  category: string = 'All Categories';


  constructor(
    private tokenService: TokenStorageService,
    private msg: MessengerService,
    private headerService: HeaderService,
    private router: Router,
    private session: SessionStorageService
  ) {
  }
  ngOnInit(): void {
    if(this.session.getCategory() != null){
      this.category = this.session.getCategory();
    }else{
      this.category = 'All Categories';
    }

    if(this.session.getSexType() != null){
      this.sexType = this.session.getSexType();
    }else{
      this.sexType = 'home';
    }

    try{
      var user = this.tokenService.getUser();
      this.userId = user.id;
      for(var i = 0; i < user.authorities.length; i++){
        if(user.authorities[i].authority == "SUPER_ADMIN"){
            this.hasAdminRole = true;
            break;
        }
    }
    }catch{
      this.hasAdminRole = false;
      this.userId = null;
    }

    
    $('.mobile-menu').slicknav({
      prependTo: '#mobile-menu-wrap',
      allowParentLinks: true,
    });

    $(document).ready(function () {
      // Show hide popover
      $('.inputMe').keyup(function () {
        $('.searchProductUl').slideDown('fast');
      });
    });
    $(document).on('click', function (event) {
      var $trigger = $('.menuResult');
      if ($trigger !== event.target && !$trigger.has(event.target).length) {
        $('.searchProductUl').slideUp('fast');
      }
    });

    if (this.tokenService.getToken() != null) {
      this.isLoggedIn = true;
      this.lastName = this.tokenService.getUser().lastName;
    }

    this.subscription = this.msg.getMsg().subscribe((cartItem: CartItem) => {
      this.cartItems = this.headerService.addProductToCartService(cartItem);
      this.calcCartTotal();
    });
    
    this.cartItems = this.session.getCartItems();
    if (this.cartItems.length !== 0) {
      this.calcCartTotal();
    }
    
  }

  signOut() {
    this.tokenService.removeUser();
    this.tokenService.removeToken();
    this.router.navigateByUrl('/home').then(()=>{window.location.reload()});
  }

  goToTransactionHistory(){
    this.router.navigate(['/transaction', this.tokenService.getUser().id]);
  }

  //calc products total
  calcCartTotal() {
    this.cartTotal = 0;
    this.cartItems.forEach((item) => {
      this.cartTotal += item.qty * item.price;
    });
  }

  //Delete item from cart item
  deleteItem(cart: RemoveCartItem) {
    this.cartItems = this.headerService.removeProductFromCart(cart.proId, cart.colorId, cart.sizeId);
    if (this.cartItems.length !== 0) {
      this.calcCartTotal();
    }
  }

  search(event: any) {
    if (event.target.value !== '') {
      this.headerService
        .findProductByKeyword(event.target.value)
        .subscribe((res) => {
          if (res.data.body !== null) {
            this.ListProductsSearching = res.data.body;
          } else {
            this.ListProductsSearching = [];
          }
        });
    }
  }

  trackByFn(index, item) {
    return index;
  }


  viewProduct(product){
    $('.searchProductUl').slideUp('fast');
    let st = ''
    if(product.sexType == 'WOMAN'){
      st = 'women'
    }else if(product.sexType == 'MAN'){
      st = 'man'
    }else if(product.sexType == 'Girls'){
      st = 'girl'
    }else if(product.sexType == 'Boys'){
      st = 'boy'
    }
    this.router.navigate(['/products/product-detail',{id: product.id, type: st}]).then(()=>{window.location.reload()});  
  }

  getLikeList(){
    if(this.router.url === '/filter'){
      this.router.navigateByUrl('/home').then(()=>this.router.navigateByUrl('/filter'));
    }else{
      this.router.navigateByUrl('/filter');
    }
  }

  getProductsSearching(){
    // this.msg.sendMsgSearching(this.keyword);
    if(this.router.url === '/filter'){
      this.router.navigateByUrl('/home').then(()=>this.router.navigateByUrl('/filter')).then(()=>this.session.saveKeyword(this.keyword))
    }else{
      this.router.navigateByUrl('/filter').then(()=>this.session.saveKeyword(this.keyword));   
    }
  }

  changeCategory(cate){
    return this.category == cate;
  }

  chooseCategory(cate){
    var sType = this.router.url.replace("/products/","");
    if(sType != '/home'){
      this.router.navigateByUrl('/home').then(()=>this.router.navigateByUrl('/products/' + sType));
    }
    this.category = cate;
    this.session.saveCategory(cate);
  }




}
