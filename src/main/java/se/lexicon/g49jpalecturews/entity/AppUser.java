package se.lexicon.g49jpalecturews.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class AppUser {
   //Fields
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

   @Column(nullable = false, unique = true)
   @Setter private String username;

   @Column(nullable = false)
   @Setter private String password;

   @Column
   @Setter private LocalDate regDate;

   @OneToOne
   @JoinColumn(name = "details.id")
   @Setter private Details userDetails;

   public AppUser(String username, String password) {
      this.username = username;
      this.password = password;
      this.regDate = LocalDate.now();
   }
}
