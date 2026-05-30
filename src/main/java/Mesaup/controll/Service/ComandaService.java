package Mesaup.controll.Service;

import Mesaup.controll.Entity.*;
import Mesaup.controll.Enums.StatusComanda;
import Mesaup.controll.Enums.StatusMesa;
import Mesaup.controll.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class ComandaService {

   private final ComandaRepository comandaRepository;
   private final UsuarioRepository usuarioRepository;
   private final MesaRepository mesaRepository;
   private final ProdutoRepository produtoRepository;
   private final ComandaItemRepository comandaItemRepository;


    public ComandaService(ComandaRepository comandaRepository, UsuarioRepository usuarioRepository, MesaRepository mesaRepository,  ProdutoRepository produtoRepository, ComandaItemRepository comandaItemRepository) {
        this.comandaRepository = comandaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
        this.produtoRepository = produtoRepository;
        this.comandaItemRepository = comandaItemRepository;
    }

    @Transactional
    public Comanda abrirComanda(Long mesaId, Long usuarioId) {

        System.out.println("Mesa ID: " + mesaId);
        System.out.println("Usuario ID: " + usuarioId);

        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa inexistente"));

        System.out.println("Mesa encontrada");

        if (mesa.getStatus() == StatusMesa.OCUPADA) {
            throw new RuntimeException("Mesa ocupada");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario inexistente"));

        System.out.println("Usuario encontrado");

        mesa.setStatus(StatusMesa.OCUPADA);

        Comanda comanda = new Comanda();

        comanda.setMesa(mesa);
        comanda.setFuncionario(usuario);
        comanda.setStatus(StatusComanda.ABERTA);
        comanda.setDataCriacao(LocalDateTime.now());
        comanda.setTotal(BigDecimal.ZERO);

        comanda.setItens(new ArrayList<>());

        System.out.println("Salvando comanda");

        return comandaRepository.save(comanda);
    }

    @Transactional
    public Comanda adicionarItem(
            Long comandaId,
            Long produtoId,
            Integer quantidade) {

        Comanda comanda = comandaRepository.findById(comandaId)
                .orElseThrow(() -> new RuntimeException("Comanda inexistente"));


        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto inexistente"));

        ComandaItem item = new ComandaItem();

        item.setComanda(comanda);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPreco());

        BigDecimal subtotal = produto.getPreco()
                .multiply(BigDecimal.valueOf(quantidade));

        item.setSubtotal(subtotal);

        comandaItemRepository.save(item);

        comanda.getItens().add(item);

        BigDecimal total = comanda.getItens()
                .stream()
                .map(ComandaItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        comanda.setTotal(total);

        return comandaRepository.save(comanda);



    }


    @Transactional
    public void fecharComanda(Long id) {
        Comanda comanda = comandaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda inexistente"));

        comanda.setStatus(StatusComanda.FECHADA);
        comanda.setDataFechamento(LocalDateTime.now());


        Mesa mesa = comanda.getMesa();
        mesa.setStatus(StatusMesa.LIVRE);

        comandaRepository.save(comanda);
    }


    @Transactional
    public Comanda alterarPedido (
            Long itemId,
            Integer novaQuantidade) {

        if(novaQuantidade <= 0) {
            throw new RuntimeException(
                    "Quantidade inválida"
            );
        }

        ComandaItem item = comandaItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Comanda comanda = item.getComanda();
        if (comanda.getStatus() == StatusComanda.FECHADA) {
            throw new RuntimeException("Comanda fechada");
        }

        item.setQuantidade(novaQuantidade);
        BigDecimal novoSubTotal = item.getPrecoUnitario()
                .multiply(BigDecimal.valueOf(novaQuantidade));

        item.setSubtotal(novoSubTotal);
        BigDecimal total = comanda.getItens()
                .stream()
                .map(ComandaItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        comanda.setTotal(total);
        return comandaRepository.save(comanda);

    }

    public Comanda buscarComandaPorId(Long id){

        return comandaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Comanda não encontrada"));
    }

    public Comanda buscarComandaPorMesa(Long mesaId){

        return comandaRepository
                .findByMesaIdAndStatus(
                        mesaId,
                        StatusComanda.ABERTA
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "Comanda não encontrada"
                        )
                );
    }


    public List<Comanda> listarComandas() {
        return comandaRepository.findAll();
    }


}
