import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';

import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { LogoutInterceptor } from './app.interceptor';

import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CookieService,
    { provide: HTTP_INTERCEPTORS, useClass: LogoutInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
