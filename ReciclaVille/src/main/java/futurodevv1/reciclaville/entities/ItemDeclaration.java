package futurodevv1.reciclaville.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items_declaration")
public class ItemDeclaration
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "percentage_compensation", nullable = false, precision = 5, scale = 2)
    private BigDecimal percentageCompensation;

    @NotNull
    @Column(name = "tons_declared", nullable = false, precision = 10, scale = 2)
    private BigDecimal tonsDeclared;

    @NotNull
    @Column(name = "tons_compensation", nullable = false, precision = 10, scale = 2)
    private BigDecimal tonsCompensation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private Declaration declaration;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;
}