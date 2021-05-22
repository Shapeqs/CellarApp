import {Component, Input, OnInit} from '@angular/core';
import {Bottle} from "../../../models/bottle.model";
import {environment} from "../../../../../environments/environment";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-bottle-details',
  templateUrl: './bottle-details.component.html',
  styleUrls: ['./bottle-details.component.scss']
})
export class BottleDetailsComponent implements OnInit {

  @Input() bottle: Bottle
  url_image:string = environment.apiUrls.images;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  hide(){
    this.activeModal.close();
  }

}
