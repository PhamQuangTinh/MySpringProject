import { ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { HistoryTransactionService } from './history-transaction.service'
@Component({
  selector: 'app-history-transaction',
  templateUrl: './history-transaction.component.html',
  styleUrls: ['./history-transaction.component.css']
})
export class HistoryTransactionComponent implements OnInit {

  userId: any;
  transactionData: any;
  constructor(
    private service: HistoryTransactionService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.userId = parseInt(params.get('id'));
    });
    this.getAllOrderById();
  }

  getAllOrderById(){
    this.service.getAllOrderByUserId(this.userId).subscribe(
      res=>{
        if(res.success && res.data.body != null){
          this.transactionData = res.data.body;
          console.log(this.transactionData);
        }
      },err=>{console.log(err)}
    )
  }

}
