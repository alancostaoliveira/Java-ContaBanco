package contabanco;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

        int numero = readNumero(sc);
        String agencia = readAgencia(sc);
        String nomeCliente = readNomeCliente(sc);
        BigDecimal saldo = readSaldo(sc);

        sc.close();

        String saldoFormatado = formatarSaldoPtBr(saldo);

        String mensagem = String.format("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %s já está disponível para saque",
                nomeCliente, agencia, numero, saldoFormatado);

        System.out.println(mensagem);
    }

    private static int readNumero(Scanner sc) {
        while (true) {
            System.out.print("Por favor, digite o número da Conta (somente números): ");
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) {
                System.out.println("Entrada vazia. Tente novamente.");
                continue;
            }
            try {
                int numero = Integer.parseInt(linha);
                if (numero <= 0) {
                    System.out.println("O número da conta deve ser um inteiro positivo. Tente novamente.");
                    continue;
                }
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Digite apenas dígitos inteiros (ex.: 1021). Tente novamente.");
            }
        }
    }

    private static String readAgencia(Scanner sc) {
        while (true) {
            System.out.print("Por favor, digite o número da Agência (ex.: 067-8 ou 0678): ");
            String raw = sc.nextLine().trim();
            if (raw.isEmpty()) {
                System.out.println("Entrada vazia. Tente novamente.");
                continue;
            }
            String norm = normalizeAgencia(raw);
            if (norm == null) {
                System.out.println("Agência inválida. Formato esperado: 3 dígitos + '-' + 1 dígito verificador (ex.: 067-8). Tente novamente.");
                continue;
            }
            return norm;
        }
    }

    static String normalizeAgencia(String raw) {
        String s = raw.replaceAll("\\s+", "").replace('/', '-').replace("\\", "");
        if (s.contains("-")) {
            String[] parts = s.split("-");
            if (parts.length != 2) return null;
            String left = parts[0];
            String right = parts[1];
            if (left.matches("\\d{3}") && right.matches("\\d")) {
                return left + "-" + right;
            } else {
                return null;
            }
        }
        String digits = s.replaceAll("\\D", "");
        if (digits.length() >= 4) {
            String left = digits.substring(0, digits.length() - 1);
            String right = digits.substring(digits.length() - 1);
            if (left.length() == 3 && right.matches("\\d")) {
                return left + "-" + right;
            }
        }
        return null;
    }

    private static String readNomeCliente(Scanner sc) {
        while (true) {
            System.out.print("Por favor, digite o Nome do Cliente: ");
            String raw = sc.nextLine();
            String norm = normalizeNome(raw);
            if (norm == null || norm.isEmpty()) {
                System.out.println("Nome inválido. Informe um nome não vazio. Tente novamente.");
                continue;
            }
            return norm;
        }
    }

    static String normalizeNome(String raw) {
        if (raw == null) return null;
        String s = raw.trim().replaceAll("\\s+", " ");
        if (s.isEmpty()) return "";
        return s.toUpperCase(Locale.forLanguageTag("pt-BR"));
    }

    private static BigDecimal readSaldo(Scanner sc) {
        while (true) {
            System.out.print("Por favor, digite o Saldo (ex.: 237.48 ou 237,48 ou 1.234,56): ");
            String raw = sc.nextLine().trim();
            if (raw.isEmpty()) {
                System.out.println("Entrada vazia. Tente novamente.");
                continue;
            }
            try {
                return parseSaldo(raw);
            } catch (Exception e) {
                System.out.println("Saldo inválido. Informe um número válido. Tente novamente.");
            }
        }
    }

    static BigDecimal parseSaldo(String raw) {
        String s = raw.trim();
        s = s.replaceAll("\\s+", "");
        boolean hasDot = s.indexOf('.') >= 0;
        boolean hasComma = s.indexOf(',') >= 0;
        if (hasDot && hasComma) {
            // Determine which symbol is the decimal separator by which occurs last
            int lastDot = s.lastIndexOf('.');
            int lastComma = s.lastIndexOf(',');
            if (lastComma > lastDot) {
                // comma is decimal separator (ex: 1.234,56)
                s = s.replace(".", "");
                s = s.replace(",", ".");
            } else {
                // dot is decimal separator (ex: 1,234.56)
                s = s.replace(",", "");
                // keep the dot as decimal separator
            }
        } else if (hasComma) {
            // comma as decimal separator
            s = s.replace(",", ".");
        }
        return new BigDecimal(s);
    }

    static String formatarSaldoPtBr(BigDecimal saldo) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(saldo);
    }
}

