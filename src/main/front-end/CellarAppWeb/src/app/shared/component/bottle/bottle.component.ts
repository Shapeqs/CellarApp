import {Component, Input, OnInit} from '@angular/core';
import {Bottle} from "../../models/bottle.model";

@Component({
  selector: 'app-bottle',
  templateUrl: './bottle.component.html',
  styleUrls: ['./bottle.component.scss']
})
export class BottleComponent{

  @Input() bottle: Bottle

  constructor() { }

}
