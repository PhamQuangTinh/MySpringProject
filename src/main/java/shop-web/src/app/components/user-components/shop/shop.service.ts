import { orderUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

const URL = 'http://localhost:8080/api/product';

@Injectable({
    providedIn: 'root'
})
export class ShopService {

    constructor(private http: HttpClient){

    }

    findAllProductsPaginationService(page, size): Observable<any>
    {
        const params = new HttpParams()
                    .set('page', page)
                    .set('size', size);
        return this.http.get(URL + '/get/all_product', {params})
        .pipe(catchError(this.errorHandler));

    }



    paymentService(): Observable<any>{
        var x = {
            currency: "USD",
            description: "shopping",
            unitPrice: 21
        }
        return this.http.post(orderUrl + "/payment/make/payment", x);
    }

  // tslint:disable-next-line:typedef
    errorHandler(error: HttpErrorResponse){
        return throwError(error.error.error.body.message || 'Server Error');
    }


}
