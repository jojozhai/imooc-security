import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  constructor(private http: HttpClient) {
    this.http.get('me').subscribe(data => {
      if (data) {
        this.authenticated = true;
      }
      if (!this.authenticated) {
        window.location.href = 'http://auth.imooc.com:9090/oauth/authorize?' +
          'client_id=admin&' +
          'redirect_uri=http://admin.imooc.com:8080/oauth/callback&' +
          'response_type=code';
      }
    });
  }

  getOrder() {
    this.http.get('api/order/orders/1').subscribe(data => {
      this.order = data;
    });
  }

  logout() {
    this.http.post('logout', this.credentials).subscribe(() => {
      window.location.href = 'http://auth.imooc.com:9090/logout?redirect_uri=http://admin.imooc.com:8080';
    }, () => {
      alert('logout fail');
    });
  }

}
