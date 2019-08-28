import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse
} from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class LogoutInterceptor implements HttpInterceptor {

  constructor(private http: HttpClient) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(
        () => { },
        error => {
          console.log(error);
          if (error.status === 500 && error.error.message === 'refresh fail') {
            this.redirect();
          }
        }));
  }

  redirect() {
    window.location.href = 'http://auth.imooc.com:9090/oauth/authorize?' +
      'client_id=admin&' +
      'redirect_uri=http://admin.imooc.com:8080/oauth/callback&' +
      'response_type=code';
  }

}
