import { Routes, RouterModule } from '@angular/router';

import { AdComponent } from './ad';
import { AdsComponent } from './ads';
import {BasketComponent} from "./basket";
import {UserComponent} from "./user";
import {UsersComponent} from "./users";

const routes: Routes = [
  { path: '', component: AdsComponent },
  { path: 'basket', component: BasketComponent },
  { path: 'ad/:adId', component: AdComponent },
  { path: 'users', component: UsersComponent },
  { path: 'user/:userId', component: UserComponent },

  // if nothing of above = redirect to main page (teams)
  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
