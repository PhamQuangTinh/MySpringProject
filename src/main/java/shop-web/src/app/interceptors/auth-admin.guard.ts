import { TokenStorageService } from './../services/token-storage.service';
import {Injectable} from '@angular/core'
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router'
import {Observable} from 'rxjs'


const ROLE_KEY = 'role-user';
@Injectable({
    providedIn: 'root'
})
export class AuthAdminGuard implements CanActivate{

    
    constructor(
        private router: Router,
        private token: TokenStorageService,
    ){}
    canActivate(
        next: ActivatedRouteSnapshot,
        state:RouterStateSnapshot
    ):boolean{
        const user = this.token.getUser();
        var isAdmin:boolean = false;
        for(var i = 0; i < user.authorities.length; i++){
            console.log(user.authorities[i].authority);
            if(user.authorities[i].authority == "SUPER_ADMIN"){
                isAdmin = true;
                break;
            }
        }
            
        if(isAdmin){
            return true;
        }
        else{
            alert("No Permission")
            this.router.navigate['/home'];
            return false;
        }
        
    }
}