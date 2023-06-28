package rm349040.techchallenge1.api.dtos.eltrodomesticos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import rm349040.techchallenge1.domain.model.Eletrodomestico;

public record DadosAtualizarEletrodomestico(


        @NotNull(message = "ERRO: o ID NÃO pode ser nulo")
        Long id,

        @NotBlank(message = "ERRO: o nome NÃO pode ser nulo ou em BRANCO")
        @Size(max = 60, message = "ERRO: o nome do eletrodoméstico NÃO pode conter mais do que 60 chars")
        String nome,

        @NotBlank(message = "ERRO: o modelo NÃO pode ser nulo ou em BRANCO")
        @Size(max = 60, message = "ERRO: o modelo NÃO pode conter mais do que 60 chars")
        String modelo,

        @NotBlank(message = "ERRO: a potência NÃO pode ser nula ou em BRANCO")
        @Size(max = 30, message = "ERRO: a potência NÃO pode conter mais do que 30 chars")
        String potencia


) {}
