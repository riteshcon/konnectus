import { Component } from '@angular/core';
import { LoginService } from './login/login.service';
import { TokenRequest } from './login/TokenRequest';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'konnectui';

  constructor(private loginService: LoginService) { }



  signIn(email:string, password:string){
    const tokenRequest: TokenRequest = new TokenRequest(email, password, "password" );
    this.loginService.getToken(tokenRequest);
  }
}
