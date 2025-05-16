package futurodevv1.reciclaville.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materials")
public class Material
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @NotNull
    @Column(name = "percentage_compensation", nullable = false, precision = 5, scale = 2)
    private BigDecimal percentageCompensation;

    @OneToMany(mappedBy = "material", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ItemDeclaration> itemsDeclarations;
}