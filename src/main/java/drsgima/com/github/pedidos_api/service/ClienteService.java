package drsgima.com.github.pedidos_api.service;

import drsgima.com.github.pedidos_api.dto.ClienteNewDto;
import drsgima.com.github.pedidos_api.dto.ClienteUpdateDto;
import drsgima.com.github.pedidos_api.entity.Cidade;
import drsgima.com.github.pedidos_api.entity.Cliente;
import drsgima.com.github.pedidos_api.entity.Endereco;
import drsgima.com.github.pedidos_api.enums.TipoCliente;
import drsgima.com.github.pedidos_api.exception.DataIntegratyException;
import drsgima.com.github.pedidos_api.exception.ObjectNotFoundException;
import drsgima.com.github.pedidos_api.repository.ClienteRepository;
import drsgima.com.github.pedidos_api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public URI insert(ClienteNewDto cliente) {
        Cliente newcliente = fromDto(cliente);
        Cliente clienteSaved = clienteRepository.save(newcliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(newcliente.getId()).toUri();
        enderecoRepository.save(clienteSaved.getEnderecos().get(0));
        return uri;
    }

    public void update(Integer id, ClienteUpdateDto clienteUpdateDto) {
        Cliente clienteToUpdate = find(id);
        updateData(clienteToUpdate, clienteUpdateDto);
        clienteRepository.save(clienteToUpdate);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possível excluir um cliente que possui pedidos relacionados." + e.getMessage());
        }
    }


    public Cliente fromDto(ClienteNewDto clienteNewDto) {
        Cliente cliente = new Cliente(null,
                clienteNewDto.getNome(),
                clienteNewDto.getEmail(),
                clienteNewDto.getCpfCnpj(),
                TipoCliente.toEnum(clienteNewDto.getTipo()),
                clienteNewDto.getSenha());

        Cidade cidade = new Cidade(clienteNewDto.getCidadeId(), null, null);

        Endereco endereco = new Endereco(null,
                clienteNewDto.getLogradouro(),
                clienteNewDto.getNumero(),
                clienteNewDto.getComplemento(),
                clienteNewDto.getBairro(),
                cliente,
                cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNewDto.getTelefone1());
        if (clienteNewDto.getTelefone2() != null && clienteNewDto.getTelefone2() != "") {
            cliente.getTelefones().add(clienteNewDto.getTelefone2());
        }
        if (clienteNewDto.getTelefone3() != null && clienteNewDto.getTelefone3() != "") {
            cliente.getTelefones().add(clienteNewDto.getTelefone3());
        }
        return cliente;
    }

    private void updateData(Cliente clienteToUpdate, ClienteUpdateDto cliente) {
        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setEmail(cliente.getEmail());
    }
}
