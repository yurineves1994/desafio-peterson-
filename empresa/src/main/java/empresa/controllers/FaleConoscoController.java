package empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import empresa.entities.FaleConosco;
import empresa.services.FaleConoscoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/faleconosco")
public class FaleConoscoController {

    @Autowired
    private FaleConoscoService faleConoscoService;

    @PostMapping("/{idEmpresa}")
    public ResponseEntity<FaleConosco> cadastrarPergunta(@PathVariable Long idEmpresa, @RequestBody FaleConosco pergunta) {
        FaleConosco novaPergunta = faleConoscoService.cadastrarFaleConosco(idEmpresa, pergunta);

        return ResponseEntity.ok().body(novaPergunta);
    }
}
