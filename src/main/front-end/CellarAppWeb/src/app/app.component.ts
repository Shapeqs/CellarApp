import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginService} from "./shared/services/login.service";
import {User} from "./shared/models/user.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'C\' du Vin';

  authenticated: boolean;
  today: Date = new Date();
  admin: User;

  constructor(public loginService: LoginService, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loginService.authenticated.subscribe(
      auth => this.authenticated = auth
    );
    this.loginService.admin.subscribe(
      auth => this.admin = auth
    );
  }


  logout() {
    this.loginService.logout();
    this.router.navigateByUrl('/');
  }
}
