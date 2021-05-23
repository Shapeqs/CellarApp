import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../../environments/environment";
import {BehaviorSubject, Subject} from "rxjs";
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public static CURRENT_USER_KEY: string = 'currentUser';

  public admin: Subject<User> = new BehaviorSubject<User>(null);

  public authenticated: Subject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
  }

  login(employeCredential: { username: string, password: string; }) {
    this.http.post<User>(environment.apiUrls.login, employeCredential).subscribe(employe => {
      if (employe) {
        sessionStorage.setItem(
          LoginService.CURRENT_USER_KEY,
          btoa(employeCredential.username + ':' + employeCredential.password)
        );
        this.authenticated.next(true);
        this.admin.next(employe);
        if (employe.role === "ADMIN") {
          this.router.navigate(['/admin'])
        } else {
          this.router.navigate(['/stocks'])
        }
      } else {
        alert("Authentication raté veuillez recommencer.")
      }
    });
  }

  logout() {
    sessionStorage.removeItem(LoginService.CURRENT_USER_KEY);
    this.authenticated.next(false);
    this.admin.next(null);
    this.router.navigate(['/login']);
    console.log("Logout");
  }
}

