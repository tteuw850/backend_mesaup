package Mesaup.controll.Repository;

import Mesaup.controll.Entity.Comanda;
import Mesaup.controll.Enums.StatusComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda,Long> {
    Optional<Comanda> findByMesaIdAndStatus(
            Long mesaId,
            StatusComanda status
    );
}
