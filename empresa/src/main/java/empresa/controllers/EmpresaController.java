package empresa.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import empresa.entities.Empresa;
import empresa.services.EmpresaService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/empresas")
public class EmpresaController {
    private static final Logger logger = LogManager.getLogger(EmpresaController.class);

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        logger.info("LISTAGEM DE TODAS AS EMPRESAS");
        return ResponseEntity.ok().body(empresaService.getAllEmpresas());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody @Valid Empresa empresa) {
        logger.info("EMPRESA CADASTRADAS COM SUCESSO");

        List<Empresa> empresas = empresaService.cadastrarEmpresa(empresa);

        return ResponseEntity.ok().body(empresas);
    }
}
