package contabanco;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTerminalTest {

    @Test
    void testNormalizeAgencia() {
        assertEquals("067-8", ContaTerminal.normalizeAgencia("067-8"));
        assertEquals("067-8", ContaTerminal.normalizeAgencia("0678"));
        assertEquals("067-8", ContaTerminal.normalizeAgencia(" 0678 "));
        assertEquals("012-3", ContaTerminal.normalizeAgencia("0123"));
        assertEquals("067-8", ContaTerminal.normalizeAgencia("067/8"));
        assertNull(ContaTerminal.normalizeAgencia("06-78"));
        assertNull(ContaTerminal.normalizeAgencia("abcd"));
    }

    @Test
    void testNormalizeNome() {
        assertEquals("MARIO ANDRADE", ContaTerminal.normalizeNome("MARIO   ANDRADE"));
        assertEquals("JOÃO SILVA", ContaTerminal.normalizeNome(" João   Silva "));
    }

    @Test
    void testParseSaldo() {
        assertEquals(new BigDecimal("237.48"), ContaTerminal.parseSaldo("237.48"));
        assertEquals(new BigDecimal("237.48"), ContaTerminal.parseSaldo("237,48"));
        assertEquals(new BigDecimal("1234.56"), ContaTerminal.parseSaldo("1.234,56"));
        assertEquals(new BigDecimal("1234.56"), ContaTerminal.parseSaldo("1,234.56"));
    }

    @Test
    void testFormatarSaldoPtBr() {
        assertEquals("1.234,56", ContaTerminal.formatarSaldoPtBr(new BigDecimal("1234.56")));
        assertEquals("237,48", ContaTerminal.formatarSaldoPtBr(new BigDecimal("237.48")));
    }
}

