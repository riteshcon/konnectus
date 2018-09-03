import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { TokenRequest } from './TokenRequest';
import { HttpErrorHandler, HandleError } from '../http-error-handler.service';
import { map } from 'rxjs/operators';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HttpModule } from '@angular/http';


  @Injectable({
    providedIn: 'root',
  })
export class LoginService {
    authurl = 'http://localhost:8080/oauth/token';  // URL to web api


  constructor(private http: Http, private router: Router) { }

  url: string;
  headers: Headers;
  options: RequestOptions;
  creds: String;


 
  
  

getToken(tokenRequest: TokenRequest){ 
    this.url = "http://localhost:8080/oauth/token";
    this.headers = new Headers({
      "Content-Type": "application/x-www-form-urlencoded",
      "Authorization": "Basic a29uOmtvbg=="
    });
    this.options = new RequestOptions({ headers: this.headers });
    this.creds = 'username='+tokenRequest.username+'&password='+tokenRequest.password+'&grant_type=password';
    this.http.post(this.url, this.creds, this.options).pipe(map(res => res.json())).subscribe(response => {
        localStorage.setItem('currentUser', JSON.stringify({userName:tokenRequest.username, token: response.access_token }));
        this.router.navigateByUrl("/group");
      }, (error) => {
        console.log('error in', error);
      });
  
}

}
 