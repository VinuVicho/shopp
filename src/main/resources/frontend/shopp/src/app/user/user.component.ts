import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({templateUrl: 'user.component.html'})
export class UserComponent implements OnInit {


  constructor(private route: ActivatedRoute,
              // private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit() {
    const routeParams = this.route.snapshot.paramMap;
    const adIdFromRoute = Number(routeParams.get('userId'));
    console.log(adIdFromRoute);
    // this.getPlayer(playerIdFromRoute);
  }


}
