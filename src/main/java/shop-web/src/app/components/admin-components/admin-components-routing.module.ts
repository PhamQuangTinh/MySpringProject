import { AuthAdminGuard } from './../../interceptors/auth-admin.guard';
import { DashBoardComponent } from './dash-board/dash-board.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: "dash-board",
    component: DashBoardComponent,
    canActivate:[AuthAdminGuard],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminComponentsRoutingModule { }
