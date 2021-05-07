import {Component, OnInit} from '@angular/core';
import {Bottle} from "./shared/models/bottle.model";
import {BottleService} from "./shared/services/bottle.service";
import {Observable} from "rxjs";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'CellarAppWeb';
  bottles$ : Observable<Bottle[]> = this.bottleService.getBottles();
  bottles:Bottle[]

  constructor(private bottleService:BottleService) {
  }

  ngOnInit() {
    this.bottles$.subscribe(bottles => {
      this.bottles = bottles;
    },
      (error:HttpErrorResponse)=>{
        alert(error.message);
      }
    );
  }
}
