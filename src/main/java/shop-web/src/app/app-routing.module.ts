import { ProductComponent } from './components/user-components/product/product.component';
import { CheckOutComponent } from './components/user-components/check-out/check-out.component';
import { ShoppingCartComponent } from './components/user-components/shopping-cart/shopping-cart.component';
import { HomeComponent } from './components/user-components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { RegisterComponent } from './components/user-components/register/register.component';
import { LoginComponent } from './components/user-components/login/login.component';
import { FilterProductsComponent } from './components/user-components/filter-products/filter-products.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { ShopComponent } from './components/user-components/shop/shop.component';
import { HistoryTransactionComponent } from './components/user-components/history-transaction/history-transaction.component';
import { OrderComfirmComponent } from './components/user-components/order-comfirm/order-comfirm.component';




const routes: Routes = [
  {
    path:'products',
    children:[
      {
        path:'women',
        component: ShopComponent,
      },
      {
        path:'men',
        component: ShopComponent,
      },
      {
        path:'girl',
        component: ShopComponent,
      },
      {
        path:'boy',
        component: ShopComponent,
      },
      {
        path:'product-detail',
        component: ProductComponent,
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
    ]
    // loadChildren: () => import('./components/user-components/shop/shop.module').then(m => m.ShopModule),
  },
  {
    path:'admin',
    loadChildren: () => import('./components/admin-components/admin-components.module').then(x => x.AdminComponentsModule)
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'filter',
    component: FilterProductsComponent
  },
  
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'pay-confirm',
    component: OrderComfirmComponent,
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path:'shopping-cart',
    component:ShoppingCartComponent
  },

  {
    path:'check-out',
    component: CheckOutComponent
  },
  {
    path:'transaction/:id',
    component: HistoryTransactionComponent,
  },  
  {
    path:'',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path:'**',
    component:PageNotFoundComponent,
  },
  
];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    {
      onSameUrlNavigation: 'reload',
      preloadingStrategy: PreloadAllModules,
      enableTracing: true,
      scrollPositionRestoration: 'enabled',
    }
  )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
