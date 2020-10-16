import { Observable } from 'rxjs';
import { productsUrl, userUrl, orderUrl } from './../../../config/api';
import { HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from '../../../services/session-storage.service'


@Injectable({
  providedIn: 'root'
})
export class DashBoardService {

  constructor(
    private http: HttpClient,
    private sessionStorage: SessionStorageService
  ) { }


  findProductPaginationService(page, size, type): Observable<any>{
      
    const params = new HttpParams()
    .set('page', page)
    .set('size', size)
    .set('sort', 'id low');
    if(type == 'product'){
        return this.http.get(productsUrl + "/get/all_product", {params});
    }
    else if(type == 'user'){
        return this.http.get(userUrl + "/get/all_user/pagination", {params});
    }
    else if(type == 'order'){
      return this.http.get(orderUrl + "/get/all_order_pagination", {params});
    }
  }
}
