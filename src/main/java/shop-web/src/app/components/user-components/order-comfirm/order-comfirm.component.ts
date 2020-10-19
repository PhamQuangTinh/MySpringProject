import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { OrderComfirmService } from './order-comfirm.service' 
@Component({
  selector: 'app-order-comfirm',
  templateUrl: './order-comfirm.component.html',
  styleUrls: ['./order-comfirm.component.css']
})
export class OrderComfirmComponent implements OnInit {

  paymentId: string;
  payerId: string;
  orderId: number;
  tokenPayment: string;
  isCancel: boolean;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: OrderComfirmService,
    private session: SessionStorageService
  ) { }

  ngOnInit(): void {

    this.route.queryParams.subscribe((params) => {
      this.paymentId = params['paymentId'];
      this.payerId = params['PayerID'];
      this.tokenPayment = params['token'];
    });

    if(this.payerId !== undefined && this.paymentId !== undefined && this.tokenPayment !== undefined && this.session.getOrderId() != null){
      this.isCancel = false;
      this.service.completePayment(this.paymentId, this.payerId, Number(this.session.getOrderId())).subscribe(
        (res)=>{
          let resString = res.data.body.stringRes;
          if(resString === 'success'){
            alert("Check out complete");
            this.session.removeCartItems();
            this.session.removeOrderId();
            
            this.router.navigateByUrl('/home').then(()=>{window.location.reload()});  
          }else{
            alert("Check out failed");
          }
          
        },
        (err) => {
          console.log(err);
        }
      )
    }else{
      this.isCancel = true;
      this.service.removeOrder(this.session.getOrderId()).subscribe(
        res=>{
          this.router.navigateByUrl('/home').then(()=>{window.location.reload()});  
        }
      )
    }

  }



}
