package local.prueba.api.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "name", length = 45)
    private String name;

    @Getter @Setter @Column(name = "dni", length = 12)
    private String dni;

    @Getter @Setter @Column(name = "email", length = 255)
    private String email;

    @Getter @Setter @Column(name = "phone", length = 12)
    private String phone;

    @Getter @Setter @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
}
