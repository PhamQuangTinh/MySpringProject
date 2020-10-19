import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderComfirmComponent } from './order-comfirm.component';

describe('OrderComfirmComponent', () => {
  let component: OrderComfirmComponent;
  let fixture: ComponentFixture<OrderComfirmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderComfirmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderComfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
