import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent implements OnInit {
  
  param$!: Subscription
  form!: FormGroup
  comment!: Comment
  name!: string
  cuisine!: string

  constructor(private fb: FormBuilder, private ar: ActivatedRoute, private svc: RestaurantService){ }

  ngOnInit(): void {
    this.param$ = this.ar.params.subscribe(
      (params) => {
        this.name = params['name'] as string
        console.log("name" + this.name)
        this.svc.getRestaurant(this.name).then(
          results => {
           
          
          }
        ).catch(error => console.log(error))
      }
    )
    
    this.form = this.createForm()
  }

  processForm(){
    this.form = this.form.value 
    console.log(this.form)
  }

  createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>("", [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control<number>(0, [Validators.min(1), Validators.max(5)]),
      text: this.fb.control<string>("", [Validators.required])

      
    })
  }
	
	// TODO Task 4 and Task 5
	// For View 3

}
