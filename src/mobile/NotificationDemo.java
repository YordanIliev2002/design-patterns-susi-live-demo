package mobile;

import mobile.listings.Listing;
import mobile.listings.ListingService;
import mobile.listings.ListingStorage;
import mobile.vehicles.Car;

public class NotificationDemo {
    public static void main(String[] args) {
        ListingService listingService = new ListingService(new ListingStorage());

        // subscription logika

        listingService.addListing(new Listing(
            "Hubava Toyota za teb",
            15000,
            new Car(
                "Toyota",
                "Corolla",
                2021,
                true
            )
        ));
    }
}
