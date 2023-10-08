package drsgima.com.github.pedidos_api.enums;

public enum EstadoPagamento {
    PENDENTE( 1,  "Pendente"),
    QUITADO( 2,  "Quitado"),
    CANCELADO( 3,  "Cancelado");

    private Integer codigo;
    String descricao;

    private EstadoPagamento(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer codigo){
        if(codigo == null)
            return  null;
        for(EstadoPagamento x : EstadoPagamento.values())
            if(codigo.equals(x.getCodigo()))
                return x;
        throw new IllegalArgumentException("Id de Estado Pagamento Invalido: " + codigo);


    }

}
