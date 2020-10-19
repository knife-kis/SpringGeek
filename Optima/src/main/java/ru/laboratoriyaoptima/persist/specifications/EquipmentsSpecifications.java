package ru.laboratoriyaoptima.persist.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.laboratoriyaoptima.persist.entity.Equipment;

public class EquipmentsSpecifications {

    public static Specification<Equipment> trueLiteral(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    public static Specification<Equipment> likeTitle(String name){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

//    public static Specification<Journal> priceGreaterOrEqualsThen(Integer minPrice){
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
//    }
//    public static Specification<Journal> priceLesserOrEqualsThan(Integer maxPrice) {
//        return  (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
//    }
}
