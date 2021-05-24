import {Component, Input} from '@angular/core';
import {Bottle} from "../../models/bottle.model";
import {environment} from "../../../../environments/environment";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BottleDetailsComponent} from "./bottle-details/bottle-details.component";
import {BottleFormComponent} from "./bottle-form/bottle-form.component";
import {BottleService} from "../../services/bottle.service";

@Component({
  selector: 'app-bottle',
  templateUrl: './bottle.component.html',
  styleUrls: ['./bottle.component.scss']
})
export class BottleComponent{

  @Input() bottle: Bottle;
  @Input() authentification: boolean;

  url:string = environment.apiUrls.images;

  constructor(private modalService: NgbModal, private bottleService:BottleService) {
  }

  openDetailModal() {
    if(this.modalService.hasOpenModals() == false){
      const modalRef = this.modalService.open(BottleDetailsComponent, { animation: true });
      modalRef.componentInstance.bottleViewed = this.bottle;
      modalRef.result.then((result)=>{
        if(result!="Modal Closed"){}
      }).catch((error)=>{
        console.log(error);
      })
    }
  }

  openEditModal() {
    if(this.modalService.hasOpenModals() == false){
      const modalRef = this.modalService.open(BottleFormComponent, { animation: true });
      modalRef.componentInstance.bottleEdited = this.bottle;
      modalRef.result.then((result)=>{
        if(result!="Modal Closed"){
          this.updateBottle(result);
        }
      }).catch((error)=>{
        console.log(error);
      })
    }
  }

  private updateBottle(result: Bottle) {
    this.bottleService.updateBottle(result).subscribe((response:Bottle) =>{
      console.log(response);
    });
  }
}
