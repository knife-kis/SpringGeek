package ru.geekbrains.shop;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

public class Repository <T> {

    private EntityManagerFactory emFactory;
    private Class<T> type;

    public Repository(Class<T> type) {

        this.type = type;
    }

    public Repository() {

    }

    public T add(T temp){
       return null;
    }
}
