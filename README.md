# tech-challenge-1 
Implementação ***CRUD** Java Rest Apis*.<br>
Os endpoints são enderecos, pessoas e eletrodomesticos
## Relatório Técnico
<div style='text-align: justify;'>
  
Debian host, Oracle virtual machine, bash scripting, git, IDE Intellij, maven, Spring Boot, Spring MVC, Spring DevTools, Lombok, curl, Insomnia e Postman são as ferramentas e tecnologias usadas para desenvolver as apis rest.
<br><br>**Não está implementada persistência de dados**. Os dados que o app cria e manipula residem na memória volátil e **não são persistentes**. Estes dados ficam armazenados numa estrututura de dados java do tipo Set (conjunto). 
<br><br>Um [**repositório**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/repository/Repositorio.java) é usado para acessar e manipular uma coleção (Set) de objetos, simulando **CRUD**. 
<br><br>**Soluções genéricas** podem ser excelentes, quando evoluimos uma app. <br><br>
Neste projeto **tech-challenge-1**, o [**repositorio**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/repository/Repositorio.java) e o [**serviço de cadastro**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/domain/service/CadastroService.java) implementados usam **java generics**. O código para fazer o *CRUD* é parametrizado pelos tipos Endereco, Pessoa e Eletromestico. *Instâncias de CadastroService<T> e Repositorio<T>* são criadas e gerenciadas pelo Spring e são injetadas em cada um dos controllers e repositórios correspondentes.
<br><br>
**Requests corretas** aos endpoints tem **responses** descritas na **Documentação das APIs**
<br><br>**Requests incorretas** aos endpoints recebem **Http status codes** conformes a uma rest api e o body da resposta padronizado de acordo com a **RFC 7807 - Problem Detail for Http Apis**. <br>
Por exemplo, a request abaixo tenta atualizar um recurso inexistente:
<br>

            PUT api.tech-challenge/eletrodomesticos/386340500511945<br>
            Content-Type: application/json
            
            {
                "nome": "RURAL-RURAL-eletrodomesticos",
                "modelo": "RURAL RURAL",
                "potencia": "10.39"
            }
ela recebe uma response que estende a **RFC 7807 - Problem Detail for Http Apis**
<br>

            {
                "status": 404,
                "timeStamp": "2023-06-29T14:25:25.083893926Z",
                "type": "https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/documentation/recurso-nao-encontrado",
                "title": "Recurso não encontrado",
                "detail": "Eletrodomestico não atualizado(a), pois o id 386340500511945 não existia na base de dados. Tentando te ajudar ... passe um id que exista na base de dados que daí você poderá receber o que solicita."
            }

<br>**Todo e qualquer** problema/erro é representado no padrão **RFC 7807 - Problem Detail for Http Apis**.
<br>As responses nesse formato tentarão ao máximo ajudar o usuário da API a ter uma experiência o mais amigável possível.


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
#### Endpoint enderecos : REQUESTS, Curls, RESPONSES
___
##### **POST**<br><br>
No body da **POST** request, devem estar os pares key-value: 
  -    ***rua*** , *não em-branco e no máximo 60 caracteres* 
  -    ***numero***, *não em-branco e no máximo 10 caracteres*
  -    ***bairro***, *não em-branco e no máximo 40 caracteres*
  -    ***cidade***, *não em-branco e no máximo 50 caracteres*
  -    ***estado***, *não em-branco e no máximo 30 caracteres*

*EXEMPLO:*   

**POST** api.tech-challenge/enderecos <br>
    Content-Type: application/json
    
    {
      "rua": "rua bela",
      "numero": "234",
      "bairro": "bairro",
      "cidade": "Maya",
      "estado": "SP"
    }
    
    curl -i -X POST --location "api.tech-challenge/enderecos" -H "Content-type:application/json" -d '{"rua":"rua bela", "numero":"234", "bairro":"bairro", "cidade":"Maya","estado":"SP"}'
    
    HTTP/1.1 201
    Content-Type: application/json
         
    {"id":1275424829065256685,"rua":"rua bela","numero":"234","bairro":"bairro","cidade":"Maya","estado":"SP"}


___

##### **GET**<br><br>

  ###### **GET ALL**<br><br>
    
   **GET** api.tech-challenge/enderecos

    curl -i -X GET --location "api.tech-challenge/enderecos"
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    [{"id":1275424829065256685,"rua":"rua bela","numero":"234","bairro":"bairro","cidade":"Maya","estado":"SP"}]

  ###### **GET BY ID**<br><br>

    **GET** api.tech-challenge/enderecos/1275424829065256685

    curl -i -X GET --location "api.tech-challenge/enderecos/1275424829065256685"
    HTTP/1.1 200
    Content-Type: application/json
    
    {"id":1275424829065256685,"rua":"rua bela","numero":"234","bairro":"bairro","cidade":"Maya","estado":"SP"}

