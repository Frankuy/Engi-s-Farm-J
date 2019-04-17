package sideproduct;

import product.Product;
import tools.Renderable;

import java.util.ArrayList;

public class SideProduct extends Product {
    private ArrayList<Product> recipes;

    public SideProduct(String nama, int harga, ArrayList<Product> recipes) {
        super(nama, harga);
        this.setRecipes(recipes);
    }

    public ArrayList<Product> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Product> recipes) {
        this.recipes = recipes;
    }

    public String listResep() {
        String resep = "";
        for (Product prod : recipes) {
            resep += "- " + prod.getNama() + "\n ";
        }
        return resep;
    }
}
