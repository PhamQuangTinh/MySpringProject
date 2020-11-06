import { userUrl } from './../../../config/api';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs'

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
    providedIn: 'root'
})
export class RegisterService {

    constructor(private http : HttpClient){

    }

    register(user) : Observable<any>{
        console.log(user);
        return this.http.post(userUrl + '/post/register',user)
    }

    errorHandler(error: HttpErrorResponse){
        return throwError(error.error.error.body.message || 'Server Error')
    }
}
