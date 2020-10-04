import {
  HTTP_INTERCEPTORS,
  HttpEvent,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
} from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService, private router: Router) {}
  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 401 || err.status === 403) {
      //navigate /delete cookies or whatever
      // this.router.navigateByUrl(`/login`);
      this.token.removeToken();
      this.token.removeUser();
      this.router.navigateByUrl('/home');
      // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream consumers to have to handle it as well.
      return of(err.message); // or EMPTY may be appropriate here
    }
    return throwError(err);
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let authReq = req;

    const tokenRequest = this.token.getToken();

    //Gắn token vào header
    if(tokenRequest != null){
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + tokenRequest),
      });
    }else {
      authReq = req.clone({
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer input_your_token' ),
      });
    }
    
    return next.handle(authReq).pipe(
      // tap(
      //   succ =>{},
      //   err => {
      //     if(err.status == 403 || err.status == 401){
      //       localStorage.removeItem('auth-token');
      //       this.router.navigateByUrl('/login');
      //     }
      //   },

      // ),
      catchError((x) => this.handleAuthError(x))
    );
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
];
