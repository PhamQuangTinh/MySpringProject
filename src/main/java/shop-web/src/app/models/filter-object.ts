export class FilterObject {
  
  fPrice: number;
  lPrice: number;
  color: string;
  sexTypes:any;

  constructor(fPrice: number, lPrice: number, color: string, sexTypes:any) {
    this.fPrice = fPrice;
    this.lPrice = lPrice;
    this.color = color;
    this.sexTypes = sexTypes;
  }
}
