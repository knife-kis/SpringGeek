package ru.geekbrains.shop;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        //написать тестовое консольное приложение,
        //которое позволит посмотреть, какие товары покупал клиент
//
        List<Product> products = listProductsUserBuys(2);
        System.out.println(products);
//
//        //какие клиенты купили определенный товар
//
        List<Product> users = listUserBuysProduct(3);
        System.out.println(users);
//
//        //предоставит возможность удалять из базы товары/покупателей
//
        deleteUser(3);
        deleteProduct(3);

        //Добавить детализацию по паре «покупатель — товар»:
        // сколько стоил товар в момент покупки клиентом


    }


    private static List<Product> listProductsUserBuys(int idUser) {
        EntityManager em = createManager();
        List<Product> product = em.createQuery("from Product where user_id_c = :user_id_c", Product.class)
                .setParameter("user_id_c", idUser)
                .getResultList();
        em.close();
        return product;
    }

    private static List<Product> listUserBuysProduct(int idProduct) {
        EntityManager em = createManager();
        List<Product> list = em.createQuery("from Product where id_c = :id_c", Product.class)
                .setParameter("id_c", idProduct)
                .getResultList();
        em.close();
        return list;
    }

    private static void deleteUser(int idUser) {
        EntityManager em = createManager();
        em.getTransaction().begin();
        User user = em.find(User.class, idUser);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    private static void deleteProduct(int idProduct) {
        EntityManager em = createManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, idProduct);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    private static EntityManager createManager(){
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory()
                .createEntityManager();
    }
}
