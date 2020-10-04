import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class TokenStorageService {
  constructor() {}

  signOut() {
    window.localStorage.clear();
  }

  public saveToken(token: string) {
    this.removeToken();
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY);
  }

  public removeToken(){
    window.localStorage.removeItem(TOKEN_KEY);
  }

  public saveUser(user: any) {
    this.removeUser();
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    return JSON.parse(localStorage.getItem(USER_KEY));
  }

  public removeUser(){
    window.localStorage.removeItem(USER_KEY);
  }

}
