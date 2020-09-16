import { productsUrl } from './../../../../config/api';
import { Observable } from 'rxjs';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FilterListService {
  constructor(private http: HttpClient) {}

  findProductsByListLike(page, size, sortBy, userId): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sortBy)
      .set('userId', userId);
    return this.http.get(productsUrl + '/get/product_like', { params });
  }

  findProductsByKeyword(page, size, sortBy, keyword): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', sortBy)
      .set('keyword', keyword);
    return this.http.get(productsUrl + '/get/product_name', { params });
  }

  findProductsFilter(page, size, sortBy, fPrice, lPrice, color, sexTypes): Observable<any>
  {
    const params = new HttpParams()
                    .set('page', page)
                    .set('size', size)
                    .set('sort', sortBy)
                    .set('fPrice', fPrice)
                    .set('lPrice', lPrice)
                    .set('colorName', color)
                    .set('sexTypes', sexTypes);
    return this.http.get(productsUrl + '/get/sex_type_and_price', {params})
  }

  // tslint:disable-next-line:typedef
  errorHandler(error: HttpErrorResponse) {
    return throwError(error.error.error.body.message || 'Server Error');
  }
}
