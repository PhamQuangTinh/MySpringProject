import { TokenStorageService } from './../../../../../services/token-storage.service';
import { Router } from '@angular/router';
import { MessengerService } from './../../../../../services/messenger.service';
import { ProductItemService } from './product-item.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css'],
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: any;

  bigImage: string = '';
  id: any = null;
  isLiked: boolean = false;

  constructor(
    private productItemService: ProductItemService,
    private msg: MessengerService,
    private router: Router,
    private tokenStorage: TokenStorageService
  ) {}

  ngOnInit(): void {
    this.bigImage = this.productItem.productAvatarEntities[1].imageLink;
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

  addProductToCart() {
    this.msg.sendMsg(this.productItem);
  }

  moveToProductInfo() {
    this.router.navigate(['/products', this.productItem.id]);
  }

  getColor(color): string {
    console.log(color.imageLink);
    return color.imageLink;
  }

  changeBigImage(newBigImage) {
    this.bigImage = newBigImage;
  }

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
}
