import { NgxPaginationModule } from 'ngx-pagination';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShopRoutingModule } from './shop-routing.module';

// import { HomeComponent } from '../components/home/home.component';
import { ShopComponent } from './shop.component';
import { ContactComponent } from '../contact/contact.component';
import { PagesComponent } from '../pages/pages.component';

import { ProductComponent } from '../product/product.component';
import { MenuComponent } from '../menu/menu.component';
import { CheckOutComponent } from '../check-out/check-out.component';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { PageNotFoundComponent } from '../../page-not-found/page-not-found.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductItemComponent } from './product-list/product-item/product-item.component';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  declarations: [
    // HomeComponent,
    ShopComponent,
    ContactComponent,
    PagesComponent,
    ProductComponent,
    MenuComponent,
    CheckOutComponent,
    PageNotFoundComponent,
    ProductListComponent,
    ProductItemComponent,
    
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    NgxPaginationModule,
    CarouselModule,
    ShopRoutingModule,
    MatSelectModule
  ]
})
export class ShopModule { }
