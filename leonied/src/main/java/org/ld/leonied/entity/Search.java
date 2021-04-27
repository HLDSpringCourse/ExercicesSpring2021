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
    /*private final String cityLat;
    private final String cityLong;*/

    private Search(SearchBuilder builder) {
        name = builder.name;
        cityName = builder.cityName;
        /*cityLat = builder.cityLat;
        cityLong = builder.cityLong;*/
    }

    public List<Order> result(List<Order> orders) {
        return orders.stream()
                .filter(predicateName()
                        .and(predicateCityName())
                )
                .collect(Collectors.toList());
    }

    public Predicate<Order> predicateName() {
        return order -> order.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT));
    }

    public Predicate<Order> predicateCityName() {
        return order -> order.getCity() != null && order.getCity().toLowerCase(Locale.ROOT).contains(cityName.toLowerCase(Locale.ROOT));
    }

    // Builder Class
    public static class SearchBuilder {
        // optional
        private String name;
        private String cityName;
        /*private String cityLat;
        private String cityLong;*/


        public SearchBuilder() {
            name = "";
            cityName = "";
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

        public Search build() {
            Search search =  new Search(this);
            return search;
        }
    }
}
