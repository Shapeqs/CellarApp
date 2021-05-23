import {Castel} from "./castel.model";
import {Naming} from "./naming.model";

export class Bottle {
  id:number;
  vintage:string;
  year:number;
  infos:string;
  color:string;
  price:number;
  alcool:number;
  quantity:number;
  castel:Castel;
  naming:Naming;
}
