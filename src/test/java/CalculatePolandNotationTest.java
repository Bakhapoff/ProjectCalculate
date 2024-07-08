import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class CalculatePolandNotationTest {
    @Test
    public void newCpnSimpleExpression() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("1,32,+,");
        Assert.assertEquals(33, cpn.calculatePolandBigInteger().intValue());
    }

    @Test
    public void newCpnSimpleExpressionWithNegativeNumber() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("-1,3,+,");
        Assert.assertEquals(2, cpn.calculatePolandBigInteger().intValue());
    }

    @Test
    public void newCpnSimpleExpressionWithParenthesesAndNegativeNumber() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("1,-32,+,");
        Assert.assertEquals(-31, cpn.calculatePolandBigInteger().intValue());
    }

    @Test
    public void newCpnWithParentheses() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("20,15,-,5,+,");
        Assert.assertEquals(10, cpn.calculatePolandBigInteger().intValue());

    }

    @Test
    public void newCpnRelativeCompoundExpression() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("29,36,20,15,-,*,+,");
        Assert.assertEquals(209, cpn.calculatePolandBigInteger().intValue());

    }

    @Test
    public void newCpnRelativeCompoundExpressionWithFactorialNumbers() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("2.32,2.4,+,2,10,*,+,");
        Assert.assertEquals(new BigDecimal("24.72"), cpn.calculatePolandBigDecimal());
    }

    @Test
    public void newCpnRelativeCompoundExpressionWithFactorialAndNegativeNumbers() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("2.32,-2.4,+,-2,10,*,+,");
        Assert.assertEquals(new BigDecimal("-20.08"), cpn.calculatePolandBigDecimal());
    }

    @Test
    public void newCpnComplexExpressionWithFactorialNumbers() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("-2.5,2.3,*-2.35,2.32,+,+,1,-,");
        Assert.assertEquals(new BigDecimal("6.78"), cpn.calculatePolandBigDecimal());
    }

    @Test
    public void newCpnComplexExpression() {
        CalculatePolandNotation cpn = new CalculatePolandNotation("29,36,20,15,-,*,+,3,3,3,+,+,+,,");
        Assert.assertEquals(new BigDecimal("218"), cpn.calculatePolandBigDecimal());
    }

}
