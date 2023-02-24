import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit {
	
	// TODO Task 3
	// For View 2

  param$!: Subscription
  restaurants: Restaurant[] = []
  cuisine!: string

  constructor(private ar: ActivatedRoute, private svc: RestaurantService) {}
  
  ngOnInit(): void {
    this.param$ = this.ar.params.subscribe(
      (params) => {
        this.cuisine = params['cuisine'] as string
        console.log("cuisine" + this.cuisine)
        this.svc.getRestaurantsByCuisine(this.cuisine).then(
          results => {
           
            this.restaurants = results as unknown as Restaurant[]
            console.log(this.restaurants)
          }
        ).catch(error => console.log(error))
      }
    )
  }

}