___    

##### **PUT**<br><br>

  Na url da **PUT** request deve estar o id do recurso
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
  
  No body da **PUT** request, devem estar os pares key-value: 
  
  -    ***rua*** , *não em-branco e no máximo 60 caracteres* 
  -    ***numero***, *não em-branco e no máximo 10 caracteres*
  -    ***bairro***, *não em-branco e no máximo 40 caracteres*
  -    ***cidade***, *não em-branco e no máximo 50 caracteres*
  -    ***estado***, *não em-branco e no máximo 30 caracteres*

*EXEMPLO:*    

   **PUT** api.tech-challenge/enderecos/1275424829065256685<br>
    Content-Type: application/json
    
    {
       "rua": "rua belissima",
      "numero": "890234",
      "bairro": "brejo-bairro",
      "cidade": "Mayaporã",
      "estado": "AM"
    }
    
    curl -i -X PUT --location "api.tech-challenge/enderecos/1275424829065256685" -H "Content-type:application/json" -d '{"rua":"rua belissima",  "numero":"890234", "bairro":"brejo-bairro", "cidade":"Mayaporã","estado":"AM"}'
    
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked

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
    
   **DELETE** api.tech-challenge/enderecos/1275424829065256685
    
    curl -i -X DELETE --location "api.tech-challenge/enderecos/1275424829065256685"
    HTTP/1.1 204
         
___
#### Endpoint pessoas : REQUESTS, Curls, RESPONSES
___

##### **POST**<br><br>

No body da **POST** request, devem estar os pares key-value: 
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***nascimento***, *não-nulo e NÃO pode ser "hoje" ou qualquer outro dia depois de "hoje"*
  -    ***sexo***, *não-nulo e MASCULINO ou FEMININO*
  -    ***parentesco***, *não-nulo e um dos valores : IRMÃO, IRMÃ, PAI, MAE, FILHO , FILHA, AVÔ, AVÓ, CUNHADA, SOGRA, CUNHADO, SOGRO, AGREGADO, NAMORADA,NOMORADO, CONJUGE, TIA, TIO*

*EXEMPLO:*   

    
   **POST** api.tech-challenge/pessoas<br>
    Content-Type: application/json
    
    {
      "nome": "ana bela",
      "nascimento": "2020-01-01",
      "sexo": "FEMININO",
      "parentesco": "MAE"
    }
    
    curl -i -X POST --location "api.tech-challenge/pessoas" -H "Content-type:application/json" -d '{"nome":"ana bela", "nascimento":"2020-01-01", "sexo":"FEMININO", "parentesco":"MAE"}'
    HTTP/1.1 201 
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 24
    
    SUCESSO: ao criar Pessoa


___

  ##### **GET**<br><br>

   ###### **GET ALL**<br><br>
    
   **GET** api.tech-challenge/pessoas<br>
    
    curl -i -X GET --location "api.tech-challenge/pessoas"
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
       
    [{"id":6196618678884909927,"nome":"ana bela","nascimento":"2020-01-01","sexo":"FEMININO","parentesco":"MAE"}]

  ###### **GET BY ID**<br><br>

    **GET** api.tech-challenge/pessoas/6196618678884909927<br>
    
    curl -i -X GET --location "api.tech-challenge/pessoas/6196618678884909927"
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
       
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
    
   **PUT** api.tech-challenge/pessoas<br>
    Content-Type: application/json
    
    {
      "id": "6196618678884909927",
      "nome": "Zuleica bela",
      "nascimento": "2010-01-01",
      "sexo": "FEMININO",
      "parentesco": "CONJUGE"
    }
    
    curl -i -X PUT --location "api.tech-challenge/pessoas" -H "Content-type:application/json" -d '{"id":"6196618678884909927", "nome":"Zuleica bela", "nascimento":"2010-01-01", "sexo":"FEMININO", "parentesco":"CONJUGE"}'
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    {"id":6196618678884909927,"nome":"Zuleica bela","nascimento":"2010-01-01","sexo":"FEMININO","parentesco":"CONJUGE"}

___

  ##### **DELETE**<br><br>

  No path da **DELETE** request, deve estar o ***id*** do recurso que se deseja deletar: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
        
   **DELETE** api.tech-challenge/pessoas/1401389624475652749
    
    curl -i -X DELETE --location "api.tech-challenge/pessoas/6196618678884909927"
    HTTP/1.1 200
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 46

    SUCESSO: ao excluir Pessoa 6196618678884909927

___
#### Endpoint eletrodomesticos : REQUESTS, Curls, RESPONSES
___

