package org.ld.leonied.entity;
import org.ld.leonied.NotFoundException;
import org.ld.leonied.service.OrderService;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Search {
    // optional
    private final String name;
    private final String cityName;
    private final Integer cityLat;
    private final Integer cityLong;

    private Search(SearchBuilder builder) {
        name = builder.name;
        cityName = builder.cityName;
        cityLat = builder.cityLat;
        cityLong = builder.cityLong;
    }

    public List<Order> result(List<Order> orders) {
        return orders.stream()
                .filter(predicateName()
                        .and(predicateCityName())
                        .and(predicateCityLat())
                        .and(predicateCityLong())
                )
                .collect(Collectors.toList());
    }

    public Predicate<Order> predicateName() {
        return order -> order.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT));
    }

    public Predicate<Order> predicateCityName() {
        return order -> order.getCity() != null && order.getCity().toLowerCase(Locale.ROOT).contains(cityName.toLowerCase(Locale.ROOT));
    }

    public Predicate<Order> predicateCityLat() {
        return order -> cityLat == null || order.getLattitude() == cityLat;
    }

    public Predicate<Order> predicateCityLong() {
        return order -> cityLong == null || order.getLattitude() == cityLong;
    }

    // Builder Class
    public static class SearchBuilder {
        // optional
        private String name;
        private String cityName;
        private Integer cityLat;
        private Integer cityLong;


        public SearchBuilder() {
            name = "";
            cityName = "";
            cityLat = null;
            cityLong = null;
        }

        /**
         * @param name
         * @return lui-même
         */
        public SearchBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * @param cityName
         * @return lui-même
         */
        public SearchBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        /**
         * @param cityLat
         * @return lui-même
         */
        public SearchBuilder cityLat(Integer cityLat) {
            this.cityLat = cityLat;
            return this;
        }

        /**
         * @param cityLong
         * @return lui-même
         */
        public SearchBuilder cityLong(Integer cityLong) {
            this.cityLong = cityLong;
            return this;
        }

        public Search build() {
            Search search =  new Search(this);
            return search;
        }
    }
}
