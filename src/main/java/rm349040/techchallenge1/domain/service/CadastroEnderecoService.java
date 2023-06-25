package rm349040.techchallenge1.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rm349040.techchallenge1.domain.exception.ApiException;
import rm349040.techchallenge1.domain.model.Endereco;
import rm349040.techchallenge1.domain.repository.Repositorio;
import rm349040.techchallenge1.util.Mapper;
import rm349040.techchallenge1.util.Messages;
import rm349040.techchallenge1.util.Validation;
import rm349040.techchallenge1.domain.exception.DomainException;

import java.util.Set;

@Service
public class CadastroEnderecoService {

    @Autowired
    private Repositorio<Endereco> repositorio;

    @Autowired
    private Validation validation;

    @Autowired
    private Mapper mapper;

    public Endereco criar(Endereco endereco) {

        return repositorio.save(endereco);


//        try {
//
//            validation.throwExceptionIfDataIsWrong(dados);
//
//            try {
//
//                Endereco endereco = repositorio.save(dados.toEndereco());
//
//                return endereco;
//
//            } catch (Exception e) {
//
//                throw new ApiException(Messages.ERRO_CRIAR(Endereco.class.getSimpleName()), e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
//
//            }
//
//        } catch (ApiValidationException apiException) {
//
//            throw new ApiException(Messages.ERRO_CRIAR(Endereco.class.getSimpleName()), apiException.getMessage(), HttpStatus.BAD_REQUEST.value());
//
//        }

    }
   public Endereco atualizarOuFalhar(Endereco atual){

        if(atual == null){
            throw new DomainException("O Endereço não pode ser nulo");
        }

        if( atual.getId() == null){
            throw new DomainException(("O id do Endereço não pode ser nulo."));
        }

        Endereco endereco = repositorio.getReferenceById(atual.getId()).orElseThrow(
                                 () -> {return new DomainException("Endereço não atualizado, pois o id "
                                                                        +atual.getId()+" não existia na base de dados");});

        //simula atualizacao
        mapper.identify(atual,endereco);


        return endereco;
   }

    public void excluir(Long id) {

        try {

            repositorio.deleteById(id).orElseThrow(() -> new ApiException(
                    Messages.ERRO_EXCLUIR(Endereco.class.getSimpleName()),
                    Messages.ERRO_EXCLUIR(Endereco.class.getSimpleName(), id),
                    HttpStatus.NOT_FOUND.value()));

        }catch (ApiException apiException){

            throw apiException;

        }catch (Exception e){

            throw new ApiException(e.getMessage(),Messages.ERRO_ID_NULO(Endereco.class.getSimpleName()), HttpStatus.BAD_REQUEST.value());

        }


    }

    public Set<Endereco> listar() {
        return repositorio.findAll();
    }

    public Endereco listarById(Long id) {

        return repositorio.getReferenceById(id).orElseThrow(() -> new ApiException(
                Messages.NAO_ENCONTRADO(Endereco.class.getSimpleName()),
                Messages.NAO_ENCONTRADO(Endereco.class.getSimpleName(), id),
                HttpStatus.NOT_FOUND.value()));

    }
}