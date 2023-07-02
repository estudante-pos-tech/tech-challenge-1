# tech-challenge-1 
Implementação ***CRUD** Java Rest Api*.<br>
Os endpoints são enderecos, pessoas e eletrodomesticos
## Relatório Técnico

### Tecnologias e ferramentas utilizadas
<div style='text-align: justify;'>
  
Debian host, Oracle virtual machine, bash scripting, git, IDE Intellij, maven, Spring Boot, Spring MVC, Spring DevTools, Lombok, [model mapper](https://modelmapper.org/), [apache commons lang3](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3), curl, Insomnia e Postman são as ferramentas e tecnologias usadas para desenvolver a api rest.

### Resumo técnico. Visão panorâmica.
**Não está implementada persistência de dados**. Os dados que o app cria e manipula residem na memória volátil e **não são persistentes**. Estes dados ficam armazenados numa estrututura de dados java do tipo Set (conjunto). 
<br><br>Um [**repositório**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/domain/repository/Repositorio.java) é usado para acessar e manipular uma coleção (Set) de objetos, simulando **CRUD**. 
<br><br>**Soluções genéricas** podem ser excelentes, quando evoluimos uma app. <br><br>
Neste projeto **tech-challenge-1**, o [**repositorio**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/domain/repository/Repositorio.java) e o [**serviço de cadastro**](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/domain/service/CadastroService.java) implementados usam **java generics**. O código para fazer o *CRUD* é parametrizado pelos tipos Endereco, Pessoa e Eletromestico. *Instâncias especializadas de CadastroService<T> e Repositorio<T>* são criadas como Spring Beans e são injetadas em cada um dos controllers e repositórios correspondentes.
<br><br>Os DTOs de **input** são **Java records** . Os DTOs de **output** são **pojos**. As conversões entre DTOs e objetos de domínio e vice-versa são feitas usando o [model mapper](https://modelmapper.org/), uma biblioteca de conversão bem legal de usar e inteligente.
<br><br>
**Requests corretas** aos endpoints tem **responses** descritas na **Documentação das APIs**
<br><br>**Requests incorretas** aos endpoints recebem **Http status codes** esperados numa api rest e o body da resposta está padronizado de acordo com a **RFC 7807 - Problem Detail for Http Apis**. <br>
Por exemplo, a request abaixo tenta atualizar um recurso inexistente:
<br>

            PUT api.tech-challenge/eletrodomesticos/386340500511945
            Content-Type: application/json
            
            {
                "nome": "RURAL-RURAL-eletrodomesticos",
                "modelo": "RURAL RURAL",
                "potencia": "10.39"
            }
ela recebe um response body que extende a **RFC 7807 - Problem Detail for Http Apis**
<br>

            {
                "status": 404,
                "timeStamp": "2023-06-29T14:25:25.083893926Z",
                "type": "https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/documentation/recurso-nao-encontrado",
                "title": "Recurso não encontrado",
                "detail": "Eletrodomestico não atualizado(a), pois o id 386340500511945 não existia na base de dados. Tentando te ajudar ... passe um id que exista na base de dados que daí você poderá receber o que solicita."
            }

<br>**Todo e qualquer** problema/erro é representado no padrão **RFC 7807 - Problem Detail for Http Apis**.
<br>As responses nesse formato tentarão ao máximo ajudar o usuário da API a ter uma experiência o mais amigável possível, pois elas informam o tipo de erro ocorrido (atributos **type** e **title**), o detalhe do erro (atributo **detail**) junto com uma sugestão de correção. Além de tudo isso, o atributo **type** aponta para uma url contendo uma documentação sobre possíveis causas do erro e possíveis soluções.


</div>

### Desafios técnicos
<div style='text-align: justify;'>

Como capturar e tratar, **num mesmo lugar**, as java exceptions internas do Spring web, as java exceptions lançadas pela camada de validação da API e as exceptions lançadas pela camada de negócio ?
Esse foi o **maior** desafio dessa fase-1, pois desejava-se padronizar as respostas de erros aos usuários da API, seguindo a **RFC 7807 Problem Details for HTTP Apis**.<br>
<br>Este desafio foi resolvido criando-se a classe [GlobalExceptionHandler](https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/api/exceptionhandler/GlobalExceptionHandler.java)
que extende da classe *ResponseEntityExceptionHandler* do próprio Spring web.
Desta forma, foi possivel customizar o body das respostas de erro com as exceptions que o Spring web lança
e também customizar o body das respostas de erro com as exceptions que a camanda de validação da API lança, juntamente com as exceptions que a camada de negócio lança.
<br><br>Para se ter um contraste entre uma resposta http padrão do Spring web e uma resposta da API customizada seguindo o padrão **RFC 7807 Problem Details for HTTP Apis**, veja o exemplo :<br><br>



Depois de feita a request

       curl -i -X POST --location "api.tech-challenge/eletrodomesticos" -H "Content-type:application/json" -d '{"nome":"eletrodomesticos", "modelo":"casa-casa"}'   

<br>o Spring web responde  <br>

        {
            "type": "about:blank",
            "title": "Bad Request",
            "status": 400,
            "detail": "Invalid request content.",
            "instance": "/eletrodomesticos"
        }


<br>e a resposta customizada da API é <br>

        {
            "status": 400,
            "timeStamp": "2023-06-29T20:09:47.653039425Z",
            "type": "https://github.com/estudante-pos-tech/tech-challenge-1/blob/master/src/main/java/rm349040/techchallenge1/documentation/dado-invalido",
            "title": "Dado inválido",
            "detail": "Um ou mais campos estão inválidos. Corrija e tente novamente.",
            "userMessage": "Um ou mais campos estão inválidos. Corrija e tente novamente.",
            "fields":   [
                            {
                                "name": "potencia",
                                "userMessage": "A potência NÃO pode ser nula"
                            }
                        ]
        }
<br><br>
Na resposta customizada da API, fica descrito, no campo fields, que o campo potencia não foi
passado no body da request (ela é NOT-NULL) e essa violação foi detectada na camada de validação da API.
<br><br>A resposta customizada da API tenta ajudar o usuário informando que um dos campos está inválido.
Além disso, a resposta customizada da API fornece uma url de ajuda, onde há um documento com maiores
informações de como corrigir esse tipo de erro.

<br><br>**Outro** desafio foi o de migrar TOTALMENTE as mensagens 
das validações para **Resource Bundles**. 
<br><br>As mensagens foram colocadas, num primeiro momento, nos atributos message das annotations Bean Validations. Ali, as mensagens de erro ficaram repetitivas, duplicadas.
<br>Para reduzir essas duplicações, foi criado o **messages.properties** resource bundle que resolveu parcialmente este desafio.
<br><br>Ficou muito simples gerar mensagens de erros de validação para o tipo @NotBlank, porque as mensagens para essa constraint só carecem de apenas **1 place-holder**.<br>
Porém, para a constraint @Size, cujas mensagens necessitam de **2 place-holders**, ainda não se teve sucesso... 


</div>



##
## Documentação da API
O ***CRUD*** foi implementado seguindo o mapa : 
-  VERBO HTTP **POST** - **CRIAR**
-  VERBO HTTP **PUT** - **ATUALIZAR**
-  VERBO HTTP **DELETE** - **DELETAR**
-  VERBO HTTP **GET** - **LISTAR**
<br><br>
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

        POST api.tech-challenge/enderecos
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
             
        {
          "id":1275424829065256685,
          "rua":"rua bela",
          "numero":"234",
          "bairro":"bairro",
          "cidade":"Maya",
          "estado":"SP"
        }


___

##### **GET**<br><br>

  ###### **GET ALL**<br><br>
    
        GET api.tech-challenge/enderecos
    
        curl -i -X GET --location "api.tech-challenge/enderecos"
        
        HTTP/1.1 200
        Content-Type: application/json
        Transfer-Encoding: chunked
        
        [{"id":1275424829065256685,"rua":"rua bela","numero":"234","bairro":"bairro","cidade":"Maya","estado":"SP"}]

  ###### **GET BY ID**<br><br>

        GET api.tech-challenge/enderecos/1275424829065256685
    
        curl -i -X GET --location "api.tech-challenge/enderecos/1275424829065256685"
        
        HTTP/1.1 200
        Content-Type: application/json
        
        {
            "id":1275424829065256685,
            "rua":"rua bela",
            "numero":"234",
            "bairro":"bairro",
            "cidade":"Maya",
            "estado":"SP"
        }

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

         PUT api.tech-challenge/enderecos/1275424829065256685
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
    
          DELETE api.tech-challenge/enderecos/1275424829065256685
            
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
  -    ***parentesco***, *não-nulo e um dos valores : IRMÃO, IRMÃ, PAI, MAE, FILHO , FILHA, AVÔ, AVÓ, CUNHADA, SOGRA, CUNHADO, SOGRO, AGREGADO, NAMORADA,NOMORADO, CONJUGE, TIA, TIO, AMIGO, AMIGA, NENHUM*

*EXEMPLO:*   

    
       POST api.tech-challenge/pessoas
       Content-Type: application/json
        
        {
          "nome": "ana bela",
          "nascimento": "2020-01-01",
          "sexo": "FEMININO",
          "parentesco": "MAE"
        }
    
        curl -i -X POST --location "api.tech-challenge/pessoas" -H "Content-type:application/json" -d '{"nome":"ana bela", "nascimento":"2020-01-01", "sexo":"FEMININO", "parentesco":"MAE"}'
    
        HTTP/1.1 201 
        Server: nginx/1.18.0
        Content-Type: application/json

        {
          "id":6196618678884909927,
          "nome":"ana bela",
          "nascimento":"2020-01-01",
          "sexo":"FEMININO",
          "parentesco":"MAE"
        }


___

  ##### **GET**<br><br>

   ###### **GET ALL**<br><br>
    
        GET api.tech-challenge/pessoas
        
        curl -i -X GET --location "api.tech-challenge/pessoas"
        HTTP/1.1 200
        Content-Type: application/json
        Transfer-Encoding: chunked
           
        [{"id":6196618678884909927,"nome":"ana bela","nascimento":"2020-01-01","sexo":"FEMININO","parentesco":"MAE"}]

  ###### **GET BY ID**<br><br>

        GET api.tech-challenge/pessoas/6196618678884909927
        
        curl -i -X GET --location "api.tech-challenge/pessoas/5770425075213337291"
        
        HTTP/1.1 200
        Server: nginx/1.18.0
        Content-Type: application/json
    
    
        {
          "id":6196618678884909927,
          "nome":"ana bela",
          "nascimento":"2020-01-01",
          "sexo":"FEMININO",
          "parentesco":"MAE"
        }

___

  ##### **PUT**<br><br>
Na url da **PUT** request deve estar o id do recurso
-    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*

No body da **PUT** request, devem estar os pares key-value: 
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***nascimento***, *não-nulo e NÃO pode ser "hoje" ou qualquer outro dia depois de "hoje"*
  -    ***sexo***, *não-nulo e MASCULINO ou FEMININO*
  -    ***parentesco***, *não-nulo e um dos valores : IRMÃO, IRMÃ, PAI, MAE, FILHO , FILHA, AVÔ, AVÓ, CUNHADA, SOGRA, CUNHADO, SOGRO, AGREGADO, NAMORADA,NOMORADO, CONJUGE, TIA, TIO, AMIGO, AMIGA, NENHUM*

*EXEMPLO:*  
    
        PUT api.tech-challenge/pessoas/6196618678884909927
        Content-Type: application/json
        
        {
          "nome": "Zuleica bela",
          "nascimento": "2010-01-01",
          "sexo": "FEMININO",
          "parentesco": "CONJUGE"
        }
        
        curl -i -X PUT --location "api.tech-challenge/pessoas/6196618678884909927" -H "Content-type:application/json" -d '{"nome":"Zuleica bela", "nascimento":"2010-01-01", "sexo":"FEMININO", "parentesco":"CONJUGE"}'
        
        HTTP/1.1 200 
        Server: nginx/1.18.0
        Content-Type: application/json

        {
          "id":6196618678884909927,
          "nome":"Zuleica bela",
          "nascimento":"2010-01-01",
          "sexo":"FEMININO",
          "parentesco":"CONJUGE"
        }

___

  ##### **DELETE**<br><br>

  No path da **DELETE** request, deve estar o ***id*** do recurso que se deseja deletar: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
        
    DELETE api.tech-challenge/pessoas/6196618678884909927
    
    curl -i -X DELETE --location "api.tech-challenge/pessoas/6196618678884909927"

    HTTP/1.1 204
    Server: nginx/1.18.0



___
#### Endpoint eletrodomesticos : REQUESTS, Curls, RESPONSES
___

##### **POST**<br><br>

No body da **POST** request, devem estar os pares key-value: 
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***modelo***, *não em-branco e no máximo 60 caracteres*
  -    ***potencia***, *não-nula e no range [ 0 , Double.MAX_VALUE ]*     

*EXEMPLO:*  
  
        POST api.tech-challenge/eletrodomesticos
        Content-Type: application/json
    
        {
          "nome": "eletrodomesticos",
          "modelo": "casa-casa",
          "potencia": "0.2"
        }
    
        curl -i -X POST --location "api.tech-challenge/eletrodomesticos" -H "Content-type:application/json" -d '{"nome":"eletrodomesticos", "modelo":"casa-casa", "potencia":"0.2"}'
        
        HTTP/1.1 201 
        Server: nginx/1.18.0
        Content-Type: application/json

        {
            "id":8393364629003825317,
            "nome":"eletrodomesticos",
            "modelo":"casa-casa",
            "potencia":0.2
        }


___

##### **GET**<br><br>

  ###### **GET ALL**<br><br>

        GET api.tech-challenge/eletrodomesticos
    
        curl -i -X GET --location "api.tech-challenge/eletrodomesticos"
        
        HTTP/1.1 200
        Server: nginx/1.18.0
        Content-Type: application/json
         
        [{"id":8393364629003825317,"nome":"eletrodomesticos","modelo":"casa-casa","potencia":0.2}]

   ###### **GET BY ID**<br><br>

        GET api.tech-challenge/eletrodomesticos/8393364629003825317
        
        curl -i -X GET --location "api.tech-challenge/eletrodomesticos/8393364629003825317"
        
        HTTP/1.1 200 
        Server: nginx/1.18.0
        Content-Type: application/json
        
        {
            "id":8393364629003825317,
            "nome":"eletrodomesticos",
            "modelo":"casa-casa",
            "potencia":0.2
        }    
___

##### **PUT**<br><br>

Na url da **PUT** request deve estar o id do recurso
-    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*

No body da **PUT** request, devem estar os pares key-value:
  -    ***nome*** , *não em-branco e no máximo 60 caracteres* 
  -    ***modelo***, *não em-branco e no máximo 60 caracteres*
  -    ***potencia***, *não-nula e no range [ 0 , Double.MAX_VALUE ]*    

*EXEMPLO:* 

        PUT api.tech-challenge/eletrodomesticos/8393364629003825317
        Content-Type: application/json
        
        {
          "nome": "rural-eletro",
          "modelo": "casa-rural",
          "potencia": "0.1"
        }
        
        curl -i -X PUT --location "api.tech-challenge/eletrodomesticos/8393364629003825317" -H "Content-type:application/json" -d '{"nome":"rural-eletro", "modelo":"casa-rural", "potencia":"0.1"}'
        
        HTTP/1.1 200 
        Server: nginx/1.18.0
        Content-Type: application/json
        
        {
          "id":8393364629003825317,
          "nome":"rural-eletro",
          "modelo":"casa-rural",
          "potencia":0.1
        }

___

##### **DELETE**<br><br>

No path da **DELETE** request, deve estar o ***id*** do recurso que se deseja deletar: 
  -    ***id*** , *não-nulo e no range [ Long.MIN_VALUE, Long.MAX_VALUE ]*
    
            DELETE api.tech-challenge/eletrodomesticos/8393364629003825317
            
            curl -i -X DELETE --location "api.tech-challenge/eletrodomesticos/8393364629003825317"
            
            HTTP/1.1 204 

