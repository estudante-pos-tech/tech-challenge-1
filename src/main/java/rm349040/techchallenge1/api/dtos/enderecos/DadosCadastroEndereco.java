package rm349040.techchallenge1.api.dtos.enderecos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record DadosCadastroEndereco(

        @NotBlank
        @Size(max = 60)
        String rua,
        @NotBlank()
        @Size(max = 10)
        String numero,
        @NotBlank
        @Size(max = 40)
        String bairro,
        @NotBlank
        @Size(max = 50)
        String cidade,
        @NotBlank
        @Size(max = 30)
        String estado


) {}
