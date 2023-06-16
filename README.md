# tech-challenge-1 
Implementação ***CRUD** Java Rest Apis*.<br>
Os endpoints são endereco, pessoa e eletrodomestico
## Relatório Técnico
<div style='text-align: justify;'>
  
Debian host, Oracle virtual machine, bash scripting, git, IDE Intellij, maven, Spring Boot, Spring MVC, Spring DevTools, Lombok são as ferramentas e tecnologias usadas para desenvolver as apis rest.
<br><br>**Não está implementada persistência de dados**. Os dados que o app cria e manipula residem na memória volátil e **não são persistentes**. Estes dados ficam armazenados numa estrututura de dados java do tipo Set (conjunto). 
<br><br>Um [**repositório**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/repository/Repositorio.java) é usado para acessar e manipular uma coleção (Set) de objetos, simulando **CRUD**. 
<br><br>**Soluções genéricas** são excelentes, quando evoluimos uma app. Neste projeto **tech-challenge-1**, o [**repositorio**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/repository/Repositorio.java) implementado usa **java generics**. O código para fazer o *CRUD* é parametrizado pelos tipos Endereco, Pessoa e Eletromestico. *Instâncias de Repositorio<T>*, **com escopo prototype**, são criadas e gerenciadas pelo Spring e são injetadas em cada um dos controllers respectivos.
<br><br>**Não está implementado i18n**. Toda e qualquer mensagem ao cliente das apis está **hard-coded**; as mensagens ou estão na classe Message.java ou nos DTOs, através das ***bean validations***.  
<br>**Requests corretas** aos endpoints tem **responses** descritas na **Documentação das APIs**<br>
**Requests incorretas** aos endpoints tem **response** : ***erro + causa do erro*** . Este comportamento é implementado usando ***exception handlers GLOBAIS*** e ***validações LOCAIS*** em cada endpoint.<br>
O mecanismo de captura de erros GLOBAL foi instalado na classe [AppConfiguration.java](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/config/AppConfiguration.java), anotando esta classe com a **@ControllerAdvice** annotation do Spring.
              

</div>

##
## Documentação das APIs
O ***CRUD*** foi implementado seguindo o mapa : 
-  VERBO HTTP **POST** - **CRIAR**
-  VERBO HTTP **PUT** - **ATUALIZAR**
-  VERBO HTTP **DELETE** - **DELETAR**
-  VERBO HTTP **GET** - **LISTAR**
<br><br>
### Requests corretas
___
#### Endpoint endereco : REQUESTS, Curls, RESPONSES
___
##### **POST**<br><br>
No body da **POST** request, devem estar os pares key-value: 
  -    ***rua*** , *não em-branco e no máximo 60 caracteres* 
  -    ***numero***, *não em-branco e no máximo 10 caracteres*
  -    ***bairro***, *não em-branco e no máximo 40 caracteres*
  -    ***cidade***, *não em-branco e no máximo 50 caracteres*
  -    ***estado***, *não em-branco e no máximo 30 caracteres*

*EXEMPLO:*   

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


___

##### **GET**<br><br>
    
   **GET** http://localhost:8080/endereco
    
    curl -X GET --location "http://localhost:8080/endereco"
    [{"id":1275424829065256685,"rua":"rua bela","numero":"234","bairro":"bairro","cidade":"Maya","estado":"SP"}]


___    

##### **PUT**<br><br>
    
  No body da **PUT** request, devem estar os pares key-value: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
  -    ***rua*** , *não em-branco e no máximo 60 caracteres* 
  -    ***numero***, *não em-branco e no máximo 10 caracteres*
  -    ***bairro***, *não em-branco e no máximo 40 caracteres*
  -    ***cidade***, *não em-branco e no máximo 50 caracteres*
  -    ***estado***, *não em-branco e no máximo 30 caracteres*

*EXEMPLO:*    

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


___    
<br>

##### **DELETE**<br><br>

No path da **DELETE** request, deve estar o ***id*** do recurso que se deseja deletar: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
    
   **DELETE** http://localhost:8080/endereco/1275424829065256685
    
    curl -X DELETE --location "http://localhost:8080/endereco/1275424829065256685"
    SUCESSO: ao excluir Endereco 1275424829065256685

___
#### Endpoint pessoa : REQUESTS, Curls, RESPONSES
___

##### **POST**<br><br>

No body da **POST** request, devem estar os pares key-value: 
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***nascimento***, *não-nulo e NÃO pode ser "hoje" ou qualquer outro dia depois de "hoje"*
  -    ***sexo***, *não-nulo e MASCULINO ou FEMININO*
  -    ***parentesco***, *não-nulo e um dos valores : IRMÃO, IRMÃ, PAI, MAE, FILHO , FILHA, AVÔ, AVÓ, CUNHADA, SOGRA, CUNHADO, SOGRO, AGREGADO, NAMORADA,NOMORADO, CONJUGE, TIA, TIO*

