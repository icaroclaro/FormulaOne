
# FormulaONE
Projeto para Calcular e Ordenar informações obtidas em uma corrida de Formula 1


Este projeto tem por objetivo, extrair informações de um arquivo de log e gerar estatística referente a uma corrida
de formula 1 contendo 4 voltas.

## ----Inferência no desenvolvimento---
Durante o desenvolvimento da aplicação surgiu uma dúvida referênte ao calculo para se obter o vencedor.
Foram identificadas duas possibilidades:
- Utilizar o campo HORA, como sendo a hora do registro da marcação. Neste caso utilizariamos o campo HORA com a marcação da ultima volta;
- Utilizar o menor tempo da soma de todas as voltas dos pilotos e buscar a menor delas.

Não foi utilizado o campo HORA pois inferindo que ele estava desordenado poderia ter ocorrido algum problema durante o registro destes horários.
Foi utilizado o menor tempo da soma de todos os tempos de voltas do corredor

## Começar

Ao baixar o projeto, no OS Windows, executar o arquivo scriptToBuildAndRun.cmd localizado na pasta raiz do projeto.
Este script tem por objetivo: Compilar o projeto, executar os casos de teste e por fim, executar a aplicação.
Caso tenha algum problema com o script, abra o Command Line do windows e execute os comandos abaixo dentro da pasta do projeto:

```
mvnw package

pause

java -cp target/formulaone-0.0.1-SNAPSHOT.jar

pause
```

### Pré-requisitos

O Projeto foi construido utilizando JDK12, portanto para realizar alguma alteraçao no projeto 
é recomendado a utilização desta versão.
Caso utilize versões anteriore, será necessário alterar o JDK da versão utilizada nas configurações do projeto.

## Rodando os testes

Os testes serão executados após executar o scriptToBuildAndRun.cmd

### Testes

Os testes foram escritos para garantir que a plicação continue se comportando de maneira esperada.
Todos os metodos publicos foram testados.

```
deveCalcularResultadoCorrida() - Executa a aplicação completa para verificar alguma inconsistência.
deveRetornarListaDeVoltasDoPiloto() - Verifica se o arquivo foi lido corretamente e se a lista do model<VoltaPiloto> foi preenchido
deveRetornarFileNotFoundException() - Caso o arquivo não tenha sido encontrado, testa o lançamento de exception
deveRetornarNoSuchElementException() - Caso o arquivo esteja vazio, testa o lançamento de exception
deveRetornarTempoEmMilissegundos() - Testa o metodo que faz conversão de Tempo(mm:ss.SSS) em millissegundos
deveRetornarTempoEmMinutos()- Testa o metodo que faz conversão de  milissegundos em Tempo(mm:ss.SSS)
```

## Authors

* **Ícaro Claro** - *Initial work* - [PurpleBooth](https://github.com/icaroclaro)