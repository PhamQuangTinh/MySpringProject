import { HeaderService } from './../../shared/header/header.service';
import { CheckOut } from './../../../models/check-out';
import { SessionStorageService } from './../../../services/session-storage.service';
import { CartItem } from './../../../models/cart-item';
import { Component, OnInit, AfterViewInit } from '@angular/core';

declare const $: any;

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
})
export class ShoppingCartComponent implements OnInit, AfterViewInit {
  constructor(
    private sessionStorage: SessionStorageService,
    private headerService: HeaderService
    ) {}

  cartItems: CartItem[];
  subTotal: any;

  ngOnInit(): void {

    this.cartItems = this.sessionStorage.getCartItems()
    this.subtotalMethod();
  }
  ngAfterViewInit(){
    // var proQty = $('.pro-qty');
    // proQty.prepend('<span class="dec qtybtn">-</span>');
    // proQty.append('<span class="inc qtybtn">+</span>');
    // proQty.on('click', '.qtybtn', function () {
    //   var $button = $(this);
    //   var oldValue = $button.parent().find('input').val();
    //   if ($button.hasClass('inc')) {
    //     var newVal = parseFloat(oldValue) + 1;
    //   } else {
    //     // Don't allow decrementing below zero
    //     if (oldValue > 0) {
    //       var newVal = parseFloat(oldValue) - 1;
    //       this.cartItem
    //     } else {
    //       newVal = 0;
    //     }
    //   }
    //   $button.parent().find('input').val(newVal);
    // });
  }

  subtotalMethod(){
    let res = 0;
    let cItems = this.cartItems
    for(var i = 0; i < cItems.length; i++){
      let count = cItems[i].price * cItems[i].qty
      res += count;
    } 
    this.subTotal = res
  }
  addQTY(item: CartItem){
    this.cartItems.filter(x =>x.productId === item.productId)
                  .forEach( x=>{
                    x.qty++;
                  });
    this.subtotalMethod();
                  
  }

  removeQTY(item: CartItem){
    this.cartItems.filter(x =>x.productId === item.productId)
                  .forEach( x=>{
                    if(x.qty > 1){
                      x.qty--;
                    }
                  });
    this.subtotalMethod();
  }

  removeCartItem(cart: CartItem){
    this.cartItems = this.headerService.removeProductFromCart(cart.productId, cart.colorId, cart.sizeId);
    if (this.cartItems.length !== 0) {
      this.subtotalMethod();
    }
  }


  enterCode(code){
    console.log(code);
  }
}
