package rm349040.techchallenge1.apis.enderecos.controller.dtos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEndereco(


        @NotBlank(message = "ERRO: a rua NÃO pode ser nula ou em BRANCO")
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado


) {
}
