import { TokenStorageService } from './../../../../../services/token-storage.service';
import { Router } from '@angular/router';
import { MessengerService } from './../../../../../services/messenger.service';
import { ProductItemService } from './product-item.service';
import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css'],
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: any;

  bigImage: any;
  id: any = null;
  isLiked: boolean = false;
  checkColor: any;
  constructor(
    private productItemService: ProductItemService,
    private msg: MessengerService,
    private router: Router,
    private tokenStorage: TokenStorageService,
    private sanitizer: DomSanitizer,

  ) {}

  ngOnInit(): void {
    this.bigImage = this.sanitize(this.productItem.productAvatarEntities[1].imageLink);
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

  addProductToCart() {
    this.msg.sendMsg(this.productItem);
  }

  moveToProductInfo() {
    this.router.navigate(['/products', this.productItem.id]);
  }


  //change image when hover color
  changeBigImage(newBigImage) {
    this.bigImage = this.sanitize(newBigImage.imageLink);
    this.checkColor = newBigImage.id;
    
  }

  //like product
  likeProduct() {
    if (this.id != null) {
      this.productItemService
        .likeProductSerVice(this.id, this.productItem.id)
        .subscribe((res) => {
          this.isLiked = true;
        });
    } else {
      alert('You have to login first');
      this.router.navigateByUrl('/login');
    }
  }

  unLikeProduct() {
    if (this.id != null) {
      this.productItemService
        .unLikeProductSerVice(this.id, this.productItem.id)
        .subscribe((res) => {
          this.isLiked = false;
        });
    }
  }

  checkColorChoosing(colorId){   
    if(this.checkColor === colorId){
      return true;
    }
    return false;
  }
}
