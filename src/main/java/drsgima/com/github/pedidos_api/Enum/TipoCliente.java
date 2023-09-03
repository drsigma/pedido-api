package drsgima.com.github.pedidos_api.Enum;

public enum TipoCliente {
    PESSOAFISICA(1, "Pessoa Fisica"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private Integer codigo;
    private String descricao;
    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    private TipoCliente(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoCliente toEnum(Integer codigo){
        if(codigo == null)
            return null;
        for(TipoCliente x : TipoCliente.values())
            if(codigo.equals(x.getCodigo()))
                return x;
        throw new IllegalArgumentException("Id de Tipo de Cliente  invalído: " + codigo);
    }

}
