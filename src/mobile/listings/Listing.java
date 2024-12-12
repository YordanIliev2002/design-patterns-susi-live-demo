package mobile.listings;

import mobile.vehicles.Car;

public class Listing {
    private String title;
    private int price;
    private Car car;

    public Listing(String title, int price, Car car) {
        this.title = title;
        this.price = price;
        this.car = car;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
