package sideproduct;

import product.DagingSapi;
import product.SusuSapi;

import java.util.ArrayList;
import java.util.Arrays;

public class PaketSteak extends SideProduct {
    public PaketSteak() {
        super("Paket Steak", 20000, new ArrayList<>(Arrays.asList(new DagingSapi(), new SusuSapi())));
    }
}
