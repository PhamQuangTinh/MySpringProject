import { TokenStorageService } from './../../../services/token-storage.service';
import { ProductService } from './product.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import {
  Component,
  OnInit,
  AfterViewInit,
  ViewChild,
  ElementRef,
} from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';
import { DomSanitizer } from '@angular/platform-browser';

declare const $: any;

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit, AfterViewInit {
  @ViewChild('numProduct') numProduct: ElementRef;
  @ViewChild('textComment') textComment: ElementRef;
  isFirstSmallImage: boolean;

  isPicked: boolean = false;
  checkBigImage: any = '';
  checkColor: any;
  checkSize:any;

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

  productId: number;
  userId: number;
  productInfo: any;
  productImages: any = [];
  productComments: any = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private sanitizer: DomSanitizer,
    private token: TokenStorageService
  ) {}

  //fix unsafe image
  sanitize(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
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
              this.productImages.push(proInfo[i].imageLink);
            }
            this.checkBigImage = this.sanitize(this.productImages[0]);
            this.checkColor = this.productInfo.colors[0].colorLink;
          }
        },
        (err) => {}
      );
    try {
      this.userId = this.token.getUser().id;
    } catch {}
  }

  ngAfterViewInit() {
    console.log(this.numProduct.nativeElement.value);
  }

  //change big image
  changeViewProduct(image) {
    this.checkBigImage = this.sanitize(image);
    this.isFirstSmallImage = false;
  }

  //change image when choose color
  chooseColorImage(color) {
    this.productService
      .getColorProductImages(this.productId, color.id)
      .subscribe((res: any) => {
        if (res.data != null) {
          this.productImages = res.data.body;
          this.checkBigImage = this.sanitize(this.productImages[0]);
          this.checkColor = color.colorLink;
        }
      });
  }

  chooseSize(size){
    this.checkSize = size
  }

  //check what small image is picking
  checkSmallImageChossing(image): boolean {
    if (this.checkBigImage.changingThisBreaksApplicationSecurity === image) {
      return true;
    }
    return false;
  }
  //Check what color is piking
  checkColorChoosing(colorLink): boolean {
    if(this.checkColor === colorLink){
      return true;
    }
    return false;
  }

  checkSizeChoosing(size) : boolean{
    if(this.checkSize === size){
      return true;
    }
    return false;
  }

  //increase num order of product
  increaseNumPro() {
    this.numProduct.nativeElement.value++;
  }

  //decrease num order of product
  decreaseNumPro() {
    if (this.numProduct.nativeElement.value > 1)
      this.numProduct.nativeElement.value--;
  }

  //clik comment tab
  tabComment() {
    this.productService
      .getCommentsProduct(this.productId, 1)
      .subscribe((res: any) => {
        this.productComments = res.data.body.listResponse;
      });
  }

  //user comment product
  comment() {
    var text = this.textComment.nativeElement.value;
    if (this.userId != null && text !== '') {
      this.productService
        .commentProduct(this.userId, this.productId, text)
        .subscribe(
          (res: any) => {
            this.tabComment();
            this.textComment.nativeElement.value = '';
          },
          (err: any) => {
            console.log(err);
          }
        );
    } else if (this.userId == null) {
      alert('you have to login first');
    } else if (text == '') {
      alert('write some text pls');
    }
  }
}
