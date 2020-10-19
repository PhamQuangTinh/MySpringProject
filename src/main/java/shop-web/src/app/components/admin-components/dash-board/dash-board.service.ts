import { Observable } from 'rxjs';
import { roleUrl, productsUrl, userUrl, orderUrl } from './../../../config/api';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from '../../../services/session-storage.service';
import { resolveSanitizationFn } from '@angular/compiler/src/render3/view/template';

@Injectable({
  providedIn: 'root',
})
export class DashBoardService {
  deleteOrder(dataId: any) {
    return this.http.delete(orderUrl + "/delete/delete_order/" + dataId);
  }
  deleteProduct(dataId: any) {
    return this.http.delete(productsUrl + "/delete/delete_product/" + dataId);
  }
  deleteUser(dataId: any) {
    return this.http.delete(userUrl + "/delete/id/" + dataId);
  }
  createProduct(product): Observable<any> {
    return this.http.post(productsUrl + '/post/create_new_product', product);
  }
  createUser(user): Observable<any> {

    var roles: Array<any> =[];
    roles.push(user.roleString);
    var request = {
      username: user.username,
      password: user.password,
      email: user.email,
      phone: user.phone,
      lastName: user.lastName,
      firstName: user.firstName,
      roles: roles
    };
    return this.http.post(userUrl + '/post/register', request);
  }
  updateProduct(product): Observable<any> {
    return this.http.put(productsUrl + '/put/update_product', product);
  }
  updateUser(user): Observable<any> {
    var request = {
      id: user.id,
      username: user.username,
      newPass: user.password,
      oldPass: '',
      email: user.email,
      phone: user.phone,
      lastName: user.lastName,
      firstName: user.firstName,
    };
    console.log(request);
    return this.http.put(userUrl + '/put/update_user_admin', request);
  }

  constructor(
    private http: HttpClient,
    private sessionStorage: SessionStorageService
  ) {}

  findAllRole(): Observable<any> {
    return this.http.get(roleUrl + '/get/all_role');
  }

  findProductPaginationService(page, size, type): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sort', 'id high');
    if (type == 'product') {
      return this.http.get(productsUrl + '/get/all_product', { params });
    } else if (type == 'user') {
      return this.http.get(userUrl + '/get/all_user/pagination', { params });
    } else if (type == 'order') {
      return this.http.get(orderUrl + '/get/all_order_pagination', { params });
    }
  }
}
