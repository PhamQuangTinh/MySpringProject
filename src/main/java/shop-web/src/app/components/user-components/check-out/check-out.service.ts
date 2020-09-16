import { orderUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from '../../../services/session-storage.service'


@Injectable({
  providedIn: 'root'
})
export class CheckOutService {

  constructor(
    private http: HttpClient,
    private sessionStorage: SessionStorageService
  ) { }

  completePayment(paymentId: string, payerId: string): Observable<any> {
      var x = {
        payerId: payerId,
        paymentId: paymentId
      }
      return this.http.post(orderUrl + '/payment/completion', x)
  }

}
