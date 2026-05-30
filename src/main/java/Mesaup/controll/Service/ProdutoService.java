package Mesaup.controll.Service;

import Mesaup.controll.Entity.Produto;
import Mesaup.controll.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criaProduto(Produto produto){
        boolean existisProduto = produtoRepository
                .existsByNome(produto.getNome());

        if(existisProduto){
            throw new RuntimeException(
                    "Produto já existe"
            );
        }
        return produtoRepository.save(produto);
    }

    public List<Produto> listaProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        if(produtos.isEmpty()){
            throw new RuntimeException(
                    "Nenhum produto encontrado"
            );
        }
        return produtos;
    }

    public Produto buscaProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id).get();
        if(produto == null){
            throw new RuntimeException(
                    "Nenhum produto encontrado"
            );
        }
        return produto;
    }

    public Produto atualizaProduto(Long id, Produto produto){
        Produto produtoAtualizado = produtoRepository.findById(id).get();
        if(produtoAtualizado == null){
            throw new RuntimeException(
                    "Nenhum produto encontrado"
            );
        }

        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setPreco(produto.getPreco());
        produtoAtualizado.setCategoria(produto.getCategoria());

        produtoRepository.save(produtoAtualizado);
        return produtoAtualizado;


    }

    public Produto deletaProduto(Long id){
        Produto produto = produtoRepository.findById(id).get();
        if(produto == null){
            throw new RuntimeException(
                    "Nenhum produto encontrado"
            );
        }
        produtoRepository.delete(produto);
        return produto;
    }


}
