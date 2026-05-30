package Mesaup.controll.Controller;

import Mesaup.controll.Entity.Produto;
import Mesaup.controll.Service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> criaProduto(
            @RequestBody Produto produto){
        Produto criar = produtoService.criaProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criar);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listaProdutos(){
        return ResponseEntity.ok(produtoService.listaProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(
            @PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscaProdutoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizaProduto(
            @PathVariable Long id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.atualizaProduto(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProduto(
            @PathVariable Long id){
        Produto deletado = produtoService.deletaProduto(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso");
    }







}
