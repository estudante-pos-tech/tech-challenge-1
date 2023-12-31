package rm349040.techchallenge1.api.dtos.enderecos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosAtualizarEndereco(
//
//        @NotNull(message = "não pode ser nulo")
//        Long id,
        @NotBlank(message = "não pode ser nula ou em BRANCO")
        @Size(max = 60, message = "não pode conter mais do que 60 chars")
        String rua,
        @NotBlank(message = "não pode ser nulo ou em BRANCO")
        @Size(max = 10, message = "não pode conter mais do que 10 chars")
        String numero,
        @NotBlank(message = "não pode ser nulo ou em BRANCO")
        @Size(max = 40, message = "não pode conter mais do que 40 chars")
        String bairro,
        @NotBlank(message = "não pode ser nula ou em BRANCO")
        @Size(max = 50, message = "não pode conter mais do que 50 chars")
        String cidade,
        @NotBlank(message = "não pode ser nulo ou em BRANCO")
        @Size(max = 30, message = "não pode conter mais do que 30 chars")
        String estado


) {


}
