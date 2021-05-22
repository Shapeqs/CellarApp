import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {User} from "../../shared/models/user.model";
import {UserService} from "../../shared/services/user.service";
import {LoginService} from "../../shared/services/login.service";
import {Router} from "@angular/router";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  users: User[];
  authenticated: boolean;
  admin: User;
  deleteUser: User;
  editUser: User;
  newUser: User;
  closeResult = '';

  constructor(private userService: UserService,
              private loginService: LoginService,
              private router: Router,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getUsers();
    this.loginService.authenticated.subscribe(
      auth => this.authenticated = auth
    );
    this.loginService.admin.subscribe(
      auth => this.admin = auth
    );
    if (!this.authenticated) {
      this.router.navigateByUrl('/');
    }
    this.resetNewUserData();
  }

  openDeleteModal(content, user:User) {
    this.deleteUser = user;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      if (result === 'Remove')  {
        this.onDeleteUser(user);
      }
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  public onDeleteUser(user: User): void {
    this.userService.deleteOne(user).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  getUsers() {
    this.userService.getAll().subscribe(users => {
        this.users = users;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  resetNewUserData() {
    this.newUser = new User();
    this.newUser.name = "";
    this.newUser.firstname = "";
    this.newUser.birthday = new Date();
    this.newUser.role = "";
    this.newUser.username = "";
    this.newUser.password = "";
  }
}
