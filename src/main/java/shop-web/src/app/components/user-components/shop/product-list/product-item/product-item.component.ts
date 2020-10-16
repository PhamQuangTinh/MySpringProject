import { CheckOut } from './../../../../../models/check-out';
import { CartItem } from './../../../../../models/cart-item';
import { TokenStorageService } from './../../../../../services/token-storage.service';
import { Router } from '@angular/router';
import { MessengerService } from './../../../../../services/messenger.service';
import { ProductItemService } from './product-item.service';
import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
declare const $: any;
@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css'],
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: any;
  @Input() page: number;

  currentRouter: string;
  qty: number = 1;
  bigImage: any;
  id: any = null;
  isLiked: boolean = false;
  checkColorId: any;
  checkColorLink: any;
  checkSizeType: any;
  checkSizeId: any;
  listSizes: any = [];
  cartItem: CartItem;
  checkOutItem: CheckOut
  constructor(
    private productItemService: ProductItemService,
    private msg: MessengerService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private sanitizer: DomSanitizer,
  
  ) {

  }

  ngOnInit(): void {
    this.currentRouter = this.router.url.replace("/products/","");
    this.bigImage = this.sanitize(
      this.productItem.productAvatarEntities[1].imageLink
    );
    this.checkColorId = this.productItem.productAvatarEntities[0].id;
    this.checkColorLink = this.productItem.productAvatarEntities[0].imageLink;
    try {
      this.id = this.tokenStorage.getUser().id;

      let productLiked = this.productItem.productLikeByUserEntities;
      if (productLiked.length > 0) {
        for (var i = 0; i < productLiked.length; i++) {
          if (productLiked[i].id === this.id) {
            this.isLiked = true;
            break;
          }
        }
      }
    } catch {}
  }

  //fix unsafe image
  sanitize(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  getIdModal(): string {
    return 'product' + this.productItem.id;
  }

  addProductToCart() {
    const a = 'product' + this.productItem.id;
    this.listSizes = [];
    this.productItemService
      .getSizeOfProduct(this.productItem.id)
      .subscribe((res) => {
        if (res.data != null && res.success) {
          this.getListColors(res.data.body);
          $('#' + a).modal('show');
        }
      });

    // this.msg.sendMsg(this.productItem);
  }

  moveToProductInfo() {
    this.router.navigate(['/products/product-detail',{page:this.page, id: this.productItem.id, type: this.currentRouter}]);
  }

  //like product
  likeProduct() {
    if (this.id != null) {
      this.productItemService
        .likeProductSerVice(this.id, this.productItem.id)
        .subscribe(
          (res) => {
            this.isLiked = true;
          },
          (err) => {
            console.log(err);
          }
        );
    }else{
      alert("You have to login first");
    }
  }

  unLikeProduct() {
    if (this.id != null) {
      this.productItemService
        .unLikeProductSerVice(this.id, this.productItem.id)
        .subscribe(
          (res) => {
            this.isLiked = false;
          },
          (err) => {
            console.log(err);
          }
        );
    }
  }

  //change image when hover color
  changeBigImage(color, image) {
    this.bigImage = this.sanitize(image.imageLink);
    this.checkColorId = color.id;
    this.checkColorLink = color.imageLink;
  }

  checkColorChoosing(colorId) {
    if (this.checkColorId === colorId) {
      return true;
    }
    return false;
  }

  checkSizeChoosing(size): boolean {
    if (this.checkSizeId === size) {
      return true;
    }
    return false;
  }

  chooseSize(size) {
    this.checkSizeType = size.sizeType;
    this.checkSizeId = size.id;
  }

  addToCart() {
    let num;
    if (this.checkSizeType !== undefined && this.qty > 0) {

      this.checkOutItem = new CheckOut(this.productItem.id, this.checkSizeId, this.checkColorLink, this.qty);

      this.productItemService.checkCartItem(this.checkOutItem).subscribe(
        (res: any)=>{

          if(res.data.body.qty == this.qty){
            this.cartItem = new CartItem(
              this.productItem.id,
              this.productItem.productName,
              res.data.body.colorId,
              this.checkColorLink,
              this.checkSizeId,
              this.checkSizeType,
              this.bigImage.changingThisBreaksApplicationSecurity,
              this.qty,
              this.productItem.unitPrice
            );
      
      
            this.msg.sendMsg(this.cartItem);
            alert('Add product to cart success');
            $('.modal').modal('hide');
          }else{
            alert("Still have " + res.data.body.qty + " in stock")
          }
        }
      )      
    } else if (this.checkSizeType === undefined) {
      alert('choose size pls');
    } else if (this.qty <= 0) {
      alert('quantity incorrect');
    }
  }

  getListColors(firstListColors) {
    var x1 = firstListColors.length;
    var x2 = this.productItem.productAvatarEntities.length / 2;
    var length = x1 / x2;
    for (var i = 0; i < length; i++) {
      this.listSizes[i] = firstListColors[i];
    }
  }
}
