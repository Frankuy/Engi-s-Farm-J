import java.util.Arrays;
import java.util.LinkedList;

public class GeprekSusu extends SideProduct {
    public GeprekSusu() {
        super("Geprek Susu", 15000, new LinkedList<Product>(Arrays.asList(new SusuSapi(), new DagingAyam())));
    }
}
