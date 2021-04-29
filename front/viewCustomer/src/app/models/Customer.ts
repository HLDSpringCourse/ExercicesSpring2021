export class Customer{
    id : number;
    name : string;
    codeInsee : string;

    constructor (id : number, name : string, code : string){
        this.id = id;
        this.codeInsee = code;
        this.name = name;
    }
}