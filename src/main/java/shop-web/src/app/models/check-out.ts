export class CheckOut {
  proId: number;
  sizeId: number;
  colorLink: string;
  unitInOrder: number;

  constructor(
    proId: number,
    sizeId: number,
    colorLink: string,
    unitInOrder: number
  ) {
    this.proId = proId;
    this.sizeId = sizeId;
    this.colorLink = colorLink;
    this.unitInOrder = unitInOrder;
  }
}
