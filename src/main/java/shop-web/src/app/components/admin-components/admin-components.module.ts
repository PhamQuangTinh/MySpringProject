import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminComponentsRoutingModule } from './admin-components-routing.module';
import { DashBoardComponent } from './dash-board/dash-board.component';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [DashBoardComponent],
  imports: [
    CommonModule,
    NgxPaginationModule,
    FormsModule,
    AdminComponentsRoutingModule
  ]
})
export class AdminComponentsModule { }
