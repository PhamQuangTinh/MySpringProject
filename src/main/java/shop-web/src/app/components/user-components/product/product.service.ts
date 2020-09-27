import { userUrl, productCommentUrl, availableProductUrl } from './../../../config/api';
import { Observable, throwError } from 'rxjs';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { productsUrl, productColorUrl } from 'src/app/config/api';
import { catchError } from 'rxjs/operators';
import { CheckOut } from 'src/app/models/check-out';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getProductInfo(proId, userId): Observable<any> {
    const params = new HttpParams()
      .set('pro_id', proId)
      .set('user_id', userId);
    return this.http.get(productsUrl + '/get/product_info',{params});
  }

  getColorProductImages(proId, colorId) {
    const params = new HttpParams()
      .set('pro_id', proId)
      .set('color_id', colorId);
    return this.http.get(productColorUrl + '/get/proId_and_color_id', {
      params,
    });
  }

  getCommentsProduct(proId, page){
    const params = new HttpParams()
      .set('page', page)
      .set('proId', proId)
    return this.http.get(productCommentUrl + '/get/all-comment',{params});
  }

  commentProduct(userId, proId, contentProduct): Observable<any> {
    return this.http.post(productCommentUrl + '/post/comment-creation', 
    {
      content: contentProduct,
      productId: proId,
      userId: userId
    });
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

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.error.body.message || 'Server Error');
  }
}
