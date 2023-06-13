package rm349040.techchallenge1.apis.pessoas.controller.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DadosCadastroPessoa(


        @NotBlank(message = "ERRO: o nome NÃO pode ser nulo ou em BRANCO")
        @Size(max = 60, message = "ERRO: o nome da pessoa NÃO pode conter mais do que 60 chars")
        String nome,
        @NotNull(message = "ERRO: A data de nascimento NÃO pode ser nula")
        @Past(message = "ERR0: A data de nascimento NÃO pode ser hoje ou qualquer outro dia depois de hoje")
        LocalDate nascimento,

        @NotNull(message = "ERRO: O sexo NÃO pode ser nulo")
        Sexo sexo,

        @NotNull(message = "ERRO: o parentesco NÃO pode ser nulo ")
        Parentesco parentesco


) {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        //System.out.println(LocalDate.of());
    }

}
