import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Castel } from '../models/castel.model';
import {environment} from "../../../environments/environment";
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class CastelService {

  constructor(private http:HttpClient) { }

  public addCastel(castel: Castel): Observable<Castel> {
    const body = JSON.stringify(castel);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.post<Castel>(environment.apiUrls.castels,body,httpOptions);
  }

  public getCastels():Observable<Castel[]>{
    return this.http.get<Castel[]>(environment.apiUrls.castels);
  }
}
