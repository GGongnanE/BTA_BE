package com.example.fluxdemo.web;

import com.example.fluxdemo.domain.Customer;
import com.example.fluxdemo.domain.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
class CustomerControllerTest {

    @MockBean
    CustomerRepository customerRepository;

    @Autowired
    private WebTestClient webTestClient; // 비동기로 http 요청

    @DisplayName("단건 조회 테스트")
    @Test
    void selectTest01() {

        // given
        Mono<Customer> testData = Mono.just(new Customer("Jack","Bauer"));

        // stub -> 행동 지시
        when(customerRepository.findById(1L)).thenReturn(testData);

        webTestClient.get().uri("/customer/{id}",1L)
                .exchange()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Jack")
                .jsonPath("$.lastName").isEqualTo("Bauer");
    }

}