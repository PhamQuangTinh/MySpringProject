import { PageNotFoundComponent } from '../../page-not-found/page-not-found.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShopComponent } from './shop.component';
import { ContactComponent } from '../contact/contact.component';
import { PagesComponent } from '../pages/pages.component';
import { ProductComponent } from '../product/product.component';
import { CheckOutComponent } from '../check-out/check-out.component';

const routes: Routes = [

  {
    path:'women',
    component: ShopComponent,
  },
  {
    path:':id',
    component:ProductComponent,
  },
  {
    path:'checkout',
    component:CheckOutComponent
  },
  {
    path:'**',
    component:PageNotFoundComponent
  },
  {
    path:'',
    redirectTo:'women',
    pathMatch: 'full'
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShopRoutingModule { }