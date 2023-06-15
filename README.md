# tech-challenge-1 
Implementação ***CRUD** Java Rest Apis*.<br>
Os endpoints são endereco, pessoa e eletrodoméstico
### Relatório Técnico

IDE Intellij, maven, String Boot, Spring MVC, Spring DevTools, Lombok são as ferramentas e tecnologias usadas para desenvolver as apis rest.
<br><br>**Não está implementada persistência de dados**. Os dados que o app cria e manipula residem na memória volátil e **não são persistentes**. Estes dados ficam armazenados numa estrututura de dados java do tipo Set (conjunto). 
<br><br>Um **repositório** é usado para acessar uma coleção (Set) de objetos, simulando **CRUD**. 
#### Desafios técnico

### Documentação das APIs
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



