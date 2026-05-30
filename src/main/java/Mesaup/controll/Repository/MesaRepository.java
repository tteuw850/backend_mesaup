package Mesaup.controll.Repository;

import Mesaup.controll.Entity.Mesa;
import Mesaup.controll.Enums.StatusMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa,Long> {
    List<Mesa> findByStatus(StatusMesa status);
}
