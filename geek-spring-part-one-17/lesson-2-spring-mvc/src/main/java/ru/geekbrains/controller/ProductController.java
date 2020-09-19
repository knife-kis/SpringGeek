package ru.geekbrains.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.specifications.ProductsSpecifications;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String allProucts(Model model,
                             @RequestParam(value = "title", required = false) String title,
                             @RequestParam(value = "min_price", required = false) Integer minPrice,
                             @RequestParam(value = "max_price", required = false) Integer maxPrice)
    {
        logger.info("Filtering by title: {} minPrice: {} maxPrice: {}", title, minPrice, maxPrice);

        Specification<Product> spec = ProductsSpecifications.trueLiteral();
        if (minPrice != null){
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThen(minPrice));
        }
        if (maxPrice != null){
            spec = spec.and(ProductsSpecifications.priceLesserOrEqualsThan(maxPrice));
        }

        model.addAttribute("products", productRepository.findAll(spec));
        return "products";
    }

    @GetMapping("/{id}")
    public String editProducts(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product";
        }

        // TODO реализовать проверку повторного ввода пароля.
        // TODO Использовать метод bindingResult.rejectValue();

        productRepository.save(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }
}
