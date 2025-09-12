package br.com.rodrigo.ms.user_service.dtos;

public record ResponseAddressDTO(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String ibge,
    String gia,
    String ddd,
    String siafi
) {}
