package Mesaup.controll.Entity;

import Mesaup.controll.Enums.StatusComanda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_comanda")
public class Comanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario funcionario;

    @OneToMany(
                mappedBy = "comanda",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.EAGER

    )
    private List<ComandaItem> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusComanda status;

    private BigDecimal total;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFechamento;

    public Comanda() {
        this.id = id;
        this.mesa = mesa;
        this.funcionario = funcionario;
        this.itens = itens;
        this.status = status;
        this.total = total;
        this.dataCriacao = dataCriacao;
        this.dataFechamento = dataFechamento;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ComandaItem> getItens() {
        return itens;
    }

    public void setItens(List<ComandaItem> itens) {
        this.itens = itens;
    }

    public StatusComanda getStatus() {
        return status;
    }

    public void setStatus(StatusComanda status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
}
