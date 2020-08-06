import { Component, OnInit } from '@angular/core';
declare const jQuery: any;

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    (function ($) {
      /*------------------
		Single Product
	--------------------*/
      $('.product-thumbs-track .pt').on('click', function () {
        $('.product-thumbs-track .pt').removeClass('active');
        $(this).addClass('active');
        var imgurl = $(this).data('imgbigurl');
        var bigImg = $('.product-big-img').attr('src');
        if (imgurl != bigImg) {
          $('.product-big-img').attr({ src: imgurl });
          $('.zoomImg').attr({ src: imgurl });
        }
      });

      $('.product-pic-zoom').zoom();

      /*-----------------------
       Product Single Slider
    -------------------------*/
      $('.ps-slider').owlCarousel({
        loop: false,
        margin: 10,
        nav: true,
        items: 3,
        dots: false,
        navText: [
          '<i class="fa fa-angle-left"></i>',
          '<i class="fa fa-angle-right"></i>',
        ],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
      });


      var proQty = $('.pro-qty');
      proQty.prepend('<span class="dec qtybtn">-</span>');
      proQty.append('<span class="inc qtybtn">+</span>');
      proQty.on('click', '.qtybtn', function () {
        var $button = $(this);
        var oldValue = $button.parent().find('input').val();
        if ($button.hasClass('inc')) {
          var newVal = parseFloat(oldValue) + 1;
        } else {
          // Don't allow decrementing below zero
          if (oldValue > 0) {
            var newVal = parseFloat(oldValue) - 1;
          } else {
            newVal = 0;
          }
        }
        $button.parent().find('input').val(newVal)
      })
    })(jQuery);
  }
}
