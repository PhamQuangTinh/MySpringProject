import { CartItem } from './../../../models/cart-item';
import { SessionStorageService } from './../../../services/session-storage.service';
import { ParamMap, ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { CheckOutService } from './check-out.service'
import { RtlScrollAxisType } from '@angular/cdk/platform';
import { getMatScrollStrategyAlreadyAttachedError } from '@angular/cdk/overlay/scroll/scroll-strategy';
@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css'],
})
export class CheckOutComponent implements OnInit {
  paymentId: string;
  payerId: string;
  orderId: number;
  tokenPayment: string;
  cartItems: CartItem[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CheckOutService,
    private session: SessionStorageService
    ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.paymentId = params['paymentId'];
      this.payerId = params['PayerID'];
      this.tokenPayment = params['token'];
    });


    if(this.payerId !== undefined && this.paymentId !== undefined && this.tokenPayment !== undefined && this.session.getOrderId() != null){
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
    }

    this.cartItems = this.session.getCartItems();
  }

  getTotal(): number{
    let total = 0;
    this.cartItems.forEach(x =>{
      total += x.qty * x.price;
    })
    return total;
  }

  order(){
    this.service.paymentService().subscribe(
      (res)=>{
        this.session.saveOrderId(res.data.body.orderId);
        if(this.orderId != 0){
          window.location.href = res.data.body.url;
        }else{
          alert("Pay by Paypal failed")
        }
      },
      err=>{console.log(err)
        
      }
    );
  }
}
