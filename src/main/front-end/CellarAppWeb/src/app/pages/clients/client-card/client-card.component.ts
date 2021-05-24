import {Component, Input, TemplateRef} from '@angular/core';
import {Client} from "../../../shared/models/client.model";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ClientDetailsComponent} from "../client-details/client-details.component";
import {ClientFormComponent} from "../client-form/client-form.component";
import {ClientService} from "../../../shared/services/client.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-client-card',
  templateUrl: './client-card.component.html',
  styleUrls: ['./client-card.component.scss']
})
export class ClientCardComponent{

  @Input() client : Client
  private closeResult: string;
  deleteClient: Client;

  constructor(private modalService: NgbModal,private clientService:ClientService,private router: Router) { }

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

  removeClient(clientToRemove:Client) {
    this.clientService.deleteOne(clientToRemove).subscribe();
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
  }

  openDeleteModal(sure: TemplateRef<any>, client: Client) {
    this.deleteClient = client;
    this.modalService.open(sure, {ariaLabelledBy: 'modal-basic-title',
      backdropClass: 'light-blue-backdrop'}).result.then((result) => {
      if (result === 'Remove')  {
        this.removeClient(this.deleteClient);
      }
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any) {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
