package Mesaup.controll.Controller;

import Mesaup.controll.Entity.Mesa;
import Mesaup.controll.Enums.StatusMesa;
import Mesaup.controll.Service.MesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/mesa")

public class MesaController {

    private final MesaService mesaService;
    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @PostMapping
    public ResponseEntity<Mesa> criaMesa(
            @RequestBody Mesa mesa){
        Mesa criar =  mesaService.criaMesa(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(criar);
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> listaMesas(){
        return ResponseEntity.ok(mesaService.listaMesas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscaMesaPorId(
            @PathVariable Long id){
        return ResponseEntity.ok(mesaService.buscaMesaPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Mesa>> buscaMesasPorStatus(
            @PathVariable StatusMesa status){
        return ResponseEntity.ok(mesaService.buscaMesaPorStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> atualizaMesa(
            @RequestBody Mesa mesa,
            @PathVariable Long id ){
        Mesa mesaAtualizada = mesaService.atuliazaMesa(id, mesa);
        return ResponseEntity.ok(mesaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaMesa(
            @PathVariable Long id){
        mesaService.deletaMesa(id);
        return ResponseEntity.ok("Mesa deletada com sucesso");
    }
}
