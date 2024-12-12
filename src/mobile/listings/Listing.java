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

    public ListingSnapshot createSnapshot() {
        return new ListingSnapshot(title, price, car);
    }

    public void restore(ListingSnapshot snapshot) {
        title = snapshot.title;
        price = snapshot.price;
        car = snapshot.car;
    }

    public static class ListingSnapshot {
        private final String title;
        private final int price;
        private final Car car;

        private ListingSnapshot(String title, int price, Car car) {
            this.title = title;
            this.price = price;
            this.car = car;
        }
    }
}
