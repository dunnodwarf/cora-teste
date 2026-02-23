package com.example.demo.service;

import com.example.demo.repository.Conta;
import com.example.demo.repository.ContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private static final Logger log =  LoggerFactory.getLogger(ContaService. class);

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void criarConta(String nome, String cpf) {
        log.info("Criando conta para o cpf '{}'", cpf);
        Conta novaConta = new Conta(nome, cpf);

        contaRepository.save(novaConta);
        log.info("Conta para o cpf '{}' salva", cpf);
    }

    public List<Conta> listarContas() {
        log.info("Listando todas as contas salvas");

        List<Conta> contaLista = contaRepository.findAll();

        if (contaLista.isEmpty()) log.info("Não há contas salvas");

        return contaLista;
    }

}
