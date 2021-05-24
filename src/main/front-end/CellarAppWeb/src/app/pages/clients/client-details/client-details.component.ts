import {Component, Input} from '@angular/core';
import {Client} from "../../../shared/models/client.model";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.scss']
})
export class ClientDetailsComponent{

  @Input() client : Client

  constructor(public activeModal: NgbActiveModal) { }

  closeModal() {
    this.activeModal.close('Modal Closed');
  }

  alertEnCours() {
    alert("Dev en cours");
  }
}
