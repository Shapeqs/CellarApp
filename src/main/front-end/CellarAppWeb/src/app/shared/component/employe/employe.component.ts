import {Component, Input, OnInit} from '@angular/core';
import {environment} from "../../../../environments/environment";
import {Employe} from "../../models/employe.model";

@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.scss']
})
export class EmployeComponent implements OnInit {

  @Input() employe: Employe

  url:string = environment.apiUrls.images;

  constructor() { }

  ngOnInit(): void {
  }

}
