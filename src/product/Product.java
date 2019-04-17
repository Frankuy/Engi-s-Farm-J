package product;

public class Product {
    protected String nama;
    protected int harga;

    public Product(String n, int h) { nama = n; harga = h; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public void setNama(String n) { nama = n; }
    public void setHarga(int h) { harga = h; }
    public String print() {
        return nama + " " + harga;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().getName().equals("product.Product") || obj.getClass().getSuperclass().getName().equals("product.Product")) {
            Product prod = (Product) obj;
            return nama.equals(prod.nama) && harga == prod.harga;
        }
        else {
            return false;
        }
    }
}
