import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/services/customerService';
import { Customer } from '../models/Customer';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent implements OnInit {
  customer: Customer;
  customers: Customer[];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {

  }

  viewCustomers(): void {
    this.customerService.getCustomers().subscribe(
      (response) => {
        this.customers = response;
      },
      (error) => {
        console.log('Erreur ! : ' + error);
      }
    );
    console.log(this.customers);
  }
}
