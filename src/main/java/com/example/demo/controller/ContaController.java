package com.example.demo.controller;

import com.example.demo.controller.dto.ContaRequest;
import com.example.demo.service.Conta;
import com.example.demo.service.ContaService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> criarConta(@RequestBody ContaRequest request) {

        contaService.criarConta(request.name(), request.cpf());

        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .build();
    }


    @GetMapping("/accounts")
    public ResponseEntity<List<Conta>> criarConta() {

        var contasLista = contaService.listarContas();

        return ResponseEntity.ok(contasLista);
    }
}
