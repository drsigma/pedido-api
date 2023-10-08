package drsgima.com.github.pedidos_api.service;

import drsgima.com.github.pedidos_api.entity.Cidade;
import drsgima.com.github.pedidos_api.exception.ObjectNotFoundException;
import drsgima.com.github.pedidos_api.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    public Cidade find(Integer id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        return cidade.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id +
                ", Tipo" + Cidade.class.getName()));
    }

    public URI insert(Cidade cidade) {
        Cidade newCidade = cidadeRepository.save(cidade);
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCidade.getId())
                .toUri();

    }

    public void update(Cidade cidade, Integer id) {
        find(id);
        cidade.setId(id);
        cidadeRepository.save(cidade);
    }

    public void delete(Integer id) {
        find(id);
        cidadeRepository.deleteById(id);
    }

}