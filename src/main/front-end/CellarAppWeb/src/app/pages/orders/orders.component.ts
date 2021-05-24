import { Component, OnInit } from '@angular/core';
import {OrderService} from "../../shared/services/order.service";
import {Order} from "../../shared/models/order.model";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders:Order[]

  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
    this.getOrder();
  }

  private getOrder() {
    this.orderService.getOrders().subscribe(orders=>{
      this.orders = orders;
      console.log(orders);
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      )
  }
}
