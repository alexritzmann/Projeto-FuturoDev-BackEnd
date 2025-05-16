package futurodevv1.reciclaville.repositories;

import futurodevv1.reciclaville.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long>
{
    boolean existsByName(String name);
}
