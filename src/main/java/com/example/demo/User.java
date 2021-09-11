package com.example.demo;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "User")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="create_datetime")
    private LocalDateTime timestamp;
}
