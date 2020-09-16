import { CartItem } from './../models/cart-item';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  subject = new Subject();

  subjectSearching = new Subject();

  subjectPage = new Subject();

  constructor() { }

  sendMsg(cart: CartItem) {
    this.subject.next(cart) //Triggering an event
  }


  getMsg() {
    return this.subject.asObservable();
  }

  sendMsgNumber(productId : number){
    this.subject.next(productId);
  }
  getMsgNumber(){
    return this.subject.asObservable();
  }

  sendMsgSearching(keyword){
    this.subjectSearching.next(keyword);
  }

  getMsgSearching(){
    return this.subjectSearching.asObservable();
  }


  sendMsgPage(page: number){
    this.subjectPage.next(page);
  }

  getMsgPage(){
    return this.subjectPage.asObservable();
  }
}
