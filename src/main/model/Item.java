package model;

public class Item {
    private int id;
    private int quantity;

    public Item(int id, int quantity) {

    }

    public int getID() {
        return this.id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int dq) {
        this.quantity += dq;
    }
}
