package mobile.listings;

public class ListingService {
    private final ListingStorage listingStorage;

    public ListingService(ListingStorage listingStorage) {
        this.listingStorage = listingStorage;
    }

    public void addListing(Listing listing) {
        listingStorage.addListing(listing);
    }
}
