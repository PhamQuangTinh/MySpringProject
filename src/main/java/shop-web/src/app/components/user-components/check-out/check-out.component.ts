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
  tokenPayment: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: CheckOutService,
    ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.paymentId = params['paymentId'];
      this.payerId = params['PayerID'];
      this.tokenPayment = params['token'];
    });


    if(this.payerId !== undefined && this.paymentId !== undefined && this.tokenPayment !== undefined){
      this.service.completePayment(this.paymentId, this.payerId).subscribe(
        (res)=>{
          let routeAfterCompletePayment = res.data.body.stringRes;
          if(routeAfterCompletePayment === '/home'){
            alert("Check out complete");
            this.router.navigateByUrl(routeAfterCompletePayment);
          }else{
            alert("Check out failed");
          }
          
        },
        (err) => {
          console.log(err);
        }
      )
    }
  }
}
