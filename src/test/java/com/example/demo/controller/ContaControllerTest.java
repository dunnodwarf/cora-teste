package com.example.demo.controller;

import com.example.demo.controller.dto.ContaRequest;
import com.example.demo.service.Conta;
import com.example.demo.service.ContaService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ContaService contaService;

    @Test
    void shouldCreateAccountSuccessfully() throws Exception {
        doNothing().when(contaService).criarConta(any(), any());

        ContaRequest request = new ContaRequest("Felipe Lima", "12345678901");

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                            {
                                                "name": "%s",
                                                "cpf": "%s"
                                            }
                                        """
                                        .formatted(request.name(), request.cpf())))
                .andExpect(status().isCreated());

        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.captor();
        ArgumentCaptor<String> cpfCaptor = ArgumentCaptor.captor();
        verify(contaService).criarConta(nameCaptor.capture(), cpfCaptor.capture());
        assertEquals(request.name(), nameCaptor.getValue());
        assertEquals(request.cpf(), cpfCaptor.getValue());
    }

    @Test
    void shouldListAccountsSuccessfully() throws Exception {
        Conta conta = new Conta("Felipe Lima", "12345678901");
        Conta conta2 = new Conta("Jose Silva", "42353541245");

        when(contaService.listarContas()).thenReturn(List.of(conta, conta2));

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(conta.getName()))
                .andExpect(jsonPath("$[0].cpf").value(conta.getCpf()))
                .andExpect(jsonPath("$[1].name").value(conta2.getName()))
                .andExpect(jsonPath("$[1].cpf").value(conta2.getCpf()));

        verify(contaService).listarContas();
    }

    @Test
    void shouldThrownExceptionWhenListingAccount() throws Exception {
        doThrow(RuntimeException.class)
                .when(contaService).listarContas();

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(contaService).listarContas();
    }

}