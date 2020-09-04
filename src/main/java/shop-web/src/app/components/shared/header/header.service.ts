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

  addProductToCartService(product: any): CartItem[]{
    let cartItems = this.sessionStorage.getCartItems();
    if(cartItems === null){
        cartItems.push(new CartItem(product.id, product.productName, product.productAvatarEntities[1].imageLink, 1, product.unitPrice));
    
    }else{
        let productExists = false
        for(let i in cartItems){
            if(cartItems[i].productId ===  product.id){
                cartItems[i].qty++;
                productExists = true;
                break;
            }
        } 

        if(!productExists){
            cartItems.push(new CartItem(product.id, product.productName,
                product.productAvatarEntities[1].imageLink, 1, product.unitPrice))
        }
    }
    this.sessionStorage.saveCartItems(cartItems)

    return this.sessionStorage.getCartItems();
  }

  removeProductFromCart(proId: number): CartItem[]{
    let cartItemsRes = [] as CartItem[];
    let cartItems = this.sessionStorage.getCartItems();
    cartItems.forEach(pro => {
      if(pro.productId !== proId){
        cartItemsRes.push(pro);
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
