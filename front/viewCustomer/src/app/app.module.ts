import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CustomerComponent } from './customer/customer.component';
import { CustomerService } from 'src/services/customerService';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { ReactiveFormsModule } from '@angular/forms';

const appRoutes: Routes = [
  { path: 'view', component: CustomerComponent },
  { path: 'newCustomer', component: NewCustomerComponent },
];

@NgModule({
  declarations: [AppComponent, CustomerComponent, NewCustomerComponent],
  imports: [
    HttpClientModule,
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent],
})
export class AppModule {}
