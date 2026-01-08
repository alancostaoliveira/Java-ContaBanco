# PowerShell script para compilar e executar SimpleTestRunner
Set-Location -Path $PSScriptRoot
Write-Output "Compilando fontes..."
javac -d out src\contabanco\*.java src\Main.java
if ($LASTEXITCODE -ne 0) { Write-Error "Erro na compilacao"; exit $LASTEXITCODE }

Write-Output "Executando testes..."
java -cp out contabanco.SimpleTestRunner *> run_output.txt
Write-Output "===== Saida dos testes ====="
Get-Content run_output.txt | Write-Output
Write-Output "===== Fim ====="
