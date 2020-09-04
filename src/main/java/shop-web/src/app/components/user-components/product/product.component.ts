import { ProductService } from './product.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
declare const $: any;

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit, AfterViewInit {

  
  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    dots: false,
    navSpeed: 700,
    navText: ['Previous', 'Next'],
    responsive: {
      0: {
        items: 1 
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      },
      940: {
        items: 4
      }
    },
    nav: true
  }

  productId: number;
  productInfo: any;
  productImages: any = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService
  ) {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = parseInt(params.get('id'));
      this.productId = id;
    });

    this.productInfo = this.productService
      .getProductInfo(this.productId)
      .subscribe(
        (res) => {
          if (res.data !== null) {
            this.productInfo = res.data.body;
            console.log(this.productInfo);
          }
        },
        (err) => {}
      );
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = parseInt(params.get('id'));
      this.productId = id;
    });

    this.productInfo = this.productService
      .getProductInfo(this.productId)
      .subscribe(
        (res) => {
          if (res.data !== null) {
            this.productInfo = res.data.body;
            console.log(this.productInfo);

            var proInfo = this.productInfo.firstImagesColor;
            for (var i = 0; i < proInfo.length; i++) {
              let img = proInfo[i].imageLink.split('\\').join('/');
              this.productImages.push(img);
            }
            console.log(this.productImages)
          }
        },
        (err) => {}
      );
  }

  ngAfterViewInit() {
    //Jquery
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
      $button.parent().find('input').val(newVal);
    });
  }
}
