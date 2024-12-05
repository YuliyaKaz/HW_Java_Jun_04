package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Создание SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Открытие сессии
        Session session = sessionFactory.openSession();

        try {
            // Начало транзакции
            session.beginTransaction();

            // Создание объектов Person
            Person person1 = new Person("Kliment Voroshilov", 55);
            Person person2 = new Person("Vasily Chapaev", 55);

            // Сохранение объектов в базу данных
            session.save(person1);
            session.save(person2);

            // Завершение транзакции
            session.getTransaction().commit();

            System.out.println("Persons saved successfully!");

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}