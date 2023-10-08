package drsgima.com.github.pedidos_api.enums;

public enum Perfil {
    ADMIN(1, "Role_Admin"),
    CLIENTE(2, "Role_Cliente");

    private Integer codigo;
    private String descricao;
    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    private Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer codigo){
        if(codigo == null)
            return null;
        for(Perfil x : Perfil.values())
            if(codigo.equals(x.getCodigo()))
                return x;
        throw new IllegalArgumentException("Id de Tipo de Cliente  invalído: " + codigo);
    }

}

