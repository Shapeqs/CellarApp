import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Client} from "../models/client.model";
import {LoginService} from "./login.service";

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
}
