import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../../services/token-storage.service'
import { HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  message: string;
  success: boolean = true;

  username:any = 'quangtinh';
  password:any = '123456';
  constructor(
    private loginService: LoginService,
    private router: Router,
    private tokenService: TokenStorageService
  ) { }

  ngOnInit(): void {
  }

  login(){
    this.loginService.login(this.username,this.password).subscribe(
      res=>{
        this.success = true;
        this.tokenService.saveToken(res.headers.get('token'))
        this.router.navigateByUrl('/shop')
    
      },err =>{
        this.message = err;
        this.success = false;
      }
    )
  }


}