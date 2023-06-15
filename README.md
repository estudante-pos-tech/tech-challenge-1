# tech-challenge-1 
Implementação ***CRUD** Java Rest Apis*.<br>
Os endpoints são endereco, pessoa e eletrodomestico
## Relatório Técnico

IDE Intellij, maven, String Boot, Spring MVC, Spring DevTools, Lombok são as ferramentas e tecnologias usadas para desenvolver as apis rest.
<br><br>**Não está implementada persistência de dados**. Os dados que o app cria e manipula residem na memória volátil e **não são persistentes**. Estes dados ficam armazenados numa estrututura de dados java do tipo Set (conjunto). 
<br><br>Um **repositório** é usado para acessar uma coleção (Set) de objetos, simulando **CRUD**. 
<br><br>**Soluções genéricas** são excelentes, quando evoluimos uma app. Neste projeto **tech-challenge-1**, o **repositorio** implementado usa **java generics**. O código para fazer o *CRUD* é parametrizado pelos tipos Endereco, Pessoa e Eletromestico. *Instâncias de Repositorio<T>*, **com escopo prototype**, são criadas e gerenciadas pelo Spring e são injetadas em cada um dos controllers respectivos.
<br><br>**Requests corretas** aos endpoints tem **responses** descritas na **Documentação das APIs**<br>
**Requests incorretas** aos endpoints tem **response** : ***erro + causa do erro*** 
              

##
## Documentação das APIs
**ELETRODMÉSTICOS REQUESTS E CURLs E RESPONSES**



     POST http://localhost:8080/eletrodomestico<br>
     Content-Type: application/json

    {
      "nome": "eletrodomestico",
      "modelo": "casa-casa",
      "potencia": "0.2 KWh"
    }

    curl -X POST --location "http://localhost:8080/eletrodomestico" -H "Content-type:application/json" -d '{"nome":"eletrodomestico", "modelo":"casa-casa", "potencia":"0.2 KWh"}'
    SUCESSO: ao criar Eletrodomestico


GET http://localhost:8080/eletrodomestico

curl -X GET --location "http://localhost:8080/eletrodomestico"
[{"id":8393364629003825317,"nome":"eletrodomestico","modelo":"casa-casa","potencia":"0.2 KWh"}]


###
PUT http://localhost:8080/eletrodomestico<br>
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


###
DELETE http://localhost:8080/eletrodomestico/8393364629003825317
curl -X DELETE --location "http://localhost:8080/eletrodomestico/8393364629003825317"
SUCESSO: ao excluir Eletrodomestico 8393364629003825317

#ENDEREÇOS



### Desafios técnico
Se fosse possívelr ao Spring *mapear* **um subconjunto de requests** a *apenas 1 determinado método* e se fosse possível *mapear* determindado DTO *a uma determinada request*, então, **ao invés de se ter 3 controllers** (EnderecoController, PessoaController, EletrodomesticoController), **seria possivel ter apenas 1 controller genérico**. Essa solução simplificaria, enxugaria, tornaria ++ fácil de evoluir o app.<br>
Precisa ser estudado se o **Spring AOP (Aspect Oriented Programming)** suporta os mapeamentos.


