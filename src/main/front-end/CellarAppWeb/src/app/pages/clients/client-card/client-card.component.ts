import {Component, Input} from '@angular/core';
import {Client} from "../../../shared/models/client.model";

@Component({
  selector: 'app-client-card',
  templateUrl: './client-card.component.html',
  styleUrls: ['./client-card.component.scss']
})
export class ClientCardComponent{

  @Input() client : Client

  constructor() { }

}
