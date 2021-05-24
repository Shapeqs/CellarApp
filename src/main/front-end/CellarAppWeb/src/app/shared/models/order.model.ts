import {Client} from "./client.model";
import {Bottle} from "./bottle.model";

export class Order {
  id:number;
  orderDate:Date;
  client:Client;
  listBottles:Map<Bottle,Number>;
}
