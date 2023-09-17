package drsgima.com.github.pedidos_api.Validation;

import drsgima.com.github.pedidos_api.Dto.ClienteNewDto;
import drsgima.com.github.pedidos_api.Enum.TipoCliente;
import drsgima.com.github.pedidos_api.Exception.FieldMessage;
import drsgima.com.github.pedidos_api.Repository.ClienteRepository;
import drsgima.com.github.pedidos_api.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import drsgima.com.github.pedidos_api.Util.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDto clienteNewDto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if(clienteNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCpf(clienteNewDto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CPF Invalído"));
        }
        if(clienteNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCnpj(clienteNewDto.getCpfCnpj())){
            list.add(new FieldMessage("cpfCnpj", "CNPJ Invalído"));
        }
        Cliente cliente = clienteRepository.findByEmail(clienteNewDto.getEmail());

        if(cliente != null){
            list.add(new FieldMessage("email","E-mail já existente"));
        }

        for(FieldMessage e : list){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
