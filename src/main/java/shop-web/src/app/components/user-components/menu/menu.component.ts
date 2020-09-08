import { FilterObject } from './../../../models/filter-object';
import { Component, OnInit, AfterViewInit, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';
import { MenuService } from './menu.servicce';


declare const $: any;


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit, AfterViewInit {
 
  @Output() filterEvent = new EventEmitter<any>();
  filterOb: FilterObject;
  checkedMen: boolean;
  checkedWomen: boolean;
  checkedKid: boolean;
  constructor(
    private menuService: MenuService,
  ) {}

  ngOnInit(): void {
    this.checkedMen = false;
    this.checkedWomen = false;
    this.checkedKid = false;
  }

  ngAfterViewInit() {
    $('.fw-size-choose .sc-item label, .pd-size-choose .sc-item label').on(
      'click',
      function () {
        $(
          '.fw-size-choose .sc-item label, .pd-size-choose .sc-item label'
        ).removeClass('active');
        $(this).addClass('active');
      }
    );

    $('input:checkbox').on('click', function () {
      var $box = $(this);
      if ($box.is(':checked')) {
        var group = "input:checkbox[name='" + $box.attr('name') + "']";

        $(group).prop('checked', false);
        $box.prop('checked', true);
      } else {
        $box.prop('checked', false);
      }
    });

    var rangeSlider = $('.price-range'),
      minamount = $('#minamount'),
      maxamount = $('#maxamount'),
      minPrice = rangeSlider.data('min'),
      maxPrice = rangeSlider.data('max');
    rangeSlider.slider({
      range: true,
      min: minPrice,
      max: maxPrice,
      values: [minPrice, maxPrice],
      slide: function (event, ui) {
        minamount.val('$' + ui.values[0]);
        maxamount.val('$' + ui.values[1]);
      },
    });
    minamount.val('$' + rangeSlider.slider('values', 0));
    maxamount.val('$' + rangeSlider.slider('values', 1));
  }

  editCheckbox(sex){
    if(sex === 'men'){
      this.checkedMen = !this.checkedMen;
    }else if(sex === 'women'){
      this.checkedWomen = !this.checkedWomen;
    }else if(sex ==='kid'){
      this.checkedKid = !this.checkedKid;
    }
  }

  filter(){
    var sexTypes = [];
    var minPrice = $('#minamount').val();
    var maxPrice = $('#maxamount').val();

    var fPrice = parseFloat(minPrice.replace('$',''));
    var lPrice = parseFloat(maxPrice.replace('$',''));
    
    this.checkedKid ? sexTypes.push('KID') : '';
    this.checkedMen ? sexTypes.push('MEN') : '';
    this.checkedWomen ? sexTypes.push('WOMEN') : '';

    this.filterOb = new FilterObject(fPrice,lPrice,sexTypes);
    this.filterEvent.emit(this.filterOb);

    // this.menuService.filterService(sexTypes,fPrice,lPrice,1,12).subscribe(
    //   (res:any)=>{
    //     console.log(res.data.body);
    //   },err=>{console.log(err);}
    // )
  }
}


