import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { Castel } from '../models/castel.model';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CastelService {

  constructor(private http:HttpClient) { }

  public getCastels():Observable<Castel[]>{
    return this.http.get<Castel[]>(environment.apiUrls.castels);
  }
}
