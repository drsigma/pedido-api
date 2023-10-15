package drsgima.com.github.pedidos_api.repository;

import drsgima.com.github.pedidos_api.entity.Categoria;
import drsgima.com.github.pedidos_api.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}