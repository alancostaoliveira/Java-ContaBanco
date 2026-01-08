# Project ContaBanco (ContaTerminal) — English

This README contains instructions in English to compile and run the educational exercise `ContaTerminal`.

Description

The program requests the following bank account information from the terminal:

- Number (integer) — e.g. `1021`
- Agency (text, expected format `NNN-D`) — e.g. `067-8` (the program attempts to normalize inputs such as `0678`, `067/8`, ` 067 - 8 `)
- Customer Name (text) — e.g. `MARIO ANDRADE`
- Balance (decimal) — e.g. `237.48`, `237,48`, `1.234,56`

After providing the data, the program prints:

"Olá [Nome Cliente], obrigado por criar uma conta em nosso banco, sua agência é [Agencia], conta [Numero] e seu saldo [Saldo] já está disponível para saque"

(Bracketed fields are replaced by the user input.)

How to compile and run (Windows / PowerShell)

1) Open PowerShell in the project folder (where the `src` folder is):

```powershell
cd C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco
```

2) Compile the class (this will output .class files into `out\`):

```powershell
javac -d out src\Desafio\ContaTerminal.java
```

3) Run the program (interactive mode):

```powershell
java -cp out Desafio.ContaTerminal
```

Non-interactive test input

If you want to use the provided `test_input.txt` file to feed input automatically, note that PowerShell sometimes treats `<` differently; use `cmd` if you face problems:

```powershell
# Using cmd (recommended if '<' causes issues in PowerShell)
cmd /c "cd /d C:\Users\crist\IdeaProjects\Java-ContaBanco\Java-ContaBanco && java -cp out Desafio.ContaTerminal < test_input.txt"
```

Or use a pipe:

```powershell
cmd /c "type test_input.txt | java -cp out Desafio.ContaTerminal"
```

Sample input file

`test_input.txt` example (one value per line):

```
1021
0678
MARIO   ANDRADE
1.234,56
```

Expected behavior: the program will normalize inputs and print a final message with: agency `067-8`, account `1021`, name `MARIO ANDRADE`, and balance `1.234,56`.

What the program validates and normalizes

- Account number: only positive integers.
- Agency: accepts variants and tries to normalize to `NNN-D`.
- Name: removes extra spaces and converts to uppercase.
- Balance: accepts `,` or `.` as decimal separator and thousand separators.

Next suggested improvements

- Add unit tests (JUnit) for normalization/parsing helpers.
- Make validation-policy parameters configurable.
- Add a limit of attempts per prompt.

If you'd like, I can:
1) Add unit tests (JUnit).
2) Adjust validation rules (e.g. allow zero/negative account numbers).
3) Add more detailed output examples or local run scripts.

Choose an option and I will continue.
