import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bottle} from "../models/bottle.model";
import {environment} from "../../../environments/environment";
import {LoginService} from "./login.service";
import {User} from "../models/user.model";
import {Client} from "../models/client.model";

@Injectable({
  providedIn: 'root'
})
export class BottleService {

  constructor(private http: HttpClient) {
  }

  public getBottles(): Observable<Bottle[]> {
    return this.http.get<Bottle[]>(environment.apiUrls.bottles);
  }

  public addBottle(bottle:Bottle) {
    const body = JSON.stringify(bottle);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.post<Bottle>(environment.apiUrls.bottles, body, httpOptions);
  }

  public updateBottle(result: Bottle) {
    const body = JSON.stringify(result);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    };
    return this.http.put<Bottle>(environment.apiUrls.bottles + result.id, body, httpOptions);
  }

  public deleteOne(bottle:Bottle) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.delete<void>(environment.apiUrls.bottles + bottle.id, httpOptions);
  }
}
