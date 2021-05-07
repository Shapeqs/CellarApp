import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { Bottle } from "../models/bottle.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BottleService {

  constructor(private http: HttpClient) { }

  public getBottles():Observable<Bottle[]>{
      return this.http.get<Bottle[]>(environment.url + "/bottles");
  }
}
