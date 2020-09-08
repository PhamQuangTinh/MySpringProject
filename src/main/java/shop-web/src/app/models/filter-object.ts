export class FilterObject {
  
  fPrice: number;
  lPrice: number;
  sexTypes:any;

  constructor(fPrice: number, lPrice: number, sexTypes:any) {
    this.fPrice = fPrice;
    this.lPrice = lPrice;
    this.sexTypes = sexTypes;
  }
}
