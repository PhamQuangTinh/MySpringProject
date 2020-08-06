import { PageNotFoundComponent } from './../page-not-found/page-not-found.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../web-pages/home/home.component';
import { ShopComponent } from '../web-pages/shop/shop.component';
import { ContactComponent } from '../web-pages/contact/contact.component';
import { PagesComponent } from '../web-pages/pages/pages.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { ProductComponent } from './product/product.component';
import { CheckOutComponent } from './check-out/check-out.component';

const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'shop',component: ShopComponent},
  {path:'shopping-cart',component:ShoppingCartComponent},
  {path:'product',component:ProductComponent},
  {path:'contact',component:ContactComponent},
  {path:'checkout',component:CheckOutComponent},
  {path:'', redirectTo: 'home', pathMatch: 'full'},
  {path:'**', component:PageNotFoundComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WebPagesRoutingModule { }
