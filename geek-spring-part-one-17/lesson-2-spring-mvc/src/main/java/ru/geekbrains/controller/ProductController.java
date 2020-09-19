package ru.geekbrains.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;

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
                             @RequestParam(value = "max_price", required = false) Integer maxPrice,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> sizePage)
    {
        logger.info("Filtering by title: {} minPrice: {} maxPrice: {}", title, minPrice, maxPrice);

        PageRequest pageRequest = PageRequest.of(page.orElse(1) - 1, sizePage.orElse(5));

        Specification<Product> spec = ProductsSpecifications.trueLiteral();

        if (title != null && !title.isEmpty()){
            spec = spec.and(ProductsSpecifications.likeTitle(title));
        }
        if (minPrice != null){
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThen(minPrice));
        }
        if (maxPrice != null){
            spec = spec.and(ProductsSpecifications.priceLesserOrEqualsThan(maxPrice));
        }

        model.addAttribute("productsPage", productRepository.findAll(spec, pageRequest));
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
