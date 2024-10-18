package model;

import model.databaseObjects.Ingredient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GetUserIngredients {
    private final String username;

    public GetUserIngredients(String username) {
        this.username = username;
    }

    public ArrayList<Ingredient> getIngredients() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection("jdbc:postgresql://reddwarf.cs.rit.edu:5432/p32001f", "p32001f", "eeje5EiRoo9atha3ooLo");
            Statement stmt = db.createStatement();
            String ingredientQuery = "SELECT item_id, current_quantity FROM owns WHERE username='" + username + "'";
            ResultSet ingredients = stmt.executeQuery(ingredientQuery);
            ArrayList<Ingredient> result = new ArrayList<>();
            while (ingredients.next()) {
                int quantity = ingredients.getInt("current_quantity");
                Statement getItemName = db.createStatement();
                String getItemNameSQL = "Select item_name FROM item WHERE item_id=" + ingredients.getInt("item_id");
                ResultSet itemName = getItemName.executeQuery(getItemNameSQL);
                if(itemName.next()) {
                    result.add(new Ingredient(itemName.getString("item_name"), quantity));
                }
            }
            db.close();
            return result;
        } catch (Exception e) {
            System.out.println("Error loading postgres driver.");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println("Start Chef Ingredients Query...");
        System.out.println("username: " + args[0]);
        if (args.length == 1) {
            GetUserIngredients getUserIngredients = new GetUserIngredients(args[0]);
            System.out.println(getUserIngredients.getIngredients());
        }
        System.out.println("Ingredient Query Complete.");
    }
}
