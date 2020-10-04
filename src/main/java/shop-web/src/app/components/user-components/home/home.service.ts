import { orderUrl, productsUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(
    private http: HttpClient,
  ) { }


    getCategoryProductService(sType,categoryName){
        const params = new HttpParams()
                    .set("super_category_name", categoryName)
                    .set("sType", sType);
        return this.http.get(productsUrl + "/get/super_category_products_and_sex_type", {params});
    }
  
}
