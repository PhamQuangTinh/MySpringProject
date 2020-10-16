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
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductItemComponent } from './product-list/product-item/product-item.component';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { MatSelectModule } from '@angular/material/select';
import { NgxImageZoomModule } from 'ngx-image-zoom';
@NgModule({
  declarations: [
    // HomeComponent,
    ShopComponent,
    ContactComponent,
    PagesComponent,
    ProductComponent,
    MenuComponent,
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
    MatSelectModule,
    NgxImageZoomModule
  ]
})
export class ShopModule { }
