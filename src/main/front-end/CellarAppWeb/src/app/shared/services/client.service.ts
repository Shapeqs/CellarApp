import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Client} from "../models/client.model";
import {LoginService} from "./login.service";
import {Bottle} from "../models/bottle.model";
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  public getClients():Observable<Client[]>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.get<Client[]>(environment.apiUrls.clients, httpOptions);
  }

  addClient(client: Client) {
    const body = JSON.stringify(client);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.post<Client>(environment.apiUrls.clients, body, httpOptions);
  }

  updateClient(result: Client) {
    const body = JSON.stringify(result);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    };
    return this.http.put<Client>(environment.apiUrls.clients + result.id, body, httpOptions);
  }

  public deleteOne(client:Client) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.delete<void>(environment.apiUrls.clients + client.id, httpOptions);
  }
}
