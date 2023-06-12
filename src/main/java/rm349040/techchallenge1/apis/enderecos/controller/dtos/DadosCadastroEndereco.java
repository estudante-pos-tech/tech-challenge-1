package rm349040.techchallenge1.apis.enderecos.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import rm349040.techchallenge1.apis.enderecos.dominio.Endereco;

public record DadosCadastroEndereco(

        @NotBlank(message = "ERRO: a rua NÃO pode ser nula ou em BRANCO")
        @Size(max = 60, message = "ERRO: o nome da rua NÃO pode conter mais do que 60 chars")
        String rua,
        @NotBlank(message = "ERRO: o número da rua NÃO pode ser nulo ou em BRANCO")
        @Size(max = 10, message = "ERRO: o número da rua NÃO pode conter mais do que 10 chars")
        String numero,
        @NotBlank(message = "ERRO: o bairro NÃO pode ser nulo ou em BRANCO")
        @Size(max = 40, message = "ERRO: o nome do bairro NÃO pode conter mais do que 40 chars")
        String bairro,
        @NotBlank(message = "ERRO: a cidade NÃO pode ser nula ou em BRANCO")
        @Size(max = 50, message = "ERRO: o nome da cidade NÃO pode conter mais do que 50 chars")
        String cidade,
        @NotBlank()
        @Size(max = 30, message = "ERRO: o nome do estado NÃO pode conter mais do que 30 chars")
        String estado


) {
        public Endereco toEndereco() {
                return new Endereco(null,rua,numero,bairro,cidade,estado);
        }
}