##### **POST**<br><br>

No body da **POST** request, devem estar os pares key-value: 
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***modelo***, *não em-branco e no máximo 60 caracteres*
  -    ***potencia***, *não em-branco e no máximo 30 caracteres*     

*EXEMPLO:*  
  
   **POST** api.tech-challenge/eletrodomesticos<br>
     Content-Type: application/json

    {
      "nome": "eletrodomesticos",
      "modelo": "casa-casa",
      "potencia": "0.2 KWh"
    }

    curl -i -X POST --location "api.tech-challenge/eletrodomesticos" -H "Content-type:application/json" -d '{"nome":"eletrodomesticos", "modelo":"casa-casa", "potencia":"0.2 KWh"}'
    HTTP/1.1 201 
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 33
        
    SUCESSO: ao criar Eletrodomestico

___

##### **GET**<br><br>

  ###### **GET ALL**<br><br>

  **GET** api.tech-challenge/eletrodomesticos
    
    curl -i -X GET --location "api.tech-challenge/eletrodomesticos"
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    [{"id":8393364629003825317,"nome":"eletrodomesticos","modelo":"casa-casa","potencia":"0.2 KWh"}]

   ###### **GET BY ID**<br><br>

    **GET** api.tech-challenge/eletrodomesticos/8393364629003825317
    
    curl -i -X GET --location "api.tech-challenge/eletrodomesticos/8393364629003825317"
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked
    
    [{"id":8393364629003825317,"nome":"eletrodomesticos","modelo":"casa-casa","potencia":"0.2 KWh"}]
    
___

##### **PUT**<br><br>

No body da **PUT** request, devem estar os pares key-value:
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***modelo***, *não em-branco e no máximo 60 caracteres*
  -    ***potencia***, *não em-branco e no máximo 30 caracteres*     

*EXEMPLO:* 

   **PUT** api.tech-challenge/eletrodomesticos<br>
    Content-Type: application/json
    
    {
      "id": "8393364629003825317",
      "nome": "rural-eletro",
      "modelo": "casa-rural",
      "potencia": "0.1 KWh"
    }
    
    curl -i -X PUT --location "api.tech-challenge/eletrodomesticos" -H "Content-type:application/json" -d '{"id":"8393364629003825317","nome":"rural-eletro", "modelo":"casa-rural", "potencia":"0.1 KWh"}'
    HTTP/1.1 200
    Content-Type: application/json
    Transfer-Encoding: chunked

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
    
   **DELETE** api.tech-challenge/eletrodomesticos/8393364629003825317
    
    curl -i -X DELETE --location "api.tech-challenge/eletrodomesticos/8393364629003825317"
    HTTP/1.1 200
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 55

    SUCESSO: ao excluir Eletrodomestico 8393364629003825317

### Requests incorretas

<div style='text-align: justify;'>
  
Qualquer e toda request às api que não esteja na forma indicada na documentação acima, das apis, resultará numa *java exception*.
<br><br> **Ou** esta exception será capturada pelo **exception handler GLOBAL; ou** ela será capturada pela camada de validação.<br> 
<br>Se acontecer de a exception cair no GLOBAL, então o handler criará uma mensagem genérica que será retornada ao cliente da api. O cliente será **informando** sobre o **erro e a causa do erro**, numa *linguagem técnica*. 
<br><br>Se a exception for capturada pela validação, as requests que **violaram as restrições (constraints)** programadas, serão interrompidas nesta **camada de validação** e uma mensagem de erro adequada será retornada ao cliente da api.

</div>

## Desafio técnico
<div style='text-align: justify;'>

Da maneira como estão codados os endpoints enderecos, pessoas e eletromestico, são semalhantíssimos os métodos GET, POST, PUT e DELETE, nos 3 rest controllers.<br><br> 
Por exemplo, se prestarmos atenção aos métodos GET, dos 3 rest controllers, vemos que são idênticos, em sua lógica; apenas os DTOs e os paths variam.<br><br>
Se fosse possível ao Spring *mapear* **um subconjunto específico de requests** (por exemple, {GET http://localhost:8080/enderecos , GET http://localhost:8080/pessoas , GET http://localhost:8080/eletrodomesticos}) para ***APENAS** 1 determinado método* e se fosse possível *mapear* determinado DTO *a uma determinada request*, então, **ao invés de se ter 3 REST controllers** (EnderecoController, PessoaController, EletrodomesticoController), **seria possivel ter apenas 1 rest controller genérico**. Essa solução simplificaria, enxugaria, tornaria ++ fácil de evoluir o app.<br><br>
Precisa ser estudado se o **Spring AOP (Aspect Oriented Programming)** suporta os mapeamentos.

</div>

