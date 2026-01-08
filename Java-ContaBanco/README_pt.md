# Projeto ContaBanco (ContaTerminal) — Português

Este README apresenta as instruções em português para compilar e executar o exercício didático `ContaTerminal`.

Descrição

O programa solicita, pelo terminal, as seguintes informações sobre uma conta bancária:

- Número (inteiro) — ex.: `1021`
- Agência (texto, formato esperado `NNN-D`) — ex.: `067-8` (o programa tenta normalizar entradas como `0678`, `067/8`, ` 067 - 8 `)
- Nome do Cliente (texto) — ex.: `MARIO ANDRADE`
- Saldo (decimal) — ex.: `237.48`, `237,48`, `1.234,56`

Depois de inserir os dados, o programa exibe:

"Olá [Nome Cliente], obrigado por criar uma conta em nosso banco, sua agência é [Agencia], conta [Numero] e seu saldo [Saldo] já está disponível para saque"

Como compilar e executar (Windows / PowerShell)

1) Abra o PowerShell na pasta do projeto (onde está a pasta `src`):

```powershell
cd C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco
```

2) Compilar a classe (gera os .class em `out\`):

```powershell
javac -d out src\Desafio\ContaTerminal.java
```

3) Executar o programa (interativamente):

```powershell
java -cp out Desafio.ContaTerminal
```

Executar com arquivo de teste (não-interativo)

Se quiser usar o arquivo `test_input.txt` que acompanha este repositório para alimentar automaticamente a entrada, use o `cmd` no Windows caso o PowerShell dê problema com `<`:

```powershell
# Via cmd (recomendado se houver problema com '<' no PowerShell)
cmd /c "cd /d C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco && java -cp out Desafio.ContaTerminal < test_input.txt"
```

Ou usar pipe:

```powershell
cmd /c "type test_input.txt | java -cp out Desafio.ContaTerminal"
```

Arquivo de exemplo

`test_input.txt` contém um conjunto de entradas exemplo (cada linha é uma entrada):

```
1021
0678
MARIO   ANDRADE
1.234,56
```

O programa deverá normalizar e exibir: agência `067-8`, conta `1021`, nome `MARIO ANDRADE` e saldo `1.234,56`.

O que é validado e normalizado

- Número da conta: somente inteiros positivos; repete o prompt em caso de erro.
- Agência: aceita variações e tenta normalizar para `NNN-D`.
- Nome do cliente: remove espaços redundantes e converte para maiúsculas.
- Saldo: aceita `,` ou `.` como separador decimal, e formatos com separadores de milhar.

Próximos passos sugeridos

- Adicionar testes unitários (JUnit) para as funções de normalização e parsing.
- Tornar parâmetros de validação configuráveis.
- Implementar limite de tentativas por prompt.

Se precisar, posso:
1) Adicionar README em inglês (já está pronto nesta pasta como `README_en.md`).
2) Implementar testes JUnit.
3) Ajustar regras de validação.

Escolha uma opção e eu continuo.
