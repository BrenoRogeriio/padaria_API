package com.services;

import com.domains.*;
import com.domains.enums.StatusEncomenda;
import com.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired private ClienteRepository clienteRepo;
    @Autowired private EventoRepository eventoRepo;
    @Autowired private ConfeiteiroRepository confeiteiroRepo;
    @Autowired private ProdutoRepository produtoRepo;
    @Autowired private EncomendaRepository encomendaRepo;

    public void instanciarDB() {
        // 1. Clientes
        Cliente cli1 = new Cliente(null, "Breno", "111.111.111-11", "(17) 99999-1111");
        Cliente cli2 = new Cliente(null, "Cleicy", "222.222.222-22", "(17) 99999-2222");
        clienteRepo.saveAll(Arrays.asList(cli1, cli2));

        // 2. Eventos
        Evento ev1 = new Evento(null, "Aniversário do Brian (Tema: Minecraft Survival)", LocalDate.now().plusDays(15), "Festa Infantil", cli1);
        Evento ev2 = new Evento(null, "Café da Tarde Especial", LocalDate.now().plusDays(2), "Reunião Familiar", cli2);
        eventoRepo.saveAll(Arrays.asList(ev1, ev2));

        // 3. Confeiteiros
        Confeiteiro conf1 = new Confeiteiro(null, "Dona Maria", "Salgados Finos e Tradicionais");
        Confeiteiro conf2 = new Confeiteiro(null, "Chef Carlos", "Bolos Decorados e Doces");
        confeiteiroRepo.saveAll(Arrays.asList(conf1, conf2));

        // 4. Produtos (Cardápio)
        Produto p1 = new Produto(null, "Cento de Coxinha", 65.00);
        Produto p2 = new Produto(null, "Cento de Bolinha de Queijo", 65.00);
        Produto p3 = new Produto(null, "Bolo de Chocolate Decorado (2kg)", 120.00);
        Produto p4 = new Produto(null, "Bandeja de Mini Churros (50 un)", 40.00);
        produtoRepo.saveAll(Arrays.asList(p1, p2, p3, p4));

        // 5. Encomendas (O Carrinho de Compras)

        // Encomenda 1: Festa do Brian (Salgados com a Dona Maria)
        Encomenda enc1 = new Encomenda(null, LocalDateTime.now(), 130.00, StatusEncomenda.PREPARANDO, ev1, conf1);
        enc1.getProdutos().addAll(Arrays.asList(p1, p2)); // 1 cento de coxinha + 1 cento de bolinha

        // Encomenda 2: Festa do Brian (Bolo com o Chef Carlos)
        Encomenda enc2 = new Encomenda(null, LocalDateTime.now(), 120.00, StatusEncomenda.PENDENTE, ev1, conf2);
        enc2.getProdutos().addAll(Arrays.asList(p3)); // O bolo

        // Encomenda 3: Café da Tarde da Cleicy
        Encomenda enc3 = new Encomenda(null, LocalDateTime.now(), 40.00, StatusEncomenda.PRONTA, ev2, conf2);
        enc3.getProdutos().addAll(Arrays.asList(p4)); // Os mini churros

        encomendaRepo.saveAll(Arrays.asList(enc1, enc2, enc3));
    }
}