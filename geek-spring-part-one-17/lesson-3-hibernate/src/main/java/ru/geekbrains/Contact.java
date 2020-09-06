package ru.geekbrains;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private String description;

    @ManyToOne
    private Usersss user;

    public Contact() {
    }

    public Contact(Integer id, String type, String description, Usersss user) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Usersss getUser() {
        return user;
    }

    public void setUser(Usersss user) {
        this.user = user;
    }
}
