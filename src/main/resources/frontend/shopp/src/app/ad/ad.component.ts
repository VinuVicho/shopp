import {Component, OnInit} from '@angular/core';
// import {Player} from "./player";
import {ActivatedRoute, Router} from "@angular/router";
// import {PlayerService} from "../player.service";
// import {Team} from "../team/team";
import {HttpErrorResponse} from "@angular/common/http";

@Component({templateUrl: 'ad.component.html'})
export class AdComponent implements OnInit {

  // player: Player | undefined;

  constructor(private route: ActivatedRoute,
              // private playerService: PlayerService,
              private router: Router) {
  }

  ngOnInit() {
    const routeParams = this.route.snapshot.paramMap;
    const adIdFromRoute = Number(routeParams.get('adId'));
    console.log(adIdFromRoute);
    // this.getPlayer(playerIdFromRoute);
  }

//  ...
}
