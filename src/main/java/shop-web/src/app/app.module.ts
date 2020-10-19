import { ProductItemComponent } from './components/user-components/shop/product-list/product-item/product-item.component';
import { ProductListComponent } from './components/user-components/shop/product-list/product-list.component';
import { MenuComponent } from './components/user-components/menu/menu.component';
import { PagesComponent } from './components/user-components/pages/pages.component';
import { ContactComponent } from './components/user-components/contact/contact.component';
import { ProductComponent } from './components/user-components/product/product.component';
import { ShopComponent } from './components/user-components/shop/shop.component';
import { CheckOutComponent } from './components/user-components/check-out/check-out.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { RegisterComponent } from './components/user-components/register/register.component';
import { LoginComponent } from './components/user-components/login/login.component';
import { HomeComponent } from './components/user-components/home/home.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';

import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CartItemComponent } from './components/shared/header/cart-item/cart-item.component';
import { ShoppingCartComponent } from './components/user-components/shopping-cart/shopping-cart.component';
import { ShoppingCartItemComponent } from './components/user-components/shopping-cart/shopping-cart-item/shopping-cart-item.component'
import { NgxPaginationModule } from 'ngx-pagination'
import { CarouselModule } from 'ngx-owl-carousel-o';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxImageZoomModule } from 'ngx-image-zoom';
import { FilterProductsComponent } from './components/user-components/filter-products/filter-products.component';
import { FilterItemComponent } from './components/user-components/filter-products/filter-list/filter-item/filter-item.component';
import { FilterListComponent } from './components/user-components/filter-products/filter-list/filter-list.component';
import {MatSelectModule} from '@angular/material/select';
import { HistoryTransactionComponent } from './components/user-components/history-transaction/history-transaction.component';
import { OrderComfirmComponent } from './components/user-components/order-comfirm/order-comfirm.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    CartItemComponent,
    ShoppingCartComponent,
    ShoppingCartItemComponent,
    FilterProductsComponent,
    FilterListComponent,
    FilterItemComponent,
    CheckOutComponent,
    PageNotFoundComponent,
    ProductComponent,
    ShopComponent,
    ContactComponent,
    PagesComponent,
    ProductComponent,
    MenuComponent,
    ProductListComponent,
    ProductItemComponent,
    AppComponent,
    HistoryTransactionComponent,
    OrderComfirmComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    CarouselModule,
    AppRoutingModule,
    NgxImageZoomModule,
    BrowserAnimationsModule,
    MatSelectModule,
  ],

  schemas: [ CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    //Add token to request header
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
