import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../../../shared/models/client.model";
import {NgForm} from "@angular/forms";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-client-form',
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit {

  @Input() clientEdited : Client;

  formTypeClient:string;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.formTypeClient=this.typeConverterForm(this.clientEdited.typeClient);
  }

  closeModal() {
    this.activeModal.close('Modal Closed');
  }

  editClient(editClientForm: NgForm) {
    this.clientEdited.lastName = editClientForm.value.lastName;
    this.clientEdited.email = editClientForm.value.email;
    this.clientEdited.phoneNumber = editClientForm.value.phoneNumber;
    if(this.formTypeClient==='Personne'){
      this.clientEdited.typeClient="Person";
      this.clientEdited.firstName = editClientForm.value.firstName;
      this.clientEdited.birthday = editClientForm.value.birthday;
      this.clientEdited.siren = "null";
    }else{
      this.clientEdited.siren = editClientForm.value.siren;
      this.clientEdited.typeClient="Company";
      this.clientEdited.firstName = "null";
      this.clientEdited.birthday = new Date();
    }
    if(this.clientEdited.registrationDate===undefined){
      this.clientEdited.registrationDate = new Date();
    }
    this.activeModal.close(this.clientEdited);
  }

  filterChanged(selectedValue:string){
    this.formTypeClient = selectedValue;
  }

  typeConverterForm(typeClient: string) {
    switch(typeClient) {
      case "Person": {
        return "Personne";
      }
      case "Company": {
        return "Entreprise";
      }
      default: {
        return "Personne";
      }
    }
  }
}
