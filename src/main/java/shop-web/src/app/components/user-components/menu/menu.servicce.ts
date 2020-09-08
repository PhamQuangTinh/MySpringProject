import { productsUrl, userUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';



@Injectable({
    providedIn: 'root'
})
export class MenuService {
    

    constructor(private http : HttpClient){

    }

    filterService(sexTypes, fPrice, lPrice, page, size): Observable<any>{
        const params = new HttpParams()
            .set('page', page)
            .set('size', size)
            .set('fPrice', fPrice)
            .set('lPrice', lPrice)
            .set('sexTypes', sexTypes);
        return this.http.get(productsUrl + "/get/sex_type_and_price",{params})
    }


}
