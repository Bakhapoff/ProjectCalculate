import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class ReversePolishNotationCalculatorTest {
    @Test
    public void newCpnSimpleExpression() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("1,32,+,");
        Assert.assertEquals(33, cpn.calculateRPNBigInteger().intValue());
    }

    @Test
    public void newCpnSimpleExpressionWithNegativeNumber() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("-1,3,+,");
        Assert.assertEquals(2, cpn.calculateRPNBigInteger().intValue());
    }

    @Test
    public void newCpnSimpleExpressionWithParenthesesAndNegativeNumber() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("1,-32,+,");
        Assert.assertEquals(-31, cpn.calculateRPNBigInteger().intValue());
    }

    @Test
    public void newCpnWithParentheses() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("20,15,-,5,+,");
        Assert.assertEquals(10, cpn.calculateRPNBigInteger().intValue());

    }

    @Test
    public void newCpnRelativeCompoundExpression() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("29,36,20,15,-,*,+,");
        Assert.assertEquals(209, cpn.calculateRPNBigInteger().intValue());

    }

    @Test
    public void newCpnRelativeCompoundExpressionWithFactorialNumbers() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("2.32,2.4,+,2,10,*,+,");
        Assert.assertEquals(new BigDecimal("24.72"), cpn.calculateRPNBigDecimal());
    }

    @Test
    public void newCpnRelativeCompoundExpressionWithFactorialAndNegativeNumbers() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("2.32,-2.4,+,-2,10,*,+,");
        Assert.assertEquals(new BigDecimal("-20.08"), cpn.calculateRPNBigDecimal());
    }

    @Test
    public void newCpnComplexExpressionWithFactorialNumbers() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("-2.5,2.3,*-2.35,2.32,+,+,1,-,");
        Assert.assertEquals(new BigDecimal("6.78"), cpn.calculateRPNBigDecimal());
    }

    @Test
    public void newCpnComplexExpression() {
        ReversePolishNotationCalculator cpn = new ReversePolishNotationCalculator("29,36,20,15,-,*,+,3,3,3,+,+,+,,");
        Assert.assertEquals(new BigDecimal("218"), cpn.calculateRPNBigDecimal());
    }

}
