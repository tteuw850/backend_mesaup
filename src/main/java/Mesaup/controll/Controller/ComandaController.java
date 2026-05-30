package Mesaup.controll.Controller;

import Mesaup.controll.Entity.Comanda;
import Mesaup.controll.Service.ComandaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comanda")

public class ComandaController {

    private final ComandaService comandaService;
    public ComandaController(ComandaService comandaService) {
        this.comandaService = comandaService;
    }


    @PostMapping("/abrir")
    public ResponseEntity<Comanda> abrirComanda (
            @RequestParam Long mesaId,
            @RequestParam Long usuarioId){
        Comanda comanda = comandaService.abrirComanda(mesaId, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(comanda);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Comanda> adicionarItem (
            @RequestParam Long comandaId,
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade) {
        Comanda comanda = comandaService.adicionarItem(comandaId, produtoId, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(comanda);
    }


    @PutMapping("/fechar/{comandaId}")
    public ResponseEntity<String> fecharComanda(
            @PathVariable Long comandaId){
        comandaService.fecharComanda(comandaId);
        return ResponseEntity.status(HttpStatus.OK).body("Comanda Fechada");
    }

    @PutMapping("/alterar-pedido")
    public ResponseEntity<Comanda> alterarPedido(
            @RequestParam Long itemId,
            @RequestParam Integer novaQuantidade) {
        Comanda comanda = comandaService.alterarPedido(itemId, novaQuantidade);
        return ResponseEntity.status(HttpStatus.OK).body(comanda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> buscarComanda(
            @PathVariable Long id){

        return ResponseEntity.ok(
                comandaService.buscarComandaPorId(id)
        );
    }

    @GetMapping("/mesa/{mesaId}")
    public ResponseEntity<Comanda> buscarComanadaPorMesa(
            @PathVariable Long mesaId){

        return ResponseEntity.ok(
                comandaService.buscarComandaPorMesa(mesaId)
        );
    }

    @GetMapping
    public ResponseEntity<List<Comanda>> listarComandas() {
        return ResponseEntity.ok(comandaService.listarComandas());
    }
}
