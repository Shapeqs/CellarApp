import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../../environments/environment";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public static CURRENT_USER_KEY: string = 'currentUser';

  public authenticated:Subject<boolean> = new BehaviorSubject<boolean>(false);


  constructor(private http: HttpClient, private router: Router) {}

  login(employeCredential: { username: string, password: string; }) {
    this.http.post(environment.apiUrls.login, employeCredential).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          LoginService.CURRENT_USER_KEY,
          btoa(employeCredential.username + ':' + employeCredential.password)
        );
        this.authenticated.next(true);
        this.router.navigate(['/admin']);
      } else {
        alert("Authentication failed.")
      }
    });
  }

  logout() {
    sessionStorage.removeItem(LoginService.CURRENT_USER_KEY);
    this.authenticated.next(false);
    this.router.navigate(['/login']);
    console.log("Logout");
  }
}
