package drsgima.com.github.pedidos_api.db;

import drsgima.com.github.pedidos_api.entity.Categoria;
import drsgima.com.github.pedidos_api.entity.Produto;
import drsgima.com.github.pedidos_api.repository.CategoriaRepository;
import drsgima.com.github.pedidos_api.repository.CidadeRepository;
import drsgima.com.github.pedidos_api.repository.EstadoRepository;
import drsgima.com.github.pedidos_api.entity.Cidade;
import drsgima.com.github.pedidos_api.entity.Estado;
import drsgima.com.github.pedidos_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;

@Service
public class DbService {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public void instantiateTestDatabase() throws ParseException {
        Estado est1 = new Estado(null,"São Paulo");
        Estado est2 = new Estado(null,"Minas Gereais");

        Cidade cid1 = new Cidade(null,"uberlândia", est2);
        Cidade cid2= new Cidade(null,"São Paulo", est1);
        Cidade cid3= new Cidade(null,"Campinas", est1);

        est1.getCidades().addAll(Arrays.asList(cid2,cid3));
        est2.getCidades().addAll(Arrays.asList(cid1));

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(2000.00));
        Produto p2 = new Produto(null, "Impressora", BigDecimal.valueOf(800.00));
        Produto p3 = new Produto(null, "Mouse", BigDecimal.valueOf(80.00));
        Produto p4 = new Produto(null, "Mesa de escritório", BigDecimal.valueOf(300.00));
        Produto p5 = new Produto(null, "Toalha", BigDecimal.valueOf(50.00));
        Produto p6 = new Produto(null, "Colcha", BigDecimal.valueOf(200.00));
        Produto p7 = new Produto(null, "TV True color", BigDecimal.valueOf(1200.00));
        Produto p8 = new Produto(null, "Roçadeira", BigDecimal.valueOf(800.00));
        Produto p9 = new Produto(null, "Abajour", BigDecimal.valueOf(100.00));
        Produto p10 = new Produto(null, "Pendente", BigDecimal.valueOf(180.00));
        Produto p11 = new Produto(null, "Shampoo", BigDecimal.valueOf(90.00));

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

    }
}
