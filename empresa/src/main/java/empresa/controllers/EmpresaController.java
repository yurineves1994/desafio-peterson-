package empresa.controllers;

import java.util.List;

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

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        return ResponseEntity.ok().body(empresaService.getAllEmpresas());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody @Valid Empresa empresa) {
        System.out.println(empresa);

        List<Empresa> empresas = empresaService.cadastrarEmpresa(empresa);

        return ResponseEntity.ok().body(empresas);
    }
}
