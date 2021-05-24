import { Component, OnInit } from '@angular/core';
import {Client} from "../../../shared/models/client.model";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Bottle} from "../../../shared/models/bottle.model";
import {BottleService} from "../../../shared/services/bottle.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {Order} from "../../../shared/models/order.model";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {

  client:Client;
  bottles:Bottle[];
  basket;
  amountOrder:number;


  constructor(private bottleService: BottleService) { }

  ngOnInit(): void {
    this.client = history.state.data;
    this.getBottles()
    this.basket = new Map<Bottle,Number>();
    this.amountOrder =0;
  }

  getBottles(){
    this.bottleService.getBottles().subscribe(bottles => {
        this.bottles = bottles;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  addBasket(addToBasket: NgForm, bottle: Bottle) {
    console.log(addToBasket.value.quantity);
    if(this.basket.get(bottle)===undefined){
      this.basket.set(bottle, Number(addToBasket.value.quantity));
    }else{
      let number = this.basket.get(bottle);
      this.basket.set(bottle, number+ Number(addToBasket.value.quantity));
    }
    console.log(this.basket);
  }


  removeToBasket(key: Bottle) {
    this.basket.delete(key);
  }

  formatNumber(number: number) {
   return Math.trunc(number*100)/100;
  }

  totalOrder() {
    this.amountOrder=0;
    this.basket.forEach((value: number, key: Bottle) => {
      this.amountOrder += key.price * value;
    });
    return this.formatNumber(this.amountOrder);
  }

  saveOrder() {
    let order = new Order();
    order.client = this.client;
    order.listBottles = this.basket;
    order.orderDate = new Date();
    console.log(order);
  }
}
