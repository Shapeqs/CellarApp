import {Component, OnInit} from '@angular/core';
import {BottleService} from "../../shared/services/bottle.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bottle} from "../../shared/models/bottle.model";

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.scss']
})
export class StockComponent implements OnInit {

  bottles$: Observable<Bottle[]> = this.bottleService.getBottles();
  bottles: Bottle[];

  constructor(private bottleService: BottleService) {
  }

  ngOnInit() {
    this.bottles$.subscribe(bottles => {
        this.bottles = bottles;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
