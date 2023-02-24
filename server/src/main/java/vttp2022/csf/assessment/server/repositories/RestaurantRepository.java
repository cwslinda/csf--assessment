package vttp2022.csf.assessment.server.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.csf.assessment.server.models.Restaurant;

@Repository
public class RestaurantRepository {

	@Autowired
    private MongoTemplate template;

	

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	//db.getCollection("restaurants").distinct("cuisine")
	public List<String> getCuisines() {
		List<String> cuisines = template.findDistinct(new Query() , "cuisine", "restaurants", String.class); 
		//System.out.println("cuisine >> " + cuisines);

			return cuisines;


		}
		

	

	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	// db.getCollection("restaurants").find({"cuisine":"Irish"})
	public Optional<List<Restaurant>> getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here
		//System.out.println("cusine >>>" + cuisine);
		List<Document> result = template.find(Query.query(Criteria.where("cuisine").is(cuisine)), Document.class, "restaurants");
		

		
		if (result.isEmpty()) return Optional.empty();

        List<Restaurant> restaurants = new LinkedList<>();
        for (Document d : result) {
            Restaurant r = Restaurant.create(d);
            restaurants.add(r);
        }
		//System.out.println("repo" + restaurants);

        return Optional.of(restaurants);
    }
    

	// TODO Task 4
	// Use this method to find a specific restaurant
	// You can add any parameters (if any) 
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	// db.getCollection("restaurants").find({"name":"Irish"})
	// public Optional<Restaurant> getRestaurant(String name) {
		// Implmementation in here


		
	//}

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	// public void addComment(Comment comment) {
	// 	// Implmementation in here
		
	// }
	
	// You may add other methods to this class



}