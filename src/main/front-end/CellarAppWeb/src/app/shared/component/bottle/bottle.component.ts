import {Component, Input} from '@angular/core';
import {Bottle} from "../../models/bottle.model";
import {environment} from "../../../../environments/environment";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BottleDetailsComponent} from "./bottle-details/bottle-details.component";
import {BottleFormComponent} from "./bottle-form/bottle-form.component";

@Component({
  selector: 'app-bottle',
  templateUrl: './bottle.component.html',
  styleUrls: ['./bottle.component.scss']
})
export class BottleComponent{

  @Input() bottle: Bottle;

  url:string = environment.apiUrls.images;

  constructor(private modalService: NgbModal) { }

  openDetailModal(content) {
    if(this.modalService.hasOpenModals() == false){
      this.modalService.open(content, { animation: true });
    }
  }

  openEditModal() {
    const modalRef = this.modalService.open(BottleFormComponent, { animation: true });
    modalRef.componentInstance.bottle = this.bottle;
    modalRef.result.then((result)=>{
      console.log(result);
    }).catch((error)=>{
      console.log(error);
    })
  }

}
