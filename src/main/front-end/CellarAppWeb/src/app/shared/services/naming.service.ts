import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bottle} from "../models/bottle.model";
import {environment} from "../../../environments/environment";
import { Naming } from '../models/naming.model';

@Injectable({
  providedIn: 'root'
})
export class NamingService {

  constructor(private http: HttpClient) { }

  public getNamings(): Observable<Naming[]> {
    return this.http.get<Naming[]>(environment.apiUrls.namings);
  }
}
