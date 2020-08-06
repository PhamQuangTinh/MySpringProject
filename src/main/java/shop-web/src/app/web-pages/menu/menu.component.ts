import { Component, OnInit } from '@angular/core';
declare const jQuery: any;

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

    (function ($) {


      $('.fw-size-choose .sc-item label, .pd-size-choose .sc-item label').on(
        'click',
        function () {
          $(
            '.fw-size-choose .sc-item label, .pd-size-choose .sc-item label'
          ).removeClass('active');
          $(this).addClass('active');
        }
      );

      var rangeSlider = $('.price-range'),
        minamount = $('#minamount'),
        maxamount = $('#maxamount'),
        minPrice = rangeSlider.data('min'),
        maxPrice = rangeSlider.data('max');
      rangeSlider.slider({
        range: true,
        min: minPrice,
        max: maxPrice,
        values: [minPrice, maxPrice],
        slide: function (event, ui) {
          minamount.val('$' + ui.values[0]);
          maxamount.val('$' + ui.values[1]);
        },
      });
      minamount.val('$' + rangeSlider.slider('values', 0));
      maxamount.val('$' + rangeSlider.slider('values', 1));
    })(jQuery);
  }

}
