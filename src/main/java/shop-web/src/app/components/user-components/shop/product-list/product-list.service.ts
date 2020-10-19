import { Observable } from 'rxjs';
import { HttpClient,HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { productsUrl } from 'src/app/config/api'
import { style } from '@angular/animations';
@Injectable({
  providedIn: 'root'
})
export class ProductListService {

  constructor(private http: HttpClient) { 

  }

  findAllProductsPaginationService(page, size, sortBy, sType, category): Observable<any>
    {
        if(sType == 'women'){
          sType = 'woman';
        }else if(sType == 'men'){
          sType = 'man';
        }else if(sType == 'girl'){
          sType = 'girls';
        }else if(sType == 'boy'){
          sType = 'boys';
        }
        const params = new HttpParams()
                    .set('sType', sType)
                    .set('page', page)
                    .set('size', size)
                    .set('sort',sortBy)
                    .set('cate', category);
        return this.http.get(productsUrl + '/get/all_product_by_sType_and_cate', {params})
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
