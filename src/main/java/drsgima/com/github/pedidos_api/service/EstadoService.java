package drsgima.com.github.pedidos_api.service;

import drsgima.com.github.pedidos_api.entity.Estado;
import drsgima.com.github.pedidos_api.exception.ObjectNotFoundException;
import drsgima.com.github.pedidos_api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;

    @Transactional
    public URI insert(Estado estado) {
        Estado newEstado = estadoRepository.save(estado);
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEstado.getId())
                .toUri();

    }

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    public Estado find(Integer id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
    }

    public void update(Estado estado, Integer id) {
        find(id);
        estado.setId(id);
        estadoRepository.save(estado);
    }

    public void delete(Integer id) {
        find(id);
        estadoRepository.deleteById(id);
    }
}