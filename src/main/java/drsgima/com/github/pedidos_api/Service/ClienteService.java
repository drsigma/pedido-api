package drsgima.com.github.pedidos_api.Service;

import drsgima.com.github.pedidos_api.Dto.ClienteNewDto;
import drsgima.com.github.pedidos_api.Dto.ClienteUpdateDto;
import drsgima.com.github.pedidos_api.Enum.TipoCliente;
import drsgima.com.github.pedidos_api.Exception.DataIntegratyException;
import drsgima.com.github.pedidos_api.Exception.ObjectNotFoundException;
import drsgima.com.github.pedidos_api.Repository.ClienteRepository;
import drsgima.com.github.pedidos_api.Repository.EnderecoRepository;
import drsgima.com.github.pedidos_api.entity.Cidade;
import drsgima.com.github.pedidos_api.entity.Cliente;
import drsgima.com.github.pedidos_api.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;
import java.util.Optional;

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        Cliente clienteSaved = clienteRepository.save(cliente);
        enderecoRepository.save(clienteSaved.getEnderecos().get(0));
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteToUpdate = find(cliente.getId());
        updateData(clienteToUpdate, cliente);
        return clienteRepository.save(clienteToUpdate);
    }

    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possível excluir um cliente que possui pedidos relacionados." + e.getMessage());
        }
    }


    private void updateData(Cliente clienteToUpdate, Cliente cliente) {
        clienteToUpdate.setNome(cliente.getNome());
        clienteToUpdate.setEmail(cliente.getEmail());
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
        if (clienteNewDto.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone2());
        }
        if (clienteNewDto.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDto.getTelefone3());
        }
        return cliente;
    }

    public Cliente fromDto(ClienteUpdateDto clienteUpdateDto) {
        return new Cliente(
                clienteUpdateDto.getId(),
                clienteUpdateDto.getNome(),
                clienteUpdateDto.getEmail(),
                null,
                null,
                null);
    }
}
