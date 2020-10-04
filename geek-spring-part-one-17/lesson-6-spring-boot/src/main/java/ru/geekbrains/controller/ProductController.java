package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.entity.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.services.ProductsService;
import ru.geekbrains.persist.specifications.ProductsSpecifications;
import ru.geekbrains.persist.utils.ProductFilter;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);
    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    public String allProucts(Model model,
                             @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("page", "1"));
        ProductFilter productFilter = new ProductFilter(requestParams);

        Page<Product> productsPage = productsService.findAll(productFilter.getSpec(), pageNumber);
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("filterDef", productFilter.getFilterDefinition().toString());
        return "products";
    }

    @GetMapping("/{id}")
    public String editProducts(@PathVariable("id") Integer id, Model model) {
        Product product = productsService.findById(id);
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

        productsService.saveOrUpdate(product);
        return "redirect:/product";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productsService.deleteProduct(productsService.findById(id));
        return "redirect:/product";
    }
}
