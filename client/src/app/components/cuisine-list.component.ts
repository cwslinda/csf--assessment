import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})
export class CuisineListComponent implements OnInit {


	// TODO Task 2
	// For View 1

  restaurants: Restaurant[] = []

  constructor(private svc: RestaurantService){}

  ngOnInit(): void {
    this.svc.getCuisineList()
      .then(result => {
        this.restaurants = result
        console.log('results from components:', result)
      })
      .catch(error => {
        console.error(">>> error", error)
      })
  }

}
