A# ContaBanco — README (Português / English)

Este README mescla as instruções em Português e em Inglês. Contém todas as informações necessárias para compilar, executar e testar o exercício didático `ContaTerminal`.

--------------------------------------------------

Português

Descrição

O programa solicita, via terminal, as informações de uma conta bancária:

- Número (inteiro) — ex.: `1021`
- Agência (texto, formato normalizado `NNN-D`) — ex.: `067-8` (o programa tenta normalizar entradas como `0678`, `067/8`, ` 067 - 8 `)
- Nome do Cliente (texto) — ex.: `MARIO ANDRADE`
- Saldo (decimal) — ex.: `237.48`, `237,48`, `1.234,56`

Após inserir todos os dados, o programa exibirá a mensagem final:

"Olá [Nome Cliente], obrigado por criar uma conta em nosso banco, sua agência é [Agencia], conta [Numero] e seu saldo [Saldo] já está disponível para saque"

(Os campos entre colchetes serão substituídos pelos valores informados.)

Estrutura do projeto (resumida)

- src/contabanco/ContaTerminal.java — implementação principal (entrada via terminal, validação e formatação)
- src/contabanco/SimpleTestRunner.java — runner de testes simples (não JUnit)
- src/Desafio/ — arquivos arquivados para histórico (vazios/compatibilidade)
- src/archived/Desafio/ — antigo conteúdo arquivado, se precisar restaurar
- run-tests.ps1 / run-tests.bat — scripts para compilar e executar os testes localmente
- test_input.txt — exemplo de entradas (um valor por linha)

Como compilar e executar (Windows / PowerShell)

1) Abra o PowerShell na pasta do projeto (onde está a pasta `src`):

```powershell
cd C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco
```

2) Compilar todas as classes do pacote `contabanco` (gera os .class em `out\`):

```powershell
javac -d out src\contabanco\*.java src\Main.java
```

3) Executar o programa interativamente (ex.: executar o terminal de conta):

```powershell
java -cp out contabanco.ContaTerminal
```

4) Executar os testes (script PowerShell):

```powershell
& 'C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco\run-tests.ps1'
```

Observação sobre redirecionamento de input (`<`) no PowerShell

Se for usar `test_input.txt` para alimentar a entrada automaticamente, o PowerShell pode tratar o operador `<` de forma diferente. Use o `cmd` para redirecionamento clássico:

```powershell
cmd /c "cd /d C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco && java -cp out contabanco.ContaTerminal < test_input.txt"
```

Ou use pipe:

```powershell
cmd /c "type test_input.txt | java -cp out contabanco.ContaTerminal"
```

Exemplo de arquivo `test_input.txt` (cada linha é uma entrada):

```
1021
0678
MARIO   ANDRADE
1.234,56
```

Comportamento esperado: o programa normaliza e exibirá agência `067-8`, conta `1021`, nome `MARIO ANDRADE` e saldo `1.234,56`.

O que o programa valida e normaliza

- Número da conta: somente inteiros positivos; repete o prompt em caso de erro.
- Agência: aceita variações e tenta normalizar para `NNN-D`.
- Nome do cliente: remove espaços redundantes e converte para maiúsculas.
- Saldo: aceita `,` ou `.` como separador decimal, e formatos com separadores de milhar.

Sugestões de melhorias

- Converter o runner de testes para JUnit 5 e integrar ao `pom.xml`.
- Tornar parâmetros de validação (ex.: permitir zero) configuráveis.
- Adicionar limites de tentativas ou timeout por prompt.

--------------------------------------------------

English

Description

This program requests bank account information via the terminal:

- Number (integer) — e.g. `1021`
- Agency (text, normalized format `NNN-D`) — e.g. `067-8` (the program attempts to normalize inputs such as `0678`, `067/8`, ` 067 - 8 `)
- Customer Name (text) — e.g. `MARIO ANDRADE`
- Balance (decimal) — e.g. `237.48`, `237,48`, `1.234,56`

After entering all data the program prints the final message:

"Olá [Nome Cliente], obrigado por criar uma conta em nosso banco, sua agência é [Agencia], conta [Numero] e seu saldo [Saldo] já está disponível para saque"

(the bracketed fields are replaced with user-provided values).

Project layout (brief)

- src/contabanco/ContaTerminal.java — main implementation (terminal input, validation and formatting)
- src/contabanco/SimpleTestRunner.java — small custom test runner (not JUnit)
- src/Desafio/ — archived/placeholder files (kept for history/compatibility)
- src/archived/Desafio/ — archived original content
- run-tests.ps1 / run-tests.bat — scripts to compile and run tests locally
- test_input.txt — example input (one value per line)

How to compile & run (Windows / PowerShell)

1) Open PowerShell in the project folder (where `src` is located):

```powershell
cd C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco
```

2) Compile contabanco sources (this writes .class files into `out\`):

```powershell
javac -d out src\contabanco\*.java src\Main.java
```

3) Run the program interactively (example: run the account terminal):

```powershell
java -cp out contabanco.ContaTerminal
```

4) Run the bundled tests (PowerShell script):

```powershell
& 'C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco\run-tests.ps1'
```

Notes about redirecting input

If you want to feed `test_input.txt` automatically, use `cmd` for classic redirection:

```powershell
cmd /c "cd /d C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco && java -cp out contabanco.ContaTerminal < test_input.txt"
```

Or pipe the file:

```powershell
cmd /c "type test_input.txt | java -cp out contabanco.ContaTerminal"
```

Quick example `test_input.txt`:

```
1021
0678
MARIO   ANDRADE
1.234,56
```

Expected behavior: the program normalizes inputs and prints the final message using agency `067-8`, account `1021`, name `MARIO ANDRADE` and balance `1.234,56`.

Next steps and suggestions

- Migrate the custom test runner to JUnit 5 and wire `mvn test` in `pom.xml`.
- Add unit tests that cover parsing/normalization edge cases.
- Improve user prompts and add configuration for validation rules.

If you want, I can:

- (A) Migrate tests to JUnit 5 and update `pom.xml`.
- (B) Add a combined README in a single language or generate separate release notes.
- (C) Create a tiny CI script or GitHub Actions workflow to run tests automatically.

Tell me which of these you want next and I'll implement it.
