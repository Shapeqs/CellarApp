import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Order} from "../models/order.model";
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  public getOrders(): Observable<Order[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.get<Order[]>(environment.apiUrls.orders,httpOptions);
  }

  public addOrder(order:Order) {
    const body = JSON.stringify(order);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.post<Order>(environment.apiUrls.orders, body, httpOptions);
  }
}
