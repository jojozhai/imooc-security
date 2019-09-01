import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'imooc microservice security';
  authenticated = false;
  credentials = { username: 'xixi', password: '123' };
  order = {};

  constructor(private http: HttpClient, private cookieService: CookieService) {
    this.http.get('api/user/me').subscribe(data => {
      if (data) {
        this.authenticated = true;
      }
      if (!this.authenticated) {
        window.location.href = 'http://auth.imooc.com:9090/oauth/authorize?' +
          'client_id=admin&' +
          'redirect_uri=http://admin.imooc.com:8080/oauth/callback&' +
          'response_type=code&' +
          'state=abc';
      }
    });
  }

  getOrder() {
    this.http.get('api/order/orders/1').subscribe(data => {
      this.order = data;
    }, () => {
      alert('get order fail');
    });
  }

  logout() {
    this.cookieService.delete('imooc_access_token', '/', 'imooc.com');
    this.cookieService.delete('imooc_refresh_token', '/', 'imooc.com');
    this.http.post('logout', this.credentials).subscribe(() => {
      window.location.href = 'http://auth.imooc.com:9090/logout?redirect_uri=http://admin.imooc.com:8080';
    }, () => {
      alert('logout fail');
    });
  }

  login() {
    this.http.post('login', this.credentials).subscribe(() => {
      this.authenticated = true;
    }, () => {
      alert('login fail');
    });
  }

}
