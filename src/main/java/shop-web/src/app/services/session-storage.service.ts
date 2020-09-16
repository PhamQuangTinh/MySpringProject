import { FilterObject } from './../models/filter-object';
import { CartItem } from './../models/cart-item';
import { Injectable } from '@angular/core';
import { keyframes } from '@angular/animations';
import { JsonPipe } from '@angular/common';

const CART_KEY = "cart_key"
const KEYWORD = "keyword"
const FILTER = "filter";

@Injectable({
  providedIn: 'root',
})
export class SessionStorageService {
 

  constructor() {}

  public saveKeyword(keyword){
    window.sessionStorage.removeItem(KEYWORD);
    window.sessionStorage.setItem(KEYWORD, keyword);
  }

  public getKeyword(): string{
    return window.sessionStorage.getItem(KEYWORD);
  }

  public removeKeyword(){
    window.sessionStorage.removeItem(KEYWORD);
  }

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


  public saveFilter(filter: FilterObject){
    window.sessionStorage.removeItem(FILTER);
    window.sessionStorage.setItem(FILTER, JSON.stringify(filter));
  }

  public getFilter(): FilterObject{
    return JSON.parse(window.sessionStorage.getItem(FILTER));
  }

  public removeFilter(){
    window.sessionStorage.removeItem(FILTER);
  }

}
