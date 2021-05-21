import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";
import {Employe} from "../../shared/models/employe.model";
import {EmployeService} from "../../shared/services/employe.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  employes$: Observable<Employe[]> = this.employeService.getEmployes();
  employes: Employe[];

  constructor(private employeService: EmployeService) {
  }

  ngOnInit() {
    this.employes$.subscribe(bottles => {
        this.employes = bottles;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
