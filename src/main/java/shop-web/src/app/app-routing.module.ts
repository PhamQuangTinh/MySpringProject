import { CheckOutComponent } from './components/user-components/check-out/check-out.component';
import { ShoppingCartComponent } from './components/user-components/shopping-cart/shopping-cart.component';
import { HomeComponent } from './components/user-components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';
import { RegisterComponent } from './components/user-components/register/register.component';
import { LoginComponent } from './components/user-components/login/login.component';
import { FilterProductsComponent } from './components/user-components/filter-products/filter-products.component';




const routes: Routes = [
  {
    path:'products',
    loadChildren: () => import('./components/user-components/shop/shop.module').then(m => m.ShopModule),
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
    path:'',
    redirectTo: 'home',
    pathMatch: 'full'
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
