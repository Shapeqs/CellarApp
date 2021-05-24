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
import { AdminComponent } from './pages/admin/admin.component';
import {FormUserComponent} from "./shared/component/employe/form-user/form-user.component";
import { UserComponent } from './shared/component/employe/user.component';
import { BottleDetailsComponent } from './shared/component/bottle/bottle-details/bottle-details.component';
import {ModalModule} from 'ngx-bootstrap/modal';
import { BottleFormComponent } from './shared/component/bottle/bottle-form/bottle-form.component';
import { ClientDetailsComponent } from './pages/clients/client-details/client-details.component';
import { ClientFormComponent } from './pages/clients/client-form/client-form.component';
import {DatePipe} from "@angular/common";
import {TypeWinePipe} from "./shared/pipe/type-wine.pipe";
import { Ng2SearchPipeModule } from 'ng2-search-filter';

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
    LoginComponent,
    AdminComponent,
    FormUserComponent,
    UserComponent,
    BottleDetailsComponent,
    BottleFormComponent,
    ClientDetailsComponent,
    ClientFormComponent,
    TypeWinePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'admin', component: AdminComponent},
      {path: 'stocks', component: StockComponent},
      {path: 'clients', component: ClientsComponent},
      {path: 'orders', component: OrdersComponent},
      {path: 'login', component: LoginComponent},
      {path: '', redirectTo: '/stocks', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
    ]),
    NgbModule,
    FormsModule,
    ModalModule,
    Ng2SearchPipeModule
  ],
  providers: [BottleService,
    ClientService,
    LoginService,
    DatePipe,
    { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true
  }],
  bootstrap: [AppComponent]
})

export class AppModule {
}
