import {Component, Input, OnInit} from '@angular/core';
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

  @Input() bottleEdited: Bottle
  closeResult = '';
  castelToAdd:Castel;
  namingToAdd:Naming;
  namings:Naming[];
  castels:Castel[];
  yearMax:number;

  constructor(public activeModal: NgbActiveModal,private modalService: NgbModal,
              private bottleService: BottleService,private castelService:CastelService
  , private namingService:NamingService) {}

  ngOnInit(): void {
    this.yearMax = new Date().getFullYear();
    this.castelToAdd = new Castel();
    this.namingToAdd = new Naming();
    this.getCastels();
    this.getNamings();
  }

  getCastels(){
    this.castelService.getCastels().subscribe(castels => {
        this.castels = castels;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  getNamings(){
    this.namingService.getNamings().subscribe(namings=>{
        this.namings=namings;
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
    this.bottleEdited.vintage = editBottleForm.value.vintage;
    this.bottleEdited.color=this.colorConverter(editBottleForm.value.colorPicker);
    this.bottleEdited.castel.id=this.findCastelByName(editBottleForm.value.castelPicker);
    this.bottleEdited.castel.name=editBottleForm.value.castelPicker;
    this.bottleEdited.naming.id = this.findNamingByName(editBottleForm.value.namingPicker);
    this.bottleEdited.naming.name = editBottleForm.value.namingPicker;
    this.bottleEdited.year = editBottleForm.value.yearBottle;
    this.bottleEdited.alcool = Number(editBottleForm.value.alcohol);
    this.bottleEdited.infos = editBottleForm.value.infos;
    this.bottleEdited.price = Number(editBottleForm.value.price);
    this.bottleEdited.quantity = editBottleForm.value.quantityBottle;
    this.activeModal.close(this.bottleEdited);
  }

  colorConverter(colorSelected:string){
    switch(colorSelected) {
      case "Rouge": {
        return "Red";
      }
      case "Rosé": {
        return "Pink";
      }
      case "Blanc": {
        return "White";
      }
      case "Moelleux": {
        return "Yellow";
      }
      default: {
        return this.bottleEdited.color;
      }
    }
  }

  findCastelByName(input:string){
    return (this.castels.find(element=>element.name===input).id);
  }

  findNamingByName(input:string){
    return (this.namings.find(element=>element.name===input).id);
  }

  colorConverterForm(colorSelected:string){
    switch(colorSelected) {
      case "Red": {
        return "Rouge";
      }
      case "Pink": {
        return "Rosé";
      }
      case "White": {
        return "Blanc";
      }
      case "Yellow": {
        return "Moelleux";
      }
      default: {
        return "Rouge";
      }
    }
  }

  openCastelForm(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `${result}`;
      this.castelToAdd.name = this.closeResult;
      this.addCastel();
    });
  }

  addCastel(){
    this.castelService.addCastel(this.castelToAdd).subscribe(
      (response: Castel) => {
        this.getCastels();
      }
    );
  }

  openNamingForm(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `${result}`;
      this.namingToAdd.name = this.closeResult;
      this.addNaming();
    });
  }

  addNaming(){
    this.namingService.addNaming(this.namingToAdd).subscribe(
      (response: Naming) => {
        this.getNamings();
      }
    );
  }
}
