import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {AdComponent} from "./ad";
import {AdsComponent} from "./ads";
import {appRoutingModule} from "./app.routing";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BasketComponent} from "./basket";
import {UserComponent} from "./user";
import {UsersComponent} from "./users";

@NgModule({
  declarations: [
    AppComponent,
    AdComponent,
    AdsComponent,
    BasketComponent,
    UserComponent,
    UsersComponent
  ],
  imports: [
    BrowserModule,
    appRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
