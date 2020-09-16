import { productsUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CartItem } from './../../../models/cart-item';
import { SessionStorageService } from '../../../services/session-storage.service'


@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  constructor(
    private http: HttpClient,
    private sessionStorage: SessionStorageService
  ) { }

  addProductToCartService(cart: CartItem): CartItem[]{
    let cartItems = this.sessionStorage.getCartItems();
    if(cartItems === null){
        cartItems.push(cart);
    
    }else{
        let productExists = false
        for(let i in cartItems){
            if(cartItems[i].productId ===  cart.productId && cartItems[i].colorId === cart.colorId && cartItems[i].sizeId === cart.sizeId){
                cartItems[i].qty += cart.qty;
                productExists = true;
                break;
            }
        } 

        if(!productExists){
            cartItems.push(cart)
        }
    }
    this.sessionStorage.saveCartItems(cartItems)
    return this.sessionStorage.getCartItems();
  }

  removeProductFromCart(proId: number, colorId: number, sizeId: number): CartItem[]{
    let cartItemsRes = [] as CartItem[];
    let cartItems = this.sessionStorage.getCartItems();
    cartItems.forEach(cart => {
      
      if(!(cart.productId != proId) && !(cart.colorId != colorId) && !(cart.sizeId != sizeId)){
        
      }else {
        cartItemsRes.push(cart);
      }
    })
    this.sessionStorage.saveCartItems(cartItemsRes);
    return this.sessionStorage.getCartItems();
  }

  findProductByKeyword(keyword): Observable<any>{
    const params = new HttpParams()
                    .set('keyword', keyword)
    return this.http.get(productsUrl + "/get/product_name_or_description" , {params});
  }

}
