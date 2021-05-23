import {Component, OnInit} from '@angular/core';
import {BottleService} from "../../shared/services/bottle.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Bottle} from "../../shared/models/bottle.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {BottleFormComponent} from "../../shared/component/bottle/bottle-form/bottle-form.component";
import {Castel} from "../../shared/models/castel.model";
import {Naming} from "../../shared/models/naming.model";

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.scss']
})
export class StockComponent implements OnInit {

  bottles$: Observable<Bottle[]> = this.bottleService.getBottles();
  bottles: Bottle[];
  bottle:Bottle;

  constructor(private modalService: NgbModal,private bottleService: BottleService) {
  }

  ngOnInit() {
    this.bottles$.subscribe(bottles => {
        this.bottles = bottles;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    this.bottle=new Bottle();
    this.bottle.castel = new Castel();
    this.bottle.naming= new Naming();
  }

  openForm() {
    if(this.modalService.hasOpenModals() == false){
      const modalRef = this.modalService.open(BottleFormComponent, { animation: true });
      modalRef.componentInstance.bottle = this.bottle;
      modalRef.result.then((result)=>{
        if(result!="Modal Closed"){
          this.addBottle(result);
        }
      }).catch((error)=>{
        console.log(error);
      })
    }
  }

  private addBottle(result: Bottle) {
    this.bottleService.addBottle(result).subscribe((response:Bottle) =>{
      console.log(response);
    });
  }
}
