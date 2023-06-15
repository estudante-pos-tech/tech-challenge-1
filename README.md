# tech-challenge-1 
Implementação ***CRUD** Java Rest Apis*.<br>
Os endpoints são endereco, pessoa e eletrodomestico
## Relatório Técnico

Debian host, Oracle virtual machine, bash scripting, git, IDE Intellij, maven, String Boot, Spring MVC, Spring DevTools, Lombok são as ferramentas e tecnologias usadas para desenvolver as apis rest.
<br><br>**Não está implementada persistência de dados**. Os dados que o app cria e manipula residem na memória volátil e **não são persistentes**. Estes dados ficam armazenados numa estrututura de dados java do tipo Set (conjunto). 
<br><br>Um **repositório** é usado para acessar uma coleção (Set) de objetos, simulando **CRUD**. 
<br><br>**Soluções genéricas** são excelentes, quando evoluimos uma app. Neste projeto **tech-challenge-1**, o **repositorio** implementado usa **java generics**. O código para fazer o *CRUD* é parametrizado pelos tipos Endereco, Pessoa e Eletromestico. *Instâncias de Repositorio<T>*, **com escopo prototype**, são criadas e gerenciadas pelo Spring e são injetadas em cada um dos controllers respectivos.
<br><br>**Requests corretas** aos endpoints tem **responses** descritas na **Documentação das APIs**<br>
**Requests incorretas** aos endpoints tem **response** : ***erro + causa do erro*** . Este comportamento é implementado usando ***exception handlers GLOBAIS*** e ***validações LOCAIS*** em cada endpoint.<br>
O mecanismo de captura de erros GLOBAL foi instalado na classe [AppConfiguration.java](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/config/AppConfiguration.java), anotando esta classe com a **@ControllerAdvice** annotation do Spring.
              

##
## Documentação das APIs
O ***CRUD*** foi implementado seguindo o mapa : 
-  VERBO HTTP **POST** - **CRIAR**
-  VERBO HTTP **PUT** - **ATUALIZAR**
-  VERBO HTTP **DELETE** - **DELETAR**
-  VERBO HTTP **GET** - **LISTAR**
<br><br>
### Requests corretas
#### Endpoint endereco : REQUESTS, Curls, RESPONSES

  **POST** http://localhost:8080/endereco<br>
    Content-Type: application/json
    
    {
      "rua": "rua bela",
      "numero": "234",
      "bairro": "bairro",
      "cidade": "Maya",
      "estado": "SP"
    }
    
    curl -X POST --location "http://localhost:8080/endereco" -H "Content-type:application/json" -d '{"rua":"rua bela", "numero":"234", "bairro":"bairro", "cidade":"Maya","estado":"SP"}'
    SUCESSO: ao criar Endereco
    
   **GET** http://localhost:8080/endereco
    
    curl -X GET --location "http://localhost:8080/endereco"
    [{"id":1275424829065256685,"rua":"rua bela","numero":"234","bairro":"bairro","cidade":"Maya","estado":"SP"}]
    
    
    
   **PUT** http://localhost:8080/endereco<br>
    Content-Type: application/json
    
    {
      "id": "1275424829065256685",
      "rua": "rua belissima",
      "numero": "890234",
      "bairro": "brejo-bairro",
      "cidade": "Mayaporã",
      "estado": "AM"
    }
    
    curl -X PUT --location "http://localhost:8080/endereco" -H "Content-type:application/json" -d '{"id":"1275424829065256685", "rua":"rua belissima",  "numero":"890234", "bairro":"brejo-bairro", "cidade":"Mayaporã","estado":"AM"}'
    {
      "id": "1275424829065256685",
      "rua": "rua belissima",
      "numero": "890234",
      "bairro": "brejo-bairro",
      "cidade": "Mayaporã",
      "estado": "AM"
    }
    
    
   **DELETE** http://localhost:8080/endereco/1275424829065256685
    
    curl -X DELETE --location "http://localhost:8080/endereco/1275424829065256685"
    SUCESSO: ao excluir Endereco 1275424829065256685


