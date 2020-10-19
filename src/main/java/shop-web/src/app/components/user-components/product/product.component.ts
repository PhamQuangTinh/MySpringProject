import { throwError } from 'rxjs';
import { MessengerService } from './../../../services/messenger.service';
import { CartItem } from './../../../models/cart-item';
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
import { CheckOut } from 'src/app/models/check-out';
import { PlatformLocation } from '@angular/common' 

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

  page: number;
  qty: number = 1;
  cartItem: CartItem;
  isPicked: boolean = false;
  checkBigImage: any = '';
  checkColorLink: any;
  checkColorId: any;
  checkSizeType: any;
  checkSizeId: any;
  isLiked: boolean;
  checkOutItem: CheckOut;
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
  type: string;
  productInfo: any;
  productImages: any = [];
  productComments: any = [];
  relatedProduct: any = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private productService: ProductService,
    private sanitizer: DomSanitizer,
    private token: TokenStorageService,
    private msg: MessengerService,
    private location: PlatformLocation
  ) {
    location.onPopState(()=>{
      this.msg.sendMsgPage(this.page);
    }
    )
  }

  //fix unsafe image
  sanitize(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }
  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = this.productId = parseInt(params.get('id'));
      let page = this.page =  parseInt(params.get('page'));
      let type = this.type = params.get('type');
    });

    try {
      this.userId = this.token.getUser().id;
    } catch {
      this.userId = 0;
    }

    this.productInfo = this.productService
      .getProductInfo(this.productId, this.userId)
      .subscribe(
        (res) => {
          if (res.data !== null) {
            this.productInfo = res.data.body;

            var proInfo = this.productInfo.firstImagesColor;
            for (var i = 0; i < proInfo.length; i++) {
              this.productImages.push(proInfo[i].imageLink);
            }
            this.checkBigImage = this.sanitize(this.productImages[0]);
            this.checkColorLink = this.productInfo.colors[0].colorLink;
            this.checkColorId = this.productInfo.colors[0].id;
            this.isLiked = this.productInfo.userLikeProducts;
            this.productService.getRelatedProduct(this.productInfo.category).subscribe(
              (res:any)=>{
                if(res.data.body != null){
                  this.relatedProduct = res.data.body;
                } 
              },
              err=>{console.log(err)}
            );
          }
        },
        (err) => {}
      );

  }

  ngAfterViewInit() {}

  routerToCate(){
    this.router.navigateByUrl("/products/" + this.type);
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
          this.checkColorLink = color.colorLink;
          this.checkColorId = color.id;
        }
      });
  }

  movetoProduct(product){
    let st = ''
    if(product.sexType == 'WOMAN'){
      st = 'women'
    }else if(product.sexType == 'MAN'){
      st = 'man'
    }else if(product.sexType == 'Girls'){
      st = 'girl'
    }else if(product.sexType == 'Boys'){
      st = 'boy'
    }
    this.router.navigate(['/products/product-detail',{id: product.id, type: st}]).then(()=>window.location.reload());
  }
  chooseSize(size) {
    this.checkSizeType = size.sizeType;
    this.checkSizeId = size.id;
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
    if (this.checkColorLink === colorLink) {
      return true;
    }
    return false;
  }

  checkSizeChoosing(sizeId): boolean {
    if (this.checkSizeId === sizeId) {
      return true;
    }
    return false;
  }

  //increase num order of product
  increaseNumPro() {
    this.qty++;
  }

  //decrease num order of product
  decreaseNumPro() {
    if (this.qty > 1) this.qty--;
  }

  //clik comment tab
  tabComment() {
    this.productService
      .getCommentsProduct(this.productId, 1)
      .subscribe((res: any) => {
        this.productComments = res.data.body;
      });
  }

  //user comment product
  comment() {
    var text = this.textComment.nativeElement.value;
    if (this.userId > 0 && text !== '') {
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
    } else if(this.userId === 0){
      alert("you have to login first");
    } 
    else if (text == '') {
      alert('write some text pls');
    }
  }

  //Add product to cart
  addToCart() {
    if (this.checkSizeType !== undefined && this.qty > 0) {
      let quantity = parseInt(this.qty.toString());
      this.checkOutItem = new CheckOut(
        this.productId,
        this.checkSizeId,
        this.checkColorLink,
        quantity
      );
    
      this.productService.checkCartItem(this.checkOutItem).subscribe(
        (res: any) => {
          console.log(res);
          if (res.data.body.qty == this.qty) {
            this.cartItem = new CartItem(
              this.productId,
              this.productInfo.productName,
              this.checkColorId,
              this.checkColorLink,
              this.checkSizeId,
              this.checkSizeType,
              this.checkBigImage.changingThisBreaksApplicationSecurity,
              quantity,
              this.productInfo.unitPrice
            );
            console.log(this.cartItem);
            this.msg.sendMsg(this.cartItem);
            alert('Add product to cart success');
          } else {
            alert('Still have ' + res.data.body.qty + ' in stock');
          }
        },
        (err) => {
          console.log(err);
        }
      );
    } else {
      alert('choose size pls');
    }
  }

  likeProduct() {
    if (this.userId != null) {
      this.productService
        .likeProductSerVice(this.userId, this.productId)
        .subscribe((res) => {
          this.isLiked = true;
        });
    }else{
      alert("You have to login first");
    }
  }

  unLikeProduct() {
    if (this.userId != null) {
      this.productService
        .unLikeProductSerVice(this.userId, this.productId)
        .subscribe((res) => {
          this.isLiked = false;
        });
    }
  }
}
