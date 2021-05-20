import {Injectable, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BottleService} from "./shared/services/bottle.service";
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {StockComponent} from './pages/stock/stock.component';
import {ClientsComponent} from './pages/clients/clients.component';
import {RouterModule} from '@angular/router';
import {BottleComponent} from './shared/component/bottle/bottle.component';
import {PageNotFoundComponent} from './pages/page-not-found/page-not-found.component';
import {ClientCardComponent} from './pages/clients/client-card/client-card.component';
import {ClientService} from "./shared/services/client.service";
import {OrdersComponent} from './pages/orders/orders.component';
import {LoginComponent} from './pages/login/login.component';
import {FormsModule} from "@angular/forms";
import {LoginService} from "./shared/services/login.service";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    AppComponent,
    StockComponent,
    ClientsComponent,
    BottleComponent,
    PageNotFoundComponent,
    ClientCardComponent,
    OrdersComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'stocks', component: StockComponent},
      {path: 'clients', component: ClientsComponent},
      {path: 'orders', component: OrdersComponent},
      {path: 'login', component: LoginComponent},
      {path: '', redirectTo: '/stocks', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
    ]),
    NgbModule,
    FormsModule
  ],
  providers: [BottleService, ClientService, LoginService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})

export class AppModule {
}
