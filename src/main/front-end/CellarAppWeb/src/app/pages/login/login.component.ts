import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../shared/services/login.service";
import {User} from "../../shared/models/user.model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  employeCredential = {username: '', password: ''};
  wording: string = "Login";
  authenticated: boolean;
  admin: User;

  constructor(private loginService: LoginService) {
  }

  login() {
    this.loginService.login(this.employeCredential);
    if(this.authenticated) {
      this.wording = "Loading...";
    }
  }

  ngOnInit(): void {
    this.loginService.authenticated.subscribe(
      auth => this.authenticated = auth
    );
    this.loginService.admin.subscribe(
      admin => this.admin = admin
    );
  }

}
