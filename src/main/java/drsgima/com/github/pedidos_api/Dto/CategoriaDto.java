package drsgima.com.github.pedidos_api.Dto;

import drsgima.com.github.pedidos_api.entity.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDto implements Serializable {
    private static final long serialVersionUUID = 1L;

    private Integer id;
    @NotEmpty(message = "Campo categoria é de preenchimento obrigatório")
    @Length(min = 5, max = 80, message = "O tamanho do campo categoria deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDto(){
    }

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
