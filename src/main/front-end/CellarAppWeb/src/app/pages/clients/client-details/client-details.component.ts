import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../../../shared/models/client.model";

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.scss']
})
export class ClientDetailsComponent{

  @Input() client : Client

  constructor() { }
}
