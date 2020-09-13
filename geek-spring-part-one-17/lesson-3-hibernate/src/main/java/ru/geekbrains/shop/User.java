package ru.geekbrains.shop;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_c;

    @Column
    private String name_c;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> productList;

    public User() {
    }

    public User(Integer id_c, String name_c) {
        this.id_c = id_c;
        this.name_c = name_c;
    }

    public void delete() {
    }

    public Integer getId_c() {
        return id_c;
    }

    public void setId_c(Integer id_c) {
        this.id_c = id_c;
    }

    public String getName_c() {
        return name_c;
    }

    public void setName_c(String name_c) {
        this.name_c = name_c;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id_c +
                ", login='" + name_c + '\'' +
                '}';
    }
}
