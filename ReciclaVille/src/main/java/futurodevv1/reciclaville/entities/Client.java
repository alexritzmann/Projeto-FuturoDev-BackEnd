package futurodevv1.reciclaville.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    private String cnpj;

    @NotNull
    @Column(name = "economic_activity", nullable = false, length = 100)
    private String economicActivity;

    @NotNull
    @Column(name = "responsible", nullable = false, length = 100)
    private String responsible;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Declaration> declarations;

    @OneToMany(mappedBy = "client")
    private List<User> users;
}