import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { RefreshInterceptor } from './app.interceptor';

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
    { provide: HTTP_INTERCEPTORS, useClass: RefreshInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
