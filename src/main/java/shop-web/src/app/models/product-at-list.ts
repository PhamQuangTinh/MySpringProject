export class ProductAtList {
    id: number;
    productName: string;
    unitPrice: number;
    discount: number;
    description: string;
    productAvatarEntities: string[];
  
    constructor(id: number, productName: string, unitPrice: number, discount: number, productAvatarEntities: string[]) {
        this.id = id;
        this.productName = productName;
        this.discount = discount
        this.unitPrice = unitPrice;
        this.productAvatarEntities = productAvatarEntities;
    }

  }