import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WebPagesRoutingModule } from './web-pages-routing.module';

import { HomeComponent } from '../web-pages/home/home.component';
import { ShopComponent } from '../web-pages/shop/shop.component';
import { ContactComponent } from '../web-pages/contact/contact.component';
import { PagesComponent } from '../web-pages/pages/pages.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ProductComponent } from './product/product.component';
import { MenuComponent } from './menu/menu.component';
import { CheckOutComponent } from './check-out/check-out.component';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import { PageNotFoundComponent } from './../page-not-found/page-not-found.component';
import {HttpClientModule} from '@angular/common/http';



@NgModule({
  declarations: [
    HomeComponent,
    ShopComponent,
    ContactComponent,
    PagesComponent,
    LoginComponent,
    RegisterComponent,
    ShoppingCartComponent,
    ProductComponent,
    MenuComponent,
    CheckOutComponent,
    PageNotFoundComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    WebPagesRoutingModule
  ]
})
export class WebPagesModule { }
