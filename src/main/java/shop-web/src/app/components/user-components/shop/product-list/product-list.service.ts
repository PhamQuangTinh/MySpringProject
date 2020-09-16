import { Observable } from 'rxjs';
import { HttpClient,HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { productsUrl } from 'src/app/config/api'
@Injectable({
  providedIn: 'root'
})
export class ProductListService {

  constructor(private http: HttpClient) { 

  }

  findAllProductsPaginationService(page, size, sortBy): Observable<any>
    {
        const params = new HttpParams()
                    .set('page', page)
                    .set('size', size)
                    .set('sort',sortBy);
        return this.http.get(productsUrl + '/get/all_product', {params})
        .pipe(catchError(this.errorHandler));

    }

  

  findProductsByListLike(page, size, sortBy, userId): Observable<any>
  {
    const params = new HttpParams()
                    .set('page', page)
                    .set('size', size)
                    .set('sort', sortBy)
                    .set('userId', userId);
    return this.http.get(productsUrl + '/get/product_like', {params})
  }

  // tslint:disable-next-line:typedef
    errorHandler(error: HttpErrorResponse){
        return throwError(error.error.error.body.message || 'Server Error');
    }
}
