import {Component, Input} from '@angular/core';
import {Client} from "../../../shared/models/client.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BottleDetailsComponent} from "../../../shared/component/bottle/bottle-details/bottle-details.component";
import {ClientDetailsComponent} from "../client-details/client-details.component";
import {ClientFormComponent} from "../client-form/client-form.component";
import {ClientService} from "../../../shared/services/client.service";

@Component({
  selector: 'app-client-card',
  templateUrl: './client-card.component.html',
  styleUrls: ['./client-card.component.scss']
})
export class ClientCardComponent{

  @Input() client : Client

  constructor(private modalService: NgbModal,private clientService:ClientService) { }

  openDetailModal() {
    if(this.modalService.hasOpenModals() == false){
      const modalRef =  this.modalService.open(ClientDetailsComponent, { animation: true });
      modalRef.componentInstance.client = this.client;
    }
  }

  openEditModal() {
    if(this.modalService.hasOpenModals() == false){
      const modalRef =  this.modalService.open(ClientFormComponent, { animation: true });
      modalRef.componentInstance.clientEdited = this.client;
      modalRef.result.then((result)=>{
        if(result!="Modal Closed"){
          this.updateClient(result);
        }
      }).catch((error)=>{
        console.log(error);
      })
    }
  }

  private updateClient(result: Client) {
    this.clientService.updateClient(result).subscribe((response:Client) =>{
    });
  }
}
