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
   private String username;

   @Column(nullable = false)
   private String password;

   @Column
   private LocalDate regDate;
   @Setter
   @OneToOne
   @JoinColumn(name = "details.id")
   private Details userDetails;

   public AppUser(int id, String username, String password, LocalDate regDate) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.regDate = regDate;
   }
}
