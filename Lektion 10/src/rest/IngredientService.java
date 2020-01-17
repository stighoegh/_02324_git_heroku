package rest;

import data.IngredientDAO;
import data.IngredientDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import java.util.List;

@Path("ingredient")
public class IngredientService {

    @GET
    public String getIngredientList() {
        List<IngredientDTO> list = IngredientDAO.getInstance().getIngredientList();
        return new JSONArray(list).toString();
    }

    @GET
    @Path("{id}")
    /*Eksempel på HTTP kald: GET localhost:8080/Lektion10/rest/ingredient/3 */
    public String getIngredient(@PathParam("id") int id) {
        String returnString;
        IngredientDTO ingredient = IngredientDAO.getInstance().getIngredient(id);
        if (ingredient == null)
            returnString = "Ingredient with ID " + id + " does not exist";
        else
            returnString = new JSONObject(ingredient).toString();

        return returnString;
    }

    @POST
    @Path("form")
    /*Variablerne tages fra HTTP body med encoding x-www-form-urlencoded */
    public String addIngredientForm(@FormParam("id") String id, @FormParam("name") String name, @FormParam("amount") String amount) {
        IngredientDTO ingredient = new IngredientDTO(Integer.parseInt(id), name, Double.parseDouble(amount));
        IngredientDAO.getInstance().addIngredient(ingredient);

        return "Ingredient added";
    }

    @POST
    @Path("query")
    /*Variablerne tages fra URL'en
    * Eksempel på HTTP kald: POST localhost:8080/Lektion10/rest/ingredient/query?id=4&name=sukker&amount=45 */
    public String addIngredientQuery(@QueryParam("id") String id, @QueryParam("name") String name, @QueryParam("amount") String amount) {
        IngredientDTO ingredient = new IngredientDTO(Integer.parseInt(id), name, Double.parseDouble(amount));
        IngredientDAO.getInstance().addIngredient(ingredient);

        return "Ingredient added";
    }

    @POST
    @Path("{id}/{name}/{amount}")
    /*Variablerne tages fra URL'en
    * Eksempel på HTTP kald: POST localhost:8080/Lektion10/rest/ingredient/4/sukker/45 */
    public String addIngredientPath(@PathParam("id") String id, @PathParam("name") String name, @PathParam("amount") String amount) {
        IngredientDTO ingredient = new IngredientDTO(Integer.parseInt(id), name, Double.parseDouble(amount));
        IngredientDAO.getInstance().addIngredient(ingredient);

        return "Ingredient added";
    }

}
