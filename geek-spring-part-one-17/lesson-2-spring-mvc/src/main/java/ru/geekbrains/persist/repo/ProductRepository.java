package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.entity.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findAllByCostIsLessThan(int cost);
    List<Product> findAllByCostGreaterThan(int cost);

    List<Product> findByTitleLike(String titlePattern);

//    Page<Product> findByPage(int pageNumber, int pageSize);
}
