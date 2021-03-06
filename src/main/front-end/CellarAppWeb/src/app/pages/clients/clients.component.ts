import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../shared/services/client.service";
import {Client} from "../../shared/models/client.model";
import {Observable} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ClientFormComponent} from "./client-form/client-form.component";
import {LoginService} from "../../shared/services/login.service";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.scss']
})
export class ClientsComponent implements OnInit {

  clients$ : Observable<Client[]> = this.clientService.getClients();
  clients:Client[];
  clientToAdd:Client;
  authenticated:boolean;
  searchText: string;

  constructor(private clientService:ClientService,private modalService: NgbModal, private loginService:LoginService) { }

  ngOnInit(): void {
    this.clientToAdd = new Client();
    this.getClients();
    this.loginService.authenticated.subscribe(
      auth => this.authenticated = auth
    );
  }

  openForm() {
    if(this.modalService.hasOpenModals() == false){
      const modalRef =  this.modalService.open(ClientFormComponent, { animation: true });
      modalRef.componentInstance.clientEdited = this.clientToAdd;
      modalRef.result.then((result)=>{
        if(result!="Modal Closed"){
          this.addClient(result);
        }
      }).catch((error)=>{
        console.log(error);
      })
    }
  }

  private addClient(result: Client) {
    this.clientService.addClient(result).subscribe((response:Client) =>{
      this.getClients();
    });
  }

  private getClients() {
    this.clients$.subscribe(clients =>{
        this.clients = clients;
      },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }
}
