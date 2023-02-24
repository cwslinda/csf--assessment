package vttp2022.csf.assessment.server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;

@Controller
@RequestMapping("/api")
public class RestaurantController {


    @Autowired
    private RestaurantService svc;

    @GetMapping(path = "/cuisines", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCuisines(){

        List<String> restaurants = svc.getCuisines();

        Gson objGson = new Gson();
        // System.out.println(objGson.toJson(restaurants));
   
        
        return ResponseEntity.ok(objGson.toJson(restaurants));

    }

    @GetMapping(path = "{cuisine}/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getRestaurantByCuisine(@PathVariable String cuisine){
         
        Optional<List<Restaurant>> opt = svc.getRestaurantsByCuisine(cuisine);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body(
                Json.createObjectBuilder()
                    .add("error", "Could not find restaurants")
                    .build().toString()
            );
        }

            List<Restaurant> restaurants = opt.get();
            //System.out.println("controller > " + restaurants);

            JsonArrayBuilder jab = Json.createArrayBuilder();
            for (Restaurant r: restaurants) {
                JsonObject json = r.toJson();
                jab.add(json);
            }
            JsonArray ja = jab.build();
    
            return ResponseEntity.status(200).body(ja.toString());
        }
    }

