//TODO Excluir essa classe se não houver relacao entre usuario e pessoa.
package rm349040.techchallenge1.domain.model;


import rm349040.techchallenge1.domain.exception.IdNullException;
import rm349040.techchallenge1.domain.exception.ParenteJaEstaRelacionadoAoUsuarioException;

import java.util.Collection;
import java.util.HashSet;

public class Usuario extends Pessoa{

    private Collection<Pessoa> meusParentes;

    public Usuario() {
        super();
        meusParentes = new HashSet<>();
    }

    //TODO checar se uma pessoa com id nulo depois de ser inserida será automaticamente salva pela JPA
    public void relacionarParente(Pessoa parente){

        if(parente.getId() == null) throw new IdNullException("Erro ao relacionar: Id do(a) parente não pode ser null.");

        if( ! meusParentes.add(parente) ){

            String exMsg="Erro ao relacionar: a(o) "+(parente.getParentesco()!=null?parente.getParentesco().toString():"pessoa")
                    +" id="+parente.getId() + " já está relacionada(o) ao usuário id="+getId();

            throw new ParenteJaEstaRelacionadoAoUsuarioException(exMsg);
        }

    }

    //TODO checar se a lista, depois de ser removi
    // da parente, será automaticamente atualizada pela JPA
    public void desrelacionarParente(Pessoa parente) {

        if (parente.getId() == null) throw new IdNullException("Erro ao desrelacionar parente do usuario " +
                (this.getId() != null ? "id= " + this.getId().toString() : "") + " : Id do(a) parente não pode ser null.");

        if (!meusParentes.remove(parente)) {

            String exMsg = "Erro ao desrelacionar: a(o) " + (parente.getParentesco() != null ? parente.getParentesco().toString() : " pessoa")
                    + " id=" + parente.getId() + " NÃO está relacionada ao usuário "
                    + (this.getId() != null ? this.getId().toString() : "");

            throw new ParenteJaEstaRelacionadoAoUsuarioException(exMsg);
        }
    }

    public static void main(String[] args) {

        Pessoa p = Pessoa.builder().id(123l).build();
        Pessoa p2 = Pessoa.builder().id(999l).build();
        Usuario usuario = new Usuario();
        usuario.setId(111000l);

        p.setParentesco(Parentesco.MAE);
        usuario.relacionarParente(p);

        usuario.desrelacionarParente(p);
        usuario.desrelacionarParente(p);

    }

}
