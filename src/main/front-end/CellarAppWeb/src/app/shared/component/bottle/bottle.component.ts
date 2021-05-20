import {Component, Input} from '@angular/core';
import {Bottle} from "../../models/bottle.model";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-bottle',
  templateUrl: './bottle.component.html',
  styleUrls: ['./bottle.component.scss']
})
export class BottleComponent{

  @Input() bottle: Bottle

  url:string = environment.apiUrls.images;

  constructor() { }

}
