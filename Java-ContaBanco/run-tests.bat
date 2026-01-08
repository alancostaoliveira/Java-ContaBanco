@echo off
REM Script para compilar e executar os testes simples (Windows cmd)
cd /d %~dp0
echo Compilando fontes...
javac -d out src\Desafio\*.java src\contabanco\*.java src\Main.java
if errorlevel 1 (
  echo Erro na compilacao. Abortando.
  exit /b 1
)

echo Executando testes (SimpleTestRunner)...
java -cp out Desafio.SimpleTestRunner > run_output.txt 2>&1

echo ===== Saida dos testes =====
type run_output.txt

echo ===== Fim =====
exit /b 0

