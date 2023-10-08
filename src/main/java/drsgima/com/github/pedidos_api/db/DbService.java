package drsgima.com.github.pedidos_api.db;

import drsgima.com.github.pedidos_api.repository.CidadeRepository;
import drsgima.com.github.pedidos_api.repository.EstadoRepository;
import drsgima.com.github.pedidos_api.entity.Cidade;
import drsgima.com.github.pedidos_api.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DbService {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    public void instantiateTestDatabase() throws ParseException {
        Estado est1 = new Estado(null,"São Paulo");
        Estado est2 = new Estado(null,"Minas Gereais");

        Cidade cid1 = new Cidade(null,"uberlândia", est2);
        Cidade cid2= new Cidade(null,"São Paulo", est1);
        Cidade cid3= new Cidade(null,"Campinas", est1);

        est1.getCidades().addAll(Arrays.asList(cid2,cid3));
        est2.getCidades().addAll(Arrays.asList(cid1));

        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));

    }
}
