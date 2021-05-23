import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bottle} from "../models/bottle.model";
import {environment} from "../../../environments/environment";
import { Naming } from '../models/naming.model';
import {Castel} from "../models/castel.model";
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class NamingService {

  constructor(private http: HttpClient) { }

  public addNaming(naming: Naming): Observable<Naming> {
    const body = JSON.stringify(naming);
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.post<Naming>(environment.apiUrls.namings,body,httpOptions);
  }

  public findNamingByName(name: String): Observable<Naming> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + sessionStorage.getItem(LoginService.CURRENT_USER_KEY)
      })
    }
    return this.http.get<Naming>(environment.apiUrls.namings+"find/"+name,httpOptions);
  }

  public getNamings(): Observable<Naming[]> {
    return this.http.get<Naming[]>(environment.apiUrls.namings);
  }
}
