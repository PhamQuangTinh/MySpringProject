import { userUrl, productCommentUrl } from './../../../config/api';
import { Observable, throwError } from 'rxjs';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { productsUrl, productColorUrl } from 'src/app/config/api';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getProductInfo(id: number): Observable<any> {
    return this.http.get(productsUrl + '/get/product_info/' + id);
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
    return this.http.get(productCommentUrl + '/get/product_comment/'+ proId,{params});
  }

  commentProduct(userId, proId, contentProduct): Observable<any> {
    console.log(typeof(userId), typeof(proId), typeof(contentProduct));
    return this.http.post(userUrl + '/post/comment', 
    {
      content: contentProduct,
      productId: proId,
      userId: userId
    });
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.error.body.message || 'Server Error');
  }
}
