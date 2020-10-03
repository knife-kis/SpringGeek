package ru.geekbrains.persist.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.specifications.ProductsSpecifications;

import java.util.Map;

public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("title") && !map.get("title").isEmpty()){
            String title = map.get("title");
            spec = spec.and(ProductsSpecifications.likeTitle(title));
            filterDefinition.append("&title=").append(title);
        }
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            int minPrice = Integer.parseInt(map.get("min_price"));
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThen(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            int maxPrice = Integer.parseInt(map.get("max_price"));
            spec = spec.and(ProductsSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
    }

    public Specification<Product> getSpec() {
        return spec;
    }

    public StringBuilder getFilterDefinition() {
        return filterDefinition;
    }
}
