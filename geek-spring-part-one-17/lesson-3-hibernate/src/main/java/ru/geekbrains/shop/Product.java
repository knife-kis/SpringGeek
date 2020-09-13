package ru.geekbrains.shop;

import javax.persistence.*;

@Entity
@Table(name = "product_tb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_c;

    @Column
    private String title_c;

    @Column
    private Double cost_c;

    @ManyToOne
    private User user;

    public Product() {
    }

    public Product(Integer id_c, String title_c, Double cost_c, User user) {
        this.id_c = id_c;
        this.title_c = title_c;
        this.cost_c = cost_c;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId_c() {
        return id_c;
    }

    public void setId_c(Integer id_c) {
        this.id_c = id_c;
    }

    public String getTitle_c() {
        return title_c;
    }

    public void setTitle_c(String title_c) {
        this.title_c = title_c;
    }

    public Double getCost_c() {
        return cost_c;
    }

    public void setCost_c(Double cost_c) {
        this.cost_c = cost_c;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_c=" + id_c +
                ", title_c='" + title_c + '\'' +
                ", cost_c=" + cost_c +
                ", user=" + user +
                '}';
    }
}
