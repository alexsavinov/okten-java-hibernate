import models.Body;
import models.Guitar;
import models.Pickup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();

        Metadata metadata =
                new MetadataSources(serviceRegistry)
                        .addAnnotatedClass(Guitar.class)
                        .getMetadataBuilder()
                        .build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        /* Creating a list of entities */
        session.beginTransaction();

        session.save(Guitar.builder()
                .manufacturerName("Les Paul")
                .pickup(Pickup.HH)
                .body(Body.MAHOGANY)
                .caseIncluded(true)
                .numberOfStrings(6)
                .build());

        session.save(Guitar.builder()
                .manufacturerName("Ibanez")
                .pickup(Pickup.HH)
                .body(Body.ALDER)
                .caseIncluded(false)
                .numberOfStrings(7)
                .build());

        session.save(Guitar.builder()
                .manufacturerName("Fender")
                .pickup(Pickup.SSS)
                .body(Body.ASH)
                .caseIncluded(true)
                .numberOfStrings(6)
                .build());

        session.getTransaction().commit();

        List<Guitar> guitars = session.createQuery("from Guitar", Guitar.class).list();

        /* List output */
        System.out.println("---- List of Guitars:");
        guitars.forEach(System.out::println);

        /* Search entity by id */
        Guitar guitar = session.find(Guitar.class, 2);

        System.out.println("---- Guitar entity with id 2:");
        System.out.println(guitar);

        session.close();
        sessionFactory.close();
    }
}
