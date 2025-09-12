package br.com.rodrigo.ms.user_service.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.rodrigo.ms.user_service.dtos.ResponseAddressDTO;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepProxy {
    
    @GetMapping("/{cep}/json")
    ResponseAddressDTO getEnderecoPorCep(@PathVariable("cep") String cep);
}
