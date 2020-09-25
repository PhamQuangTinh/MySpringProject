import { orderUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from '../../../services/session-storage.service'
import { OrderItem } from 'src/app/models/order-item';


@Injectable({
  providedIn: 'root'
})
export class CheckOutService {

  constructor(
    private http: HttpClient,
    private sessionStorage: SessionStorageService
  ) { }

  completePayment(paymentId: string, payerId: string, orderId: number): Observable<any> {
      var x = {
        payerId: payerId,
        paymentId: paymentId,
        orderId: orderId
      }
      return this.http.post(orderUrl + '/payment/completion', x)
  }

  paymentService(): Observable<any> {
    let orderItems = [] as OrderItem[];
    this.sessionStorage.getCartItems().forEach(x=>{
      orderItems.push(new OrderItem(x.productId, x.colorId, x.sizeId, x.qty, x.price));
    })
    var x = {
      currency: 'USD',
      description: 'shopping',
      proInfo: orderItems,
    };
    return this.http.post(orderUrl + '/payment/make/payment', x);
  }
}
