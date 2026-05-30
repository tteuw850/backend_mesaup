package Mesaup.controll.Repository;

import Mesaup.controll.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    boolean existsByNome(String nome);
}
