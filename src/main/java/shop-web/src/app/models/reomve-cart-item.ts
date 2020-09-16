export class RemoveCartItem{

    proId: number;
    colorId: number;
    sizeId: number;

    constructor(proId: number, colorId: number, sizeId: number){
        this.proId = proId;
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}