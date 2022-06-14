package entity;

public class Assignment {
    private Route route;
    protected int quantity;

    public Assignment() {
    }

    public Assignment(Route route, int quantity) {
        this.route = route;
        this.quantity = quantity;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "route=" + route +
                ", quantity=" + quantity +
                '}';
    }
}
