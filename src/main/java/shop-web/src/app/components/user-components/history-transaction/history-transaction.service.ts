import { orderUrl, productsUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class HistoryTransactionService {

  constructor(
    private http: HttpClient,
  ) { }


    getAllOrderByUserId(userId): Observable<any>{
        return this.http.get(orderUrl +"/get/get_order_by_user_id/"+userId);
    }
  
}
