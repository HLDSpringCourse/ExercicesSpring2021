import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/services/customerService';
import { Customer } from '../models/Customer';

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css'],
})
export class NewCustomerComponent implements OnInit {
  userForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.userForm = this.formBuilder.group({
      id: '',
      name: '',
      codeInsee: '',
    });
  }

  onSubmitForm() {
    const formValue = this.userForm.value;
    const newUser = new Customer(
      formValue['id'],
      formValue['name'],
      formValue['codeInsee']
    );

    this.customerService.addCustomer(newUser).subscribe(
      () => {
        console.log('Client ajouté !');
      },
      (error) => {
        console.log('Erreur ! : ' + error);
      }
    );

    this.router.navigate(['/view']);
  }
}
