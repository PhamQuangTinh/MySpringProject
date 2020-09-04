import { CartItem } from './../models/cart-item';
import { Injectable } from '@angular/core';

const CART_KEY = "cart_key"

@Injectable({
  providedIn: 'root',
})
export class SessionStorageService {
 

  constructor() {}


  public saveCartItems(items : CartItem[]) {
    window.sessionStorage.removeItem(CART_KEY);
    window.sessionStorage.setItem(CART_KEY, JSON.stringify(items));
  }

  public getCartItems(): CartItem[] {
    if(window.sessionStorage.getItem(CART_KEY) === null){
      let cartItems = [] as CartItem[];
      return cartItems;
    }
    return JSON.parse(window.sessionStorage.getItem(CART_KEY));
  }

  public removeCartItems() {
    window.sessionStorage.removeItem(CART_KEY)
  }

}
