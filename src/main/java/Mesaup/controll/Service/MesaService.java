package Mesaup.controll.Service;

import Mesaup.controll.Entity.Mesa;
import Mesaup.controll.Enums.StatusComanda;
import Mesaup.controll.Enums.StatusMesa;
import Mesaup.controll.Repository.MesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MesaService {


    private final MesaRepository mesaRepository;


    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Transactional
    public Mesa criaMesa (Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public List<Mesa> listaMesas() {
        List<Mesa> mesas = mesaRepository.findAll();
        if(mesas.isEmpty()){
            throw new RuntimeException(
                    "Nenhum mesa encontrada"
            );
        }
        return mesas;
    }

    public Mesa buscaMesaPorId(Long id) {
        Mesa mesa = mesaRepository.findById(id).
                orElseThrow(() -> new RuntimeException(
                        "Nenhum mesa encontrada"
                ));
        return mesa;
    }

    public List<Mesa> buscaMesaPorStatus(StatusMesa status) {
        List<Mesa> mesaBuscada = mesaRepository.findByStatus(status);
        if(mesaBuscada.isEmpty()){
            throw new RuntimeException(
                    "Mesa com o status solicitado nao encontrada");
        }
        return mesaBuscada;
    }

    @Transactional
    public Mesa atuliazaMesa(Long id, Mesa mesa) {
        Mesa mesaBuscada = mesaRepository.findById(id).get();
        if(mesaBuscada == null){
            throw new RuntimeException(
                    "Mesa com o id solicitado nao encontrada"
            );
        }
        mesaBuscada.setStatus(mesa.getStatus());
        mesaBuscada.setNumero(mesa.getNumero());

        return mesaRepository.save(mesaBuscada);    
    }

    public Mesa deletaMesa(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Mesa com o id solicitado não encontrada"
                ));
        boolean temComandaAberta = mesa.getComandas().stream()
                .anyMatch(c -> c.getStatus() == StatusComanda.ABERTA);

        if (temComandaAberta) {
            throw new RuntimeException(
                    "Não é possível excluir uma mesa com comanda aberta"
            );
        }

        mesaRepository.delete(mesa);
        return mesa;
    }




}
