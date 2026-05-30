package Mesaup.controll.Repository;

import Mesaup.controll.Entity.Comanda;
import Mesaup.controll.Entity.ComandaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaItemRepository extends JpaRepository<ComandaItem,Long> {
}
