import java.util.LinkedList;

public class SideProduct extends Product{
    protected LinkedList<Product> recipes;

    public SideProduct(String nama, int harga, LinkedList<Product> recipes) {
        super(nama, harga);
        this.recipes = recipes;
    }
}
