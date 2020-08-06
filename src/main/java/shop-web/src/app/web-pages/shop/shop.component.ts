import { Component, OnInit } from '@angular/core';
declare const jQuery: any;

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
})
export class ShopComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {

    (function ($) {

      $('.sorting, .p-show').niceSelect();

    })(jQuery);
  }
}
