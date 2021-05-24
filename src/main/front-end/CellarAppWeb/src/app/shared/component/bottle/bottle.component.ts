import {Component, Input, OnInit} from '@angular/core';
import {Bottle} from "../../models/bottle.model";
import {environment} from "../../../../environments/environment";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BottleDetailsComponent} from "./bottle-details/bottle-details.component";
import {BottleFormComponent} from "./bottle-form/bottle-form.component";
import {BottleService} from "../../services/bottle.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-bottle',
  templateUrl: './bottle.component.html',
  styleUrls: ['./bottle.component.scss']
})
export class BottleComponent implements OnInit{

  @Input() bottle: Bottle;
  @Input() displayButton:boolean;
  @Input() authentification: boolean;

  url:string = environment.apiUrls.images;

  constructor(private modalService: NgbModal, private bottleService:BottleService,private router: Router) {
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

  ngOnInit(): void {
    if(this.displayButton===undefined){
      this.displayButton = true;
    }
  }

  removeBottle(bottle: Bottle) {
    this.bottleService.deleteOne(bottle).subscribe();
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([currentUrl]);
    });
  }
}
