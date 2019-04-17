package sideproduct;

import product.DagingAyam;
import product.SusuSapi;

import java.util.ArrayList;
import java.util.Arrays;

public class GeprekSusu extends SideProduct {
    public GeprekSusu() {
        super("Geprek Susu", 15000, new ArrayList<>(Arrays.asList(new SusuSapi(), new DagingAyam())));
    }
}
