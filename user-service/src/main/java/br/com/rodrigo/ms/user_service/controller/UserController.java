package br.com.rodrigo.ms.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.ms.user_service.dtos.ResponseAddressDTO;
import br.com.rodrigo.ms.user_service.proxies.CepProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired 
    private CepProxy cepProxy;

    @GetMapping("/{cep}")
    public ResponseEntity<ResponseAddressDTO> index(@PathVariable("cep") String cep) {
        ResponseAddressDTO enderecoPorCep = cepProxy.getEnderecoPorCep(cep);
    
        return ResponseEntity.ok(enderecoPorCep);
    }
    
}
