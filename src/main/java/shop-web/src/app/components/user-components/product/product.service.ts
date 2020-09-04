import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { productsUrl } from 'src/app/config/api'
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient,
  ) { }


  getProductInfo(id: number): Observable<any>{
    return this.http.get(productsUrl + "/get/product_info/"+id); 
  }

  errorHandler(error: HttpErrorResponse){
    return throwError(error.error.error.body.message || 'Server Error');
}
}
