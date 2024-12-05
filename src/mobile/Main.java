package mobile;

import mobile.search.CaseInsensitiveFilter;
import mobile.search.ExactValueFilter;
import mobile.search.Filter;
import mobile.search.RangeFilter;
import mobile.vehicles.Car;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla", 2021, true);
        Car car2 = new Car("Bmw", "e60", 2000, false);

        // всичките хиляди коли които имаме
        List<Car> cars = List.of(car, car2);

        List<Filter<Car>> filters = List.of(
            new ExactValueFilter<>(c -> c.brand(), "Toyota"),
            new CaseInsensitiveFilter<>(c -> c.model(), "Corolla"),
            new RangeFilter<>(c -> c.year(), 2000, 2022),
            new RangeFilter<>(c -> c.brand(), "Bmw", "Toyota")
        );

        // само колите от филтрите
        List<Car> matchingCars = filterCars(cars, filters);
        System.out.println("Matching cars:");
        matchingCars.forEach(System.out::println);
    }

    // не му е мястото тук, само за демонстративни цели е
    private static List<Car> filterCars(List<Car> cars, List<Filter<Car>> filters) {
        return cars.stream()
            .filter(car -> filters.stream().allMatch(filter -> filter.matches(car)))
            .toList();
    }
}
