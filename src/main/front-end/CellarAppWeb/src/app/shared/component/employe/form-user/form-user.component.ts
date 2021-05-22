import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../models/user.model";
import {UserService} from "../../../services/user.service";
import {LoginService} from "../../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-form-admin',
  templateUrl: './form-user.component.html',
  styleUrls: ['./form-user.component.scss']
})
export class FormUserComponent implements OnInit {
  heading: string;
  loading: boolean = false;
  wording:string;

  private authenticated: boolean;
  private admin:User;

  @Input() user:User;

  constructor(private userService: UserService, private loginService: LoginService, private router: Router) {
  }

  ngOnInit(): void {
    if(null != this.user) {
      this.wording = "Modifier";
    } else {
      this.wording = "Créer";
    }
    this.loginService.authenticated.subscribe(
      auth => this.authenticated = auth
    );
    this.loginService.admin.subscribe(
      admin => this.admin = admin
    );
    if (!this.authenticated) {
      this.router.navigateByUrl('/');
    }
  }

  submit() {
    return false;
  }
}
