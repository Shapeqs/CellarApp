import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {User} from "../../shared/models/user.model";
import {UserService} from "../../shared/services/user.service";
import {LoginService} from "../../shared/services/login.service";
import {Router} from "@angular/router";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DatePipe} from "@angular/common";
import {NgForm} from "@angular/forms";

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
  editOldUser: User;
  editNewUser: User;
  newUser: User;
  closeResult = '';
  alertModifUsersClosed: boolean;
  alertUsernameAlreadyExistClosed: boolean;
  alertBirthdayClosed: boolean;

  constructor(private userService: UserService,
              private loginService: LoginService,
              private router: Router,
              private modalService: NgbModal,
              public datepipe: DatePipe) {
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
    this.alertModifUsersClosed = true;
    this.alertUsernameAlreadyExistClosed = true;
    this.alertBirthdayClosed = true;
  }

  openDeleteModal(content, user:User) {
    this.deleteUser = user;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title',
    backdropClass: 'light-blue-backdrop'}).result.then((result) => {
      if (result === 'Remove')  {
        this.onDeleteUser();
      }
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  openAddModal(content){
    this.newUser = new User();
    this.newUser.role = 'EMPLOYE';
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title',
      backdropClass : 'light-blue-backdrop'}).result.then((result) => {
      if (result === 'Add') {
        this.onAddUser();
      }
      this.closeResult = 'Close with: ' + result;
    }, reason => {
      this.closeResult = 'Dismissed ' + this.getDismissReason(reason);
    })
  }

  openEditModal(content, user:User){
    this.editOldUser = user;
    this.editNewUser = user;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title',
      backdropClass : 'light-blue-backdrop'}).result.then((result) => {
      this.closeResult = 'Close with: ' + result;
    }, reason => {
      this.closeResult = 'Dismissed ' + this.getDismissReason(reason);
    })
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

  public onDeleteUser(): void {
    this.userService.deleteOne(this.deleteUser).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
        this.alertModifUsersClosed = false;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddUser() {
    if (this.birthdayValid(this.newUser) && !this.usernameExist(this.newUser, false)) {
      this.userService.addNew(this.newUser).subscribe(
        (response: User) => {
          this.getUsers();
          this.alertModifUsersClosed = false;
          this.resetNewUserData();
        }, (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    } else if (!this.birthdayValid(this.newUser)) {
      this.alertBirthdayClosed = false;
    } else {
      this.alertUsernameAlreadyExistClosed = false;
    }
  }

  private usernameExist(user:User, usernameEdited:boolean) {
    let exist: boolean = false;
    if(!usernameEdited) {
      this.users.forEach(u => {
        if (u.username === user.username) {
          exist = true;
        }
      });
    }
    return exist;
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

  private birthdayValid(user:User) {
    let date:string =this.datepipe.transform(user.birthday, 'yyyy-MM-dd');
    let d = new Date(date);
    return (d.getFullYear() >= 1900 && d.getFullYear() <= 2010);
  }

  editUser(formEdit: NgForm) {
    this.editNewUser.name = formEdit.value.name;
    this.editNewUser.firstname = formEdit.value.firstname;
    this.editNewUser.username = formEdit.value.username;
    this.editNewUser.birthday = formEdit.value.birthday;
    this.editNewUser.password = formEdit.value.password;
    this.editNewUser.role = formEdit.value.role;
    if (!this.usernameExist(this.editNewUser, this.editNewUser.username===this.editOldUser.username)) {
      this.userService.modifyOne(this.editNewUser).subscribe(
        (response: User) => {
          this.getUsers();
          this.alertModifUsersClosed = false;
        }, (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    } else if(this.usernameExist(this.editNewUser, this.editNewUser.username === this.editOldUser.username)) {
      this.alertUsernameAlreadyExistClosed = false;
    }
  }
}
