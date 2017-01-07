Consiste em um programa que ao ser executado recebe como entrada o caminho absoluto de um arquivo CSV, por exemplo: C:/Users/Mayza Nunes/arquivo.csv. O programa pode tratar qualquer csv que tenha como primeira linha as propriedades e as linhas restantes os valores respectivos às propriedades tendo como separador a vírgula. Após isso, o programa carrega os dados e os comandos abeixo podem ser interpretados:

count * - escreve no console a contagem total de registros importados (não deve considerar a linha de cabeçalho)

count distinct [propriedade] - escreve no console o total de valores distintos da propriedade (coluna) enviada

filter [propriedade] [valor] - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado

Para executar:
Executar diretamente no terminal com o comando : java -jar consultaCSV.jar
Ou
Importe o repositório no eclipse e execute a classe: LeiaCSV.java no package com.involves.mayza.app

Para executar os testes: 
Importe o projeto no eclipse e execute as classes de teste  BuscadorTest e UtilsTest no package com.involves.mayza.teste com JUnit4. Para os arquivos de teste usei como base o arquivo arq.csv com um número menor de linhas que está no repositório principal.



