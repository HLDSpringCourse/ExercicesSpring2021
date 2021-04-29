import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Customer } from "src/app/models/Customer";

@Injectable()
export class CustomerService{
    customers : Customer[];      

    constructor(private httpClient : HttpClient){}

    oldaddCustomer(newCustomer : Customer) {
        this.httpClient
          .post('http://localhost:8080', newCustomer)
          .subscribe(
            () => {
              console.log('Enregistrement terminé !');
            },
            (error) => {
              console.log('Erreur ! : ' + error);
            }
          );
    }

    addCustomer(newCustomer : Customer) : Observable<any> {
        return this.httpClient.post('http://localhost:8080/customers', newCustomer);
    }

    getCustomers() : Observable <Customer[]>{
        return this.httpClient.get<Customer[]>('http://localhost:8080/customers');
    }
}