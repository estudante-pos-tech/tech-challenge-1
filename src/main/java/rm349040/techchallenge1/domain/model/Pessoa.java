package rm349040.techchallenge1.domain.model;

import lombok.*;
import rm349040.techchallenge1.domain.BASE;
import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.exception.UsuarioJaEstaRelacionadoAPessoaException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Pessoa extends BASE<Pessoa> {

    private Long id;
    private String nome;
    private LocalDate nascimento;
    private Sexo sexo;
    private Parentesco parentesco;

    private Collection<Usuario> meusUsuarios;


    public void relacionarUsuario(Usuario usuario){

        if(usuario.getId() == null) throw new IdNullException("Erro ao relacionar: Id do(a) usuário não pode ser null.");


        if(meusUsuarios == null){
            meusUsuarios = new HashSet<>();
        }

        if( ! meusUsuarios.add(usuario) ){

            String exMsg="Erro ao relacionar: usuario id="+usuario.getId()
                    + " já está relacionada(o) a esta pessoa id="+this.getId();

            throw new UsuarioJaEstaRelacionadoAPessoaException(exMsg);
        }

    }

    public void desrelacionarUsuario(Usuario usuario) {

        if (usuario.getId() == null) throw new IdNullException("Erro ao desrelacionar usuário da pessoa " +
                (this.getId() != null ? "id= " + this.getId().toString() : "") + " : id do usuário não pode ser null.");

        if(meusUsuarios == null){
            meusUsuarios = new HashSet<>();
        }

        if ( ! meusUsuarios.remove(usuario) ) {

            String exMsg = "Erro ao desrelacionar: o usuário id=" + usuario.getId() + " NÃO está relacionado à pessoa "
                    + (this.getId() != null ? this.getId().toString() : "");

            throw new UsuarioJaEstaRelacionadoAPessoaException(exMsg);
        }
    }


    /**
     * @param pessoa
     */
    @Override
    public void atualizarDados(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.nascimento = pessoa.getNascimento();
        this.sexo = pessoa.getSexo();
        this.parentesco = pessoa.getParentesco();
    }


    public static void main(String[] args) {

        Usuario u = new Usuario();
        u.setId(999l);

        Usuario u2 = new Usuario();
        u2.setId(888l);

        Pessoa p = Pessoa.builder().id(1111l).parentesco(Parentesco.AVÓ).build();

        p.relacionarUsuario(u);
        p.relacionarUsuario(u2);

        p.desrelacionarUsuario(u2);
        p.desrelacionarUsuario(u2);
    }

 }
