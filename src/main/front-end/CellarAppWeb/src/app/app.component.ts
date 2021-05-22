import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginService} from "./shared/services/login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'C\' du Vin';

  authenticated: boolean;
  today: Date = new Date();

  constructor(public loginService: LoginService, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loginService.authenticated.subscribe(
      auth => this.authenticated = auth
    );
  }


  logout() {
    this.loginService.logout();
    this.router.navigateByUrl('/');
  }
}
