package models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guitars")
@ToString
@Builder
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(
            name = "manufacturer",
            unique = false,
            nullable = false,
            insertable = true,
            updatable = true,
            length = 100
    )
    private String manufacturerName;
    @Enumerated(EnumType.STRING)
    private Pickup pickup;
    @Enumerated(EnumType.STRING)
    private Body body;
    private boolean caseIncluded;
    @Column(
            name = "strings",
            unique = false,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private int numberOfStrings;

}
