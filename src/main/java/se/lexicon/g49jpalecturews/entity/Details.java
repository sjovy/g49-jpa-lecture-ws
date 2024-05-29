package se.lexicon.g49jpalecturews.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.naming.factory.SendMailFactory;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    @Setter private String name;

    @Column(nullable = false, length = 100, unique = true)
    @Setter private String email;

    @Column
    @Setter private LocalDate birthDate;

    public Details(String email, String name, LocalDate birthDate) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
    }
}
