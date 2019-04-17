package sideproduct;

import product.DagingAyam;
import product.DagingSapi;

import java.util.ArrayList;
import java.util.Arrays;

public class PaketSate extends SideProduct {
    public PaketSate() {
        super("Paket Sate", 20000, new ArrayList<>(Arrays.asList(new DagingAyam(), new DagingSapi())));
    }
}

