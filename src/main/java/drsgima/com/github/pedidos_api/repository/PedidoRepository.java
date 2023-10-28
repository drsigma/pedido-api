package drsgima.com.github.pedidos_api.repository;

import drsgima.com.github.pedidos_api.entity.Cliente;
import drsgima.com.github.pedidos_api.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Transactional(readOnly = true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
