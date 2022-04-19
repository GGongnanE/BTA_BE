package com.example.fluxdemo.web;

import com.example.fluxdemo.domain.Customer;
import com.example.fluxdemo.domain.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

//@WebFluxTest
//@Import(CustomerService.class)
@SpringBootTest
class CustomerControllerTest2 {

    @Autowired
    CustomerRepository customerRepository;

//    @Autowired
//    private WebTestClient webTestClient; // 비동기로 http 요청

    @DisplayName("단건 조회 테스트")
    @Test
    void selectTest01() {
        Flux<Customer> customerFlux = customerRepository.findAll();
        customerFlux.subscribe(c -> System.out.println("Data : " + c));

    }

}