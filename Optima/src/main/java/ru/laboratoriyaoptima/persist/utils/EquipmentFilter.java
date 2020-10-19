package ru.laboratoriyaoptima.persist.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.laboratoriyaoptima.persist.entity.Equipment;
import ru.laboratoriyaoptima.persist.specifications.EquipmentsSpecifications;

import java.util.Map;

public class EquipmentFilter {
    private Specification<Equipment> spec;
    private StringBuilder filterDefinition;

    public EquipmentFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("name") && !map.get("name").isEmpty()){
            String name = map.get("name");
            spec = spec.and(EquipmentsSpecifications.likeTitle(name));
            filterDefinition.append("&name=").append(name);
        }
//        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
//            int minPrice = Integer.parseInt(map.get("min_price"));
//            spec = spec.and(JournalsSpecifications.priceGreaterOrEqualsThen(minPrice));
//            filterDefinition.append("&min_price=").append(minPrice);
//        }
//        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
//            int maxPrice = Integer.parseInt(map.get("max_price"));
//            spec = spec.and(JournalsSpecifications.priceLesserOrEqualsThan(maxPrice));
//            filterDefinition.append("&max_price=").append(maxPrice);
//        }
    }

    public Specification<Equipment> getSpec() {
        return spec;
    }

    public StringBuilder getFilterDefinition() {
        return filterDefinition;
    }
}
