import {Component, OnInit} from '@angular/core';
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {NgForm} from "@angular/forms";
import {BottleService} from "../../../services/bottle.service";
import {Bottle} from "../../../models/bottle.model";
import { Castel } from 'src/app/shared/models/castel.model';
import {Naming} from "../../../models/naming.model";
import {CastelService} from "../../../services/castel.service";
import {NamingService} from "../../../services/naming.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-bottle-form',
  templateUrl: './bottle-form.component.html',
  styleUrls: ['./bottle-form.component.scss']
})
export class BottleFormComponent implements OnInit{

  closeResult = '';
  bottle :Bottle;
  namings:Naming[];
  castels:Castel[];

  constructor(public activeModal: NgbActiveModal,private modalService: NgbModal,
              private bottleService: BottleService,private castelService:CastelService
  , private namingService:NamingService) {}

  ngOnInit(): void {
    this.bottle = new Bottle();
    this.castelService.getCastels().subscribe(castels => {
        this.castels = castels;
        console.log(castels);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
    this.namingService.getNamings().subscribe(namings=>{
      this.namings=namings;
      console.log(namings);
    },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
      );
  }

  closeModal() {
    this.activeModal.close('Modal Closed');
  }

  editBottle(editBottleForm :NgForm):void  {
    console.log(editBottleForm.value.name);
    this.bottle.vintage = editBottleForm.value.name;
    this.activeModal.close(this.bottle);
  }

  openCastelForm(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `${result}`;
      console.log(this.closeResult);
    }, (reason) => {
      this.closeResult = '';
      console.log(this.closeResult);
    });
  }
}
