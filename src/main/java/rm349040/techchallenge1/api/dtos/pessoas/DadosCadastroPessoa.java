package rm349040.techchallenge1.api.dtos.pessoas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import rm349040.techchallenge1.domain.model.Parentesco;
import rm349040.techchallenge1.domain.model.Sexo;

import java.time.LocalDate;

public record DadosCadastroPessoa(


        @NotBlank(message = "O nome da pessoa NÃO pode ser nulo ou em BRANCO")
        @Size(max = 60, message = "O nome da pessoa NÃO pode conter mais do que 60 chars")
        String nome,
        @NotNull(message = "A data de nascimento NÃO pode ser nula")
        @Past(message = "A data de nascimento NÃO pode ser hoje ou qualquer outro dia depois de hoje")
        LocalDate nascimento,

        @NotNull(message = "O sexo NÃO pode ser nulo")
        Sexo sexo,
        @NotNull(message = "O parentesco NÃO pode ser nulo ")
        Parentesco parentesco


) {}
