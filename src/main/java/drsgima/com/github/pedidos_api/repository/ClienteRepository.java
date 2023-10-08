package drsgima.com.github.pedidos_api.repository;

import drsgima.com.github.pedidos_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
