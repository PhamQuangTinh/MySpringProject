import { CheckOut } from './../../../../../models/check-out';
import { userUrl, sizeUrl, availableProductUrl } from './../../../../../config/api';
import { CartItem } from './../../../../../models/cart-item';
import { SessionStorageService } from './../../../../../services/session-storage.service';
import { Observable } from 'rxjs';
import { HttpClient,HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ProductItemService {


  constructor(
      private http: HttpClient,
      private sessionStorage : SessionStorageService
      ) { 

  }

  likeProductSerVice(userId, proId): Observable<any>{
    return this.http.post(userUrl + '/post/likination',{
      'proId': proId,
      'userId': userId
    });
  }

  unLikeProductSerVice(userId: any, proId: any) {
    return this.http.post(userUrl + '/post/unlikination',{
      'proId': proId,
      'userId': userId
    });
  }

  getSizeOfProduct(proId: any): Observable<any>{
    const params = new HttpParams().set('pro_id', proId);

    return this.http.get(sizeUrl + "/get/pro_id",{params})
  }

  checkCartItem(item: CheckOut): Observable<any>{

    var x = 
    {
      colorLink: item.colorLink,
      proId: item.proId,
      sizeId: item.sizeId,
      unitInOrder: item.unitInOrder
    }
    return this.http.post(availableProductUrl + "/post/cart_checking", x);
  }
}
