package models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wheels")
public class Wheel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated
    @Column(unique = true) /* Only one unique position allowed */
    private Position position;

    public Wheel(Position position) {
        this.position = position;
    }
}
