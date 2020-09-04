export class ProductItem {
    id: number;
    productName: string;
    unitPrice: number;
    discount: number;
    description: string;
    productImage: string[];
  
    constructor(id: number, productName: string, unitPrice: number, discount: number,) {
        this.id = id;
        this.productName = productName;
        this.discount = discount
        this.unitPrice = unitPrice;
    }

  }