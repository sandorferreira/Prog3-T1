# Prog3-T1
Trabalho Prático 1 de Programação 3

André: 

(18/06/2017)
- Obs1: Está criando as listas de docentes e publicações, porém a lista de publicações não está atribuindo valor. Imagino que herança dessa forma não funciona, pois está repetindo as váriaveis.
- Obs2: Alguns arquivos de entrada 'publicacoes.csv' estão com alguns espaçamentos no autor '9264617752964635', dando erro na execução.

(21/06/2017)
- Estou pensando em criar um objeto da classe 'Qualis' em 'Publicação' para vincular o qualis sem precisar ficar procurando toda hora, e lista de 'Publição' em 'Docente' para ter todas as listas de publicações de sua autoria.

(27/06/2017)
- Lista de publicações de cada docente está vazia, imagino que seja um erro que passou quando voltou de hash para linkedlist. Imagino que só falta mudar isso para o relatório de recredenciamento sair igual o teste.

Sandor:

(23/06/2017)
- Criei classes para manipular os arquivos com as funções que você já criou.
- Já comecei a tratar as exceções de leitura para depois gerar os relatórios mais tranquilamente

(24/06/2017)
- Agora, há as Classes ArquivoDocente, ArquivoQualificacao, etc. que manipulam os arquivos de entrada de acordo com os argumentos especificados na execução (os nomes dos arquivos de entrada podem mudar).
- O Programa.java se chama agora Main.java para executar no teste automático no arquivo build.xml
- Todas as Exceções estão codificadas como subclasses de Exception, sendo criadas com uma mensagem automática para gerar no teste automático apenas com (e.getMessage()).
- As inconsistências dos dados também já estão implementadas.

(26/06/2017)
- Gerando os relatórios.
