package models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    private List<Wheel> wheels;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "driver_car",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"))
    private List<Driver> drivers;

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }
}
