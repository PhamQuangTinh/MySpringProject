export class CartItem {
  productId: number;
  productName: string;
  colorId: number;
  colorLink: string;
  sizeId: number;
  sizeType: string;
  productImage: string;
  qty: number;
  price: number;

  constructor(
    productId: number,
    productName: string,
    colorId: number,
    colorLink: string,
    sizeId: number,
    sizeType: string,
    productImage: string,
    qty: number,
    price: number
  ) {
    this.productId = productId;
    this.productName = productName;
    this.colorId = colorId;
    this.colorLink = colorLink;
    this.sizeId = sizeId;
    this.sizeType = sizeType;
    this.productImage = productImage;
    this.qty = qty;
    this.price = price;
  }


}
