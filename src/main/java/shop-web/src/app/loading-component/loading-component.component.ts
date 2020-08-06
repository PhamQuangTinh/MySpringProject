import { LoaderService } from './../services/loader.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-loading-component',
  templateUrl: './loading-component.component.html',
  styleUrls: ['./loading-component.component.css']
})
export class LoadingComponent implements OnInit {
  
  loading: boolean;

  constructor(private loaderService: LoaderService) {

    this.loaderService.isLoading.subscribe((v) => {
      // console.log(v);
      this.loading = v;
    });

  }
  ngOnInit() {
  }

}
