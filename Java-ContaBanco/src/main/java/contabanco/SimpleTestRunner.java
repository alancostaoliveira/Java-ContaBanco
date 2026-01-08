package contabanco;

import java.math.BigDecimal;

public class SimpleTestRunner {
    public static void main(String[] args) {
        TestResults results = runAll();
        System.out.println("Pass: " + results.passed + " Fail: " + results.failed);
        System.exit(results.failed > 0 ? 1 : 0);
    }

    static class TestResults { int passed = 0; int failed = 0; }

    static TestResults runAll() {
        TestResults r = new TestResults();
        run(() -> assertEquals("067-8", ContaTerminal.normalizeAgencia("067-8")), r);
        run(() -> assertEquals("067-8", ContaTerminal.normalizeAgencia("0678")), r);
        run(() -> assertEquals("067-8", ContaTerminal.normalizeAgencia(" 0678 ")), r);
        run(() -> assertEquals("012-3", ContaTerminal.normalizeAgencia("0123")), r);
        run(() -> assertEquals("067-8", ContaTerminal.normalizeAgencia("067/8")), r);
        run(() -> assertNull(ContaTerminal.normalizeAgencia("06-78")), r);
        run(() -> assertNull(ContaTerminal.normalizeAgencia("abcd")), r);

        run(() -> assertEquals("MARIO ANDRADE", ContaTerminal.normalizeNome("MARIO   ANDRADE")), r);
        run(() -> assertEquals("JOÃO SILVA", ContaTerminal.normalizeNome(" João   Silva ")), r);

        run(() -> assertBigDecimalEquals(new BigDecimal("237.48"), ContaTerminal.parseSaldo("237.48")), r);
        run(() -> assertBigDecimalEquals(new BigDecimal("237.48"), ContaTerminal.parseSaldo("237,48")), r);
        run(() -> assertBigDecimalEquals(new BigDecimal("1234.56"), ContaTerminal.parseSaldo("1.234,56")), r);
        run(() -> assertBigDecimalEquals(new BigDecimal("1234.56"), ContaTerminal.parseSaldo("1,234.56")), r);

        run(() -> assertEquals("1.234,56", ContaTerminal.formatarSaldoPtBr(new BigDecimal("1234.56"))), r);
        run(() -> assertEquals("237,48", ContaTerminal.formatarSaldoPtBr(new BigDecimal("237.48"))), r);

        return r;
    }

    static void run(Runnable test, TestResults r) {
        try {
            test.run();
            System.out.println("PASS");
            r.passed++;
        } catch (AssertionError e) {
            System.out.println("FAIL: " + e.getMessage());
            r.failed++;
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            r.failed++;
        }
    }

    static void assertEquals(Object expected, Object actual) {
        if (expected == null) {
            if (actual != null) throw new AssertionError("Expected null but was: " + actual);
        } else if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + " but was: " + actual);
        }
    }

    static void assertNull(Object actual) {
        if (actual != null) throw new AssertionError("Expected null but was: " + actual);
    }

    static void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual) {
        if (expected == null) {
            if (actual != null) throw new AssertionError("Expected null but was: " + actual);
            return;
        }
        if (actual == null) throw new AssertionError("Expected: " + expected + " but was null");
        if (expected.compareTo(actual) != 0) throw new AssertionError("Expected: " + expected + " but was: " + actual);
    }
}

