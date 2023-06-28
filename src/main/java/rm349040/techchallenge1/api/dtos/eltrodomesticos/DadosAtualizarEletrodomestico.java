package rm349040.techchallenge1.api.dtos.eltrodomesticos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosAtualizarEletrodomestico(


        @NotNull(message = "ERRO: o ID NÃO pode ser nulo")
        Long id,

        @NotBlank(message = "O nome NÃO pode ser nulo ou em BRANCO")
        @Size(max = 60, message = "O nome do eletrodoméstico NÃO pode conter mais do que 60 chars")
        String nome,

        @NotBlank(message = "O modelo NÃO pode ser nulo ou em BRANCO")
        @Size(max = 60, message = "O modelo NÃO pode conter mais do que 60 chars")
        String modelo,

        @NotBlank(message = "A potência NÃO pode ser nula ou em BRANCO")
        @Size(max = 30, message = "A potência NÃO pode conter mais do que 30 chars")
        String potencia


) {}
