import { Component, OnInit } from '@angular/core';
declare const jQuery: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {
    (function ($) {
      /*------------------
          Preloader
      --------------------*/
      $(window).on('load', function () {
        $('.loader').fadeOut();
        $('#preloder').delay(200).fadeOut('slow');
      });

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

      console.log(timerdate);

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

      /*----------------------------------------------------
       Language Flag js 
      ----------------------------------------------------*/
      $(document).ready(function (e) {
        //convert
        $('.language_drop').msDropdown({ roundedBorder: false });
        $('#tech').data('dd');
      });
    })(jQuery);




  }
}
