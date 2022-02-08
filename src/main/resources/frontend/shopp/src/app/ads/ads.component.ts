import {Component, OnInit} from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";

@Component({templateUrl: 'ads.component.html'})
export class AdsComponent implements OnInit {
  // public players: Player[] = [];

  // constructor(private playerService: PlayerService) {
  // }

  ngOnInit(): void {
    // this.getPlayers();
  }

  // public getPlayers(): void {
  //   this.playerService.getPlayers().subscribe(
  //     (response: Player[]) => {
  //       this.players = response;
  //       console.log(this.players);
  //     },
  //     (error: HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }
}
