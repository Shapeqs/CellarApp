import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../shared/services/client.service";
import {Client} from "../../shared/models/client.model";
import {Observable} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements OnInit {

  clients$ : Observable<Client[]> = this.clientService.getClients();
  clients:Client[];

  constructor(private clientService:ClientService) { }

  ngOnInit(): void {
    this.clients$.subscribe(clients =>{
      this.clients = clients;
    },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
      )
  }

}
