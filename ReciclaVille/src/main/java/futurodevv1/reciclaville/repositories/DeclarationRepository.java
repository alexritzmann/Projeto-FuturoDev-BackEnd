package futurodevv1.reciclaville.repositories;

import futurodevv1.reciclaville.entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long>
{
    List<Declaration> findByClientId(Long clientId);

    @Query("SELECT m.name AS material, SUM(i.tonsCompensation) AS total " +
            "FROM ItemDeclaration i " +
            "JOIN i.material m " +
            "JOIN i.declaration d " +
            "WHERE d.client.id = :clientId " +
            "GROUP BY m.name")
    List<Map<String, Object>> findTotalCompensationByMaterialAndClient(@Param("clientId") Long clientId);

    @Query("SELECT m.name AS material, SUM(i.tonsCompensation) AS total " +
            "FROM ItemDeclaration i " +
            "JOIN i.material m " +
            "GROUP BY m.name")
    List<Map<String, Object>> findTotalCompensationByMaterial();
}
