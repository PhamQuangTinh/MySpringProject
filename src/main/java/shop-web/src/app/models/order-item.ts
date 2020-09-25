export class OrderItem{
    proId: number;
    colorId: number;
    sizeId: number;
    qty: number;
    finalPrice: number;
    constructor(proId: number, colorId: number, sizeId: number, qty: number, finalPrice: number){
        this.proId = proId;
        this.colorId = colorId;
        this.sizeId = sizeId;
        this.qty = qty;
        this.finalPrice = finalPrice;
    }
}