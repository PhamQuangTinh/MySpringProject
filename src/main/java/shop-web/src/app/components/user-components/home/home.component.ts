import { Router } from '@angular/router';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { TokenStorageService } from '../../../services/token-storage.service';
import { OwlOptions } from 'ngx-owl-carousel-o';
import {HomeService} from './home.service';
import { DomSanitizer } from '@angular/platform-browser';

declare const $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit, AfterViewInit {

  constructor(
    private tokenService:TokenStorageService,
    private homeService: HomeService,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {}

  productImagesMen: any = [];
  productImagesGirl: any = [];
  isClothingsMen: boolean = true;
  isInspirationMen: boolean = false;
  isShoesMen: boolean = false;
  isAccessoriesMen: boolean = false;

  isClothingsGirl: boolean = true;
  isSportsGirl: boolean = false;
  isShoesGirl: boolean = false;
  isInspirationGirl: boolean = false;


  customOptions: OwlOptions = {
    loop: false,
    mouseDrag: true,
    touchDrag: true,
    pullDrag: true,
    dots: false,
    navSpeed: 700,
    items: 3,
    navText: ['', ''],

    nav: true,
  };

  ngOnInit(): void {
    
    this.getCategoryProductMen("Clothes");
    this.getCategoryProductGirl("Clothes");

  }

  sanitize(url: any) {
    return this.sanitizer.bypassSecurityTrustUrl(url.productAvatarEntities[1].imageLink);
  }

  getCategoryProductMen(name){
    this.homeService.getCategoryProductService('man',name).subscribe(
      (res:any)=>{
        if(res.data.body!= null){
          this.productImagesMen = res.data.body;

        }
      }
    )
  }

  getCategoryProductGirl(name){
    this.homeService.getCategoryProductService('woman',name).subscribe(
      (res:any)=>{
        if(res.data.body!= null){
          this.productImagesGirl = res.data.body;

        }
      }
    )
  }
  goToProduct(image){
    let st = ''
    if(image.sexType == 'WOMAN'){
      st = 'women'
    }else if(image.sexType == 'MAN'){
      st = 'men'
    }else if(image.sexType == 'Girls'){
      st = 'girl'
    }else if(image.sexType == 'Boys'){
      st = 'boy'
    }
    this.router.navigate(['/products/product-detail',{id: image.id, type: st}]);
  }


  changeActiveMen(cate){
    this.isAccessoriesMen = false;
    this.isClothingsMen = false;
    this.isInspirationMen = false;
    this.isShoesMen = false;
    if(cate == 'menClothings'){
      this.isClothingsMen = true;
      this.getCategoryProductMen('Clothes');
    }else if(cate == 'manInspiration'){
      this.isInspirationMen = true;
      this.getCategoryProductMen('Inspiration');
    }else if(cate == 'menShoes'){
      this.isShoesMen = true;
      this.getCategoryProductMen('Shoes');
    }else if(cate == 'menAccessories'){
      this.isAccessoriesMen = true;
      this.getCategoryProductMen('Accessories');
    }
  }

  changeActiveGirl(cate){
    this.isInspirationGirl = false;
    this.isClothingsGirl = false;
    this.isSportsGirl = false;
    this.isShoesGirl = false;
    if(cate == 'girlClothings'){
      this.isClothingsGirl = true;
      this.getCategoryProductGirl('Clothes');
    }else if(cate == 'girlSports'){
      this.isSportsGirl = true;
      this.getCategoryProductGirl('Sports');
    }else if(cate == 'girlShoes'){
      this.isShoesGirl = true;
      this.getCategoryProductGirl('Shoes');
    }else if(cate == 'girlInspiration'){
      this.isInspirationGirl = true;
      this.getCategoryProductGirl('Inspiration');
    }
  }

  ngAfterViewInit(): void {
          /*------------------
          Background Set
      --------------------*/
      $('.set-bg').each(function() {
        var bg = $(this).data('setbg');
        $(this).css('background-image', 'url(' + bg + ')');
    });

      /*------------------
      Navigation
    --------------------*/
      $('.mobile-menu').slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true,
      });

      /*------------------
          Hero Slider
      --------------------*/
      $('.hero-items').owlCarousel({
        loop: true,
        margin: 0,
        nav: true,
        items: 1,
        dots: false,
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        navText: [
          '<i class="ti-angle-left"></i>',
          '<i class="ti-angle-right"></i>',
        ],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
      });

      /*------------------
          Product Slider
      --------------------*/
      $('.product-slider').owlCarousel({
        loop: true,
        margin: 25,
        nav: true,
        items: 4,
        dots: true,
        navText: [
          '<i class="ti-angle-left"></i>',
          '<i class="ti-angle-right"></i>',
        ],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        responsive: {
          0: {
            items: 1,
          },
          576: {
            items: 2,
          },
          992: {
            items: 2,
          },
          1200: {
            items: 3,
          },
        },
      });

      /*------------------
         logo Carousel
      --------------------*/
      $('.logo-carousel').owlCarousel({
        loop: false,
        margin: 30,
        nav: false,
        items: 5,
        dots: false,
        navText: [
          '<i class="ti-angle-left"></i>',
          '<i class="ti-angle-right"></i>',
        ],
        smartSpeed: 1200,
        autoHeight: false,
        mouseDrag: false,
        autoplay: true,
        responsive: {
          0: {
            items: 3,
          },
          768: {
            items: 5,
          },
        },
      });

      /*------------------
          CountDown
      --------------------*/
      // For demo preview
      var today = new Date();
      var dd = String(today.getDate()).padStart(2, '0');
      var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
      var yyyy = today.getFullYear();

      if (parseInt(mm) == 12) {
        mm = '01';
        yyyy = yyyy + 1;
      } else {
        mm = (parseInt(mm) + 1).toString();
        mm = String(mm).padStart(2, '0');
      }
      var timerdate = mm + '/' + dd + '/' + yyyy;
      // For demo preview end

      // console.log(timerdate);

      // Use this for real timer date
      /* var timerdate = "2020/01/01"; */

      $('#countdown').countdown(timerdate, function (event) {
        $(this).html(
          event.strftime(
            "<div class='cd-item'><span>%D</span> <p>Days</p> </div>" +
              "<div class='cd-item'><span>%H</span> <p>Hrs</p> </div>" +
              "<div class='cd-item'><span>%M</span> <p>Mins</p> </div>" +
              "<div class='cd-item'><span>%S</span> <p>Secs</p> </div>"
          )
        );
      });


  }
}
