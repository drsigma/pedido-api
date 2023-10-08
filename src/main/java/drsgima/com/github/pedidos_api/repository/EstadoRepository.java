package drsgima.com.github.pedidos_api.repository;

import drsgima.com.github.pedidos_api.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {

}
