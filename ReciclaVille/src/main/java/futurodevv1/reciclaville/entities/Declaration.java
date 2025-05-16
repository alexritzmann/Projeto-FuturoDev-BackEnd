package futurodevv1.reciclaville.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "declarations")
public class Declaration
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name = "date_declaration", nullable = false)
    private LocalDate dateDeclaration;

    @NotNull
    @Column(name = "date_start_period", nullable = false)
    private LocalDate dateStartPeriod;

    @NotNull
    @Column(name = "date_final_period", nullable = false)
    private LocalDate dateFinalPeriod;

    @NotNull
    @Column(name = "total_materials", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalMaterials;

    @NotNull
    @Column(name = "total_compensation", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCompensation;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemDeclaration> items;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}