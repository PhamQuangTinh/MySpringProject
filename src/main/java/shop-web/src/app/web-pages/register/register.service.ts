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
        return this.http.post('http://localhost:8080/api/user/post/register',user,httpOptions)
                        .pipe(catchError(this.errorHandler));
    }

    errorHandler(error: HttpErrorResponse){
        return throwError(error.error.error.body.message || 'Server Error')
    }
}
