import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

        Metadata metadata =
                new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(Car.class)
                        .addAnnotatedClass(Driver.class)
                        .addAnnotatedClass(Engine.class)
                        .addAnnotatedClass(Wheel.class)
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        /* Creating entities */
        session.beginTransaction();

        /* oto ud */
        Engine engine = new Engine("С20ХЕ");

        /* mtm bd */
        Driver driver = new Driver("Taxi driver");

        /* mto ud */
        List<Wheel> wheels = Arrays.asList(
                new Wheel(Position.FRONT_LEFT),
                new Wheel(Position.FRONT_RIGHT),
                new Wheel(Position.REAR_LEFT),
                new Wheel(Position.REAR_RIGHT)
        );

        session.save(Car.builder()
                .name("Audi")
                .engine(new Engine("4.2 V8 40v"))
                .drivers(Arrays.asList(
                        Driver.builder()
                                .name("Fernando Alonso")
                                .cars(Arrays.asList(
                                        new Car("Korch", engine),
                                        new Car("Ferrari")
                                ))
                                .build(),
                        driver))
                .wheels(wheels)
                .build());

        session.save(Car.builder()
                .name("Ford")
                .engine(new Engine("DOHC 4.4 L twin-turbo Diesel"))
                .drivers(Arrays.asList(
                        Driver.builder().name("Lewis Hamilton").build(),
                        Driver.builder().name("Ayrton Senna").build(),
                        driver
                ))
                .wheels(wheels)
                .build());

        session.getTransaction().commit();

        List<Car> cars = session.createQuery("from Car", Car.class).list();

        /* Entities output */
        System.out.println("---- List of Cars:");
        cars.forEach(System.out::println);

        session.close();
        sessionFactory.close();
    }
}