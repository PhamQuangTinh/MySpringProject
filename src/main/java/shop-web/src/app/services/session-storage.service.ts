import { FilterObject } from './../models/filter-object';
import { CartItem } from './../models/cart-item';
import { Injectable } from '@angular/core';
import { keyframes } from '@angular/animations';
import { JsonPipe } from '@angular/common';

const CART_KEY = "cart_key"
const KEYWORD = "keyword"
const FILTER = "filter";
const ORDER_ID = "orderId";
const CATE_KEY = 'cate';
const SEX_TYPE = 's-type';
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

  public saveOrderId(orderId: number){
    this.removeOrderId();
    window.sessionStorage.setItem(ORDER_ID, orderId.toString());
  }
  public removeOrderId(){
    window.sessionStorage.removeItem(ORDER_ID);
  }
  public getOrderId(): string{
    return window.sessionStorage.getItem(ORDER_ID);
  }

  public saveCategory(cate: string) {
    this.removeCategory();
    window.localStorage.setItem(CATE_KEY, cate);
  }

  public getCategory(): any {
    return localStorage.getItem(CATE_KEY);
  }

  public removeCategory(){
    window.localStorage.removeItem(CATE_KEY);
  }

  public saveSexType(type: string) {
    this.removeSexType();
    window.localStorage.setItem(SEX_TYPE, type);
  }

  public getSexType(): any {
    return localStorage.getItem(SEX_TYPE);
  }

  public removeSexType(){
    window.localStorage.removeItem(SEX_TYPE);
  }
}
