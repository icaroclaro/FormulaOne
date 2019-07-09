
# FormulaONE
Projeto para Calcular e Ordenar informações obtidas em uma corrida de Formula 1


Este projeto tem por objetivo, extrair informações de um arquivo de log e gerar estatística referente a uma corrida
de formula 1 contendo 4 voltas.

#### -------------------------------NOTA------------------------------------
Durante o desenvolvimento da aplicação surgiu uma dúvida referênte ao calculo para se obter o vencedor.
Foram identificadas duas possibilidades:
- Utilizar o campo HORA, como sendo a hora do registro da marcação. Neste caso utilizariamos o campo HORA com a marcação da ultima volta;
- Utilizar o menor tempo da soma de todas as voltas dos pilotos e buscar a menor delas.

Não foi utilizado o campo HORA pois inferindo que ele estava desordenado poderia ter ocorrido algum problema durante o registro destes horários.
Foi utilizado o menor tempo da soma de todos os tempos de voltas do corredor por acreditar que a soma destes valores resultariam consequentemente
no corredor que iria passar primeiro pela linha de chegada.
É possível que isso não seja uma verdade e o correto fosse ter utilizado do campo HORA, visto que a menor HORA marcada na quarta volta fosse 
determinante para decidir o vencedor
#### -------------------------------Fim NOTA---------------------------------

### Pré-requisitos

Para executar o projeto será necessário possuir o Maven e JDK posterior ao 8(o projeto foi construido com JDK12 no IntelliJ).


## Começar

### Windows
Ao baixar o projeto, no OS Windows, executar o arquivo scriptToBuildAndRun.cmd localizado na pasta raiz do projeto.
Este script tem por objetivo: Compilar o projeto, executar os casos de teste e por fim, executar a aplicação.
Caso tenha algum problema com o script, abra o Command Line do windows e execute os comandos abaixo.

Na pasta do projeto:
```
mvnw package


java -jar target/formulaone-0.0.1-SNAPSHOT.jar

```

### Linux

Na pasta do projeto:

```
mvn package

java -jar target/formulaone-0.0.1-SNAPSHOT.jar

```

## Testes de execução do projeto
O Projeto foi testado com OS Windows 10(JDK12 e Maven) e Linux Debian(JDK8 e Maven 3.x)

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

## Possiveis ajustes de configuracao
Quando o projeto foi baixado no Debian, os arquivos FormulaOneApplicationTests.java e FormulaOneApplication.java estavam com os seguintes nomes:
```
FormulaoneApplicationTests.java
FormulaoneApplication.java
```
(com o one minusculo). Foi necessário alterar o do arquivo para One(com o O maiusculo).

## Autor

* **Ícaro Claro** - [Projetos de Estudo](https://github.com/icaroclaro)