*EXEMPLO:*   

    
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

___

  ##### **GET**<br><br>
    
   **GET** http://localhost:8080/pessoa<br>
    
    curl -X GET --location "http://localhost:8080/pessoa"
    [{"id":6196618678884909927,"nome":"ana bela","nascimento":"2020-01-01","sexo":"FEMININO","parentesco":"MAE"}]
    
___

  ##### **PUT**<br><br>

No body da **PUT** request, devem estar os pares key-value: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***nascimento***, *não-nulo e NÃO pode ser "hoje" ou qualquer outro dia depois de "hoje"*
  -    ***sexo***, *não-nulo e MASCULINO ou FEMININO*
  -    ***parentesco***, *não-nulo e um dos valores : IRMÃO, IRMÃ, PAI, MAE, FILHO , FILHA, AVÔ, AVÓ, CUNHADA, SOGRA, CUNHADO, SOGRO, AGREGADO, NAMORADA,NOMORADO, CONJUGE, TIA, TIO*

*EXEMPLO:*  
    
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

___

  ##### **DELETE**<br><br>

  No path da **DELETE** request, deve estar o ***id*** do recurso que se deseja deletar: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
        
   **DELETE** http://localhost:8080/pessoa/1401389624475652749
    
    curl -X DELETE --location "http://localhost:8080/pessoa/6196618678884909927"
    SUCESSO: ao excluir Pessoa 6196618678884909927

___
#### Endpoint eletrodomestico : REQUESTS, Curls, RESPONSES
___

##### **POST**<br><br>

No body da **POST** request, devem estar os pares key-value: 
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***modelo***, *não em-branco e no máximo 60 caracteres*
  -    ***potencia***, *não em-branco e no máximo 30 caracteres*     

*EXEMPLO:*  
  
   **POST** http://localhost:8080/eletrodomestico<br>
     Content-Type: application/json

    {
      "nome": "eletrodomestico",
      "modelo": "casa-casa",
      "potencia": "0.2 KWh"
    }

    curl -X POST --location "http://localhost:8080/eletrodomestico" -H "Content-type:application/json" -d '{"nome":"eletrodomestico", "modelo":"casa-casa", "potencia":"0.2 KWh"}'
    SUCESSO: ao criar Eletrodomestico

___

##### **GET**<br><br>

  **GET** http://localhost:8080/eletrodomestico
    
    curl -X GET --location "http://localhost:8080/eletrodomestico"
    [{"id":8393364629003825317,"nome":"eletrodomestico","modelo":"casa-casa","potencia":"0.2 KWh"}]
    
___

##### **PUT**<br><br>

No body da **PUT** request, devem estar os pares key-value:
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***modelo***, *não em-branco e no máximo 60 caracteres*
  -    ***potencia***, *não em-branco e no máximo 30 caracteres*     

*EXEMPLO:* 

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

___

##### **DELETE**<br><br>

No path da **DELETE** request, deve estar o ***id*** do recurso que se deseja deletar: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
    
   **DELETE** http://localhost:8080/eletrodomestico/8393364629003825317
    
    curl -X DELETE --location "http://localhost:8080/eletrodomestico/8393364629003825317"
    SUCESSO: ao excluir Eletrodomestico 8393364629003825317

### Requests incorretas

<div style='text-align: justify;'>
  
Qualquer e toda request às apis que não esteja na forma indicada na documentação das apis, resultará numa *java exception*.
<br><br> **Ou** esta exception será capturada pelo **exception handler GLOBAL; ou** ela será capturada pela camada de validação.<br> 
<br>Se acontecer de a exception cair no GLOBAL, então o handler criará uma mensagem genérica que será retornada ao cliente da api. O cliente será **informando** sobre o **erro e a causa do erro**, numa *linguagem técnica*. 
<br><br>Se a exception for capturada pela validação, as requests que **violaram as restrições (constraints)** programadas, serão interrompidas nesta **camada de validação** e uma mensagem de erro adequada será retornada ao cliente da api.

</div>

## Desafio técnico
<div style='text-align: justify;'>
  
Se fosse possível ao Spring *mapear* **um subconjunto específico de requests** (por exemple, {GET http://localhost:8080/endereco , GET http://localhost:8080/pessoa , GET http://localhost:8080/eletrodomestico}) para ***APENAS** 1 determinado método* e se fosse possível *mapear* determinado DTO *a uma determinada request*, então, **ao invés de se ter 3 controllers** (EnderecoController, PessoaController, EletrodomesticoController), **seria possivel ter apenas 1 controller genérico**. Essa solução simplificaria, enxugaria, tornaria ++ fácil de evoluir o app.<br>
Precisa ser estudado se o **Spring AOP (Aspect Oriented Programming)** suporta os mapeamentos.

</div>

