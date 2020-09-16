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
export class HeaderComponent implements OnInit, OnDestroy {
  lastName = '';
  isLoggedIn = false;
  userId: any;

  keyword: any ='';

  value = '';
  cartItems = [];

  ListProductsSearching = [];
  cartTotal = 0;

  subscription: Subscription;


  constructor(
    private tokenService: TokenStorageService,
    private sessionStorage: SessionStorageService,
    private msg: MessengerService,
    private headerService: HeaderService,
    private router: Router,
    private session: SessionStorageService
  ) {
  }

  ngOnInit(): void {
    try{
      this.userId = this.tokenService.getUser().id;
    }catch{
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
    
    this.cartItems = this.sessionStorage.getCartItems();
    if (this.cartItems.length !== 0) {
      this.calcCartTotal();
    }
    
  }



  signOut() {
    this.tokenService.removeUser();
    this.tokenService.removeToken();
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

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  viewProduct(product){
    $('.searchProductUl').slideUp('fast');
    this.router.navigate(['/products', product.id]).then(()=>{window.location.reload()});  
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
}
