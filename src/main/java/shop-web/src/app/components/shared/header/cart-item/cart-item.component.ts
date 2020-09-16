import { RemoveCartItem } from './../../../../models/reomve-cart-item';
import { CartItem } from './../../../../models/cart-item';
import { MessengerService } from './../../../../services/messenger.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  @Input() cartItem: CartItem;
  @Output() deleteEvent = new EventEmitter();

  constructor(
    private msg: MessengerService
  ) { }

  ngOnInit(): void {
    
  }

  removeCartItem(){
    this.deleteEvent.emit(new RemoveCartItem(this.cartItem.productId, this.cartItem.colorId, this.cartItem.sizeId));  
  }

}
