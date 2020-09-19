package ru.geekbrains.persist.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.Product;

import java.math.BigDecimal;

public class ProductsSpecifications {

    public static Specification<Product> trueLiteral(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    public static Specification<Product> priceGreaterOrEqualsThen(Integer minPrice){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }
    public static Specification<Product> priceLesserOrEqualsThan(Integer maxPrice) {
        return  (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
