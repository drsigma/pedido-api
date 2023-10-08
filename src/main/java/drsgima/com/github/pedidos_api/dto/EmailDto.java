package drsgima.com.github.pedidos_api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "O Campo E-MAIL é preenchimento obrigatório")
    @Email(message = "E-MAIL invalído")
    private String email;

    public EmailDto(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
