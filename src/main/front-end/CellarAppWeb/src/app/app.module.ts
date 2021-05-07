import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {BottleService} from "./shared/services/bottle.service";
import {HttpClientModule} from "@angular/common/http";
import { StockComponent } from './pages/stock/stock.component';
import { ClientsComponent } from './pages/clients/clients.component';
import { RouterModule } from '@angular/router';
import { BottleComponent } from './shared/component/bottle/bottle.component';
import { PageNotFoundComponent } from './pages/page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    StockComponent,
    ClientsComponent,
    BottleComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'stocks', component: StockComponent},
      {path: 'clients', component: ClientsComponent},
      {path: '', redirectTo: '/stocks', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
    ]),
    NgbModule
  ],
  providers: [BottleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
