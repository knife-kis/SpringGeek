package ru.laboratoriyaoptima.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "inventoryNumber")
    private String inventoryNumber;

    @Column(name = "yearOfCommissioning")
    private String yearOfCommissioning;

    @Column(name = "name")
    private String name;

    @Column(name = "factoryNumber")
    private String factoryNumber;

    @Column(name = "verificationNumber")
    private String verificationNumber;

    @Column(name = "verificationDate")
    private Date verificationDate;

}
