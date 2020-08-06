import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs'


@Injectable({
    providedIn: 'root'
})
export class LoginService {
    

    constructor(private http : HttpClient){

    }

    login(username,password) : Observable<HttpResponse<Object>>{
        let contentHeader = new HttpHeaders({ "Content-Type": "application/json" });
        return this.http.post('http://localhost:8080/api/user/post/login',
        {
            username: username,
            password: password            
        },
        {
            headers: contentHeader,
            observe:'response'
        })
        .pipe(catchError(this.errorHandler));
    }

    errorHandler(error: HttpErrorResponse){
        return throwError(error.error.error.body.message || 'Server Error')
    }
}
