import {Component, Input, OnInit} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {User} from "../../models/user.model";

@Component({
  selector: 'app-employe',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  @Input() employe: User

  url:string = environment.apiUrls.images;

  constructor() { }

  ngOnInit(): void {
  }

}
