package futurodevv1.reciclaville.repositories;

import futurodevv1.reciclaville.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{
    boolean existsByCnpj(String cnpj);
}
