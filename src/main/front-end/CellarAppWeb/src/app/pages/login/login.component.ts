import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../shared/services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  employeCredential = {username: '', password: ''};
  loading: boolean = false;
  wording: string = "Login";

  constructor(private loginService: LoginService) {
  }

  login() {
    this.wording = "Loading...";
    this.loading = true;
    this.loginService.login(this.employeCredential);
  }

  ngOnInit(): void {
  }

}
