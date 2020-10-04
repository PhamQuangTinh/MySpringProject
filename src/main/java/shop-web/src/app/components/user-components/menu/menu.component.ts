import { Router } from '@angular/router';
import { SessionStorageService } from './../../../services/session-storage.service';
import { FilterObject } from './../../../models/filter-object';
import {
  Component,
  OnInit,
  AfterViewInit,
  ViewChild,
  ElementRef,
  Output,
  EventEmitter,
} from '@angular/core';

import { MenuService } from './menu.servicce';

declare const $: any;

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit, AfterViewInit {
  filterOb: FilterObject;
  checkedMen: boolean;
  checkedWomen: boolean;
  checkedGirl: boolean;
  checkedBoy: boolean;
  colorPicking: string = '';

  constructor(
    private menuService: MenuService,
    private session: SessionStorageService,
    private router: Router,
    ) {}

  ngOnInit(): void {
    this.checkedMen = false;
    this.checkedWomen = false;
    this.checkedGirl = false;
    this.checkedBoy = false;
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

  editCheckbox(sex) {
    if (sex === 'man') {
      this.checkedMen = !this.checkedMen;
    } else if (sex === 'woman') {
      this.checkedWomen = !this.checkedWomen;
    } else if (sex === 'girl') {
      this.checkedGirl = !this.checkedGirl;
    } else if (sex === 'boy'){
      this.checkedBoy = !this.checkedBoy;
    }
  }

  filter() {
    var sexTypes = [];
    var minPrice = $('#minamount').val();
    var maxPrice = $('#maxamount').val();

    var fPrice = parseFloat(minPrice.replace('$', ''));
    var lPrice = parseFloat(maxPrice.replace('$', ''));

    this.checkedGirl ? sexTypes.push('Girls') : '';
    this.checkedGirl ? sexTypes.push('Boys') : '';
    this.checkedMen ? sexTypes.push('MAN') : '';
    this.checkedWomen ? sexTypes.push('WOMAN') : '';

    if (sexTypes.length > 0) {
      this.filterOb = new FilterObject(
        fPrice,
        lPrice,
        this.colorPicking,
        sexTypes
      );
      this.router.navigateByUrl('/filter').then(()=>this.session.saveFilter(this.filterOb));
      // this.filterEvent.emit(this.filterOb);
    } else {
      alert('choose sex type pls');
    }
  }

  isColorChoosing(colorName) {
    return this.colorPicking === colorName;
  }

  checkColorChoosing(colorName) {
    this.colorPicking = colorName;
  }
}
