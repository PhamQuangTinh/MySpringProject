export class CartItem {
  productId: number;
  productName: string;
  productImage: string;
  qty: number;
  price: number;

  constructor(productId: number, productName: string, productImage: string, qty = 1, price: number) {
    this.productId = productId;
    this.productName = productName;
    this.productImage = productImage;
    this.qty = qty;
    this.price = price;
  }
}
