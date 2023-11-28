package empresa.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import empresa.entities.Endereco;
import empresa.services.EnderecoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/enderecos")
public class EnderecoController {
    private static final Logger logger = LogManager.getLogger(EnderecoController.class);

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        logger.info("LISTAGEM DE TODAS OS ENDEREÇOS");
        return ResponseEntity.ok().body(enderecoService.getAllEnderecos());
    }

    @PostMapping("/{idEmpresa}")
    public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable Long idEmpresa, @RequestBody Endereco endereco) {
        logger.info("ENDEREÇO CADASTRADAS COM SUCESSO");

        Endereco newEndereco = enderecoService.cadastrarEndereco(idEmpresa, endereco);

        return ResponseEntity.ok().body(newEndereco);
    }
}