#### Endpoint pessoa : REQUESTS, Curls, RESPONSES

    
   **POST** http://localhost:8080/pessoa<br>
    Content-Type: application/json
    
    {
      "nome": "ana bela",
      "nascimento": "2020-01-01",
      "sexo": "FEMININO",
      "parentesco": "MAE"
    }
    
    curl -X POST --location "http://localhost:8080/pessoa" -H "Content-type:application/json" -d '{"nome":"ana bela", "nascimento":"2020-01-01", "sexo":"FEMININO", "parentesco":"MAE"}'
    SUCESSO: ao criar Pessoa
    
    
   **GET** http://localhost:8080/pessoa<br>
    
    curl -X GET --location "http://localhost:8080/pessoa"
    [{"id":6196618678884909927,"nome":"ana bela","nascimento":"2020-01-01","sexo":"FEMININO","parentesco":"MAE"}]
    
    
    
   **PUT** http://localhost:8080/pessoa<br>
    Content-Type: application/json
    
    {
      "id": "6196618678884909927",
      "nome": "Zuleica bela",
      "nascimento": "2010-01-01",
      "sexo": "FEMININO",
      "parentesco": "CONJUGE"
    }
    
    curl -X PUT --location "http://localhost:8080/pessoa" -H "Content-type:application/json" -d '{"id":"6196618678884909927", "nome":"Zuleica bela", "nascimento":"2010-01-01", "sexo":"FEMININO", "parentesco":"CONJUGE"}'
    {"id":6196618678884909927,"nome":"Zuleica bela","nascimento":"2010-01-01","sexo":"FEMININO","parentesco":"CONJUGE"}
    
    
   **DELETE** http://localhost:8080/pessoa/1401389624475652749
    
    curl -X DELETE --location "http://localhost:8080/pessoa/6196618678884909927"
    SUCESSO: ao excluir Pessoa 6196618678884909927

#### Endpoint eletrodomestico : REQUESTS, Curls, RESPONSES

  
   **POST** http://localhost:8080/eletrodomestico<br>
     Content-Type: application/json

    {
      "nome": "eletrodomestico",
      "modelo": "casa-casa",
      "potencia": "0.2 KWh"
    }

    curl -X POST --location "http://localhost:8080/eletrodomestico" -H "Content-type:application/json" -d '{"nome":"eletrodomestico", "modelo":"casa-casa", "potencia":"0.2 KWh"}'
    SUCESSO: ao criar Eletrodomestico


  **GET** http://localhost:8080/eletrodomestico
    
    curl -X GET --location "http://localhost:8080/eletrodomestico"
    [{"id":8393364629003825317,"nome":"eletrodomestico","modelo":"casa-casa","potencia":"0.2 KWh"}]
    


   **PUT** http://localhost:8080/eletrodomestico<br>
    Content-Type: application/json
    
    {
      "id": "8393364629003825317",
      "nome": "rural-eletro",
      "modelo": "casa-rural",
      "potencia": "0.1 KWh"
    }
    
    curl -i -X PUT --location "http://localhost:8080/eletrodomestico" -H "Content-type:application/json" -d '{"id":"8393364629003825317","nome":"rural-eletro", "modelo":"casa-rural", "potencia":"0.1 KWh"}'
    {
      "id": "8393364629003825317",
      "nome": "rural-eletro",
      "modelo": "casa-rural",
      "potencia": "0.1 KWh"
    }


   **DELETE** http://localhost:8080/eletrodomestico/8393364629003825317
    
    curl -X DELETE --location "http://localhost:8080/eletrodomestico/8393364629003825317"
    SUCESSO: ao excluir Eletrodomestico 8393364629003825317

### Requests incorretas

Qualquer e toda request às apis que não esteja na forma indicada na documentação das apis, resultará numa *java exception*.
<br> Essa exception será capturada pelo **exception handler GLOBAL**. O handler criará uma mensagem genérica que será retornada ao cliente da api. O cliente será **informando** sobre o **erro e a causa do erro**, numa *linguagem técnica*. 
<br><br>Requests que **violem as restrições (constraints)** programadas, serão interrompidas na **camada de validação** e mensagem de erro adequada será retornada ao cliente da api.


## Desafio técnico
Se fosse possível ao Spring *mapear* **um subconjunto de requests** a *apenas 1 determinado método* e se fosse possível *mapear* determindado DTO *a uma determinada request*, então, **ao invés de se ter 3 controllers** (EnderecoController, PessoaController, EletrodomesticoController), **seria possivel ter apenas 1 controller genérico**. Essa solução simplificaria, enxugaria, tornaria ++ fácil de evoluir o app.<br>
Precisa ser estudado se o **Spring AOP (Aspect Oriented Programming)** suporta os mapeamentos.


