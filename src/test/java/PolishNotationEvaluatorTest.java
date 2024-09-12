import org.junit.Assert;
import org.junit.Test;


public class PolishNotationEvaluatorTest {
    @Test
    public void newTpnSimpleExpression() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("1+32");
        Assert.assertEquals("1,32,+,", tpn.convertToRPN());
    }

    @Test
    public void newTpnSimpleExpressionWithNegativeNumber() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("-1+3");
        Assert.assertEquals("-1,3,+,", tpn.convertToRPN());
    }

    @Test
    public void newTpnSimpleExpressionWithParenthesesAndNegativeNumber() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("1+(-32)");
        Assert.assertEquals("1,-32,+,", tpn.convertToRPN());
    }


    @Test
    public void newTpnWithParentheses() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("(20-15)+5");
        Assert.assertEquals("20,15,-,5,+,", tpn.convertToRPN());
    }


    @Test
    public void newTpnRelativeCompoundExpression() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("29+36*(20-15)");
        Assert.assertEquals("29,36,20,15,-,*,+,", tpn.convertToRPN());
    }

    @Test
    public void newTpnRelativeCompoundExpressionWithFactorialNumbers() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("2.32+2.4+(2*10)");
        Assert.assertEquals("2.32,2.4,+,2,10,*,+,", tpn.convertToRPN());
    }

    @Test
    public void newTpnRelativeCompoundExpressionWithFactorialAndNegativeNumbers() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("2.32+(-2.4)+(-2*10)");
        Assert.assertEquals("2.32,-2.4,+,-2,10,*,+,", tpn.convertToRPN());
    }


    @Test
    public void newTpnComplexExpressionWithFactorialNumbers() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("-2.5*2.3+(-2.35+2.32)-1");
        Assert.assertEquals("-2.5,2.3,*-2.35,2.32,+,+,1,-,", tpn.convertToRPN());
    }

    @Test
    public void newTpnComplexExpression() {
        PolishNotationEvaluator tpn = new PolishNotationEvaluator("29+36*(20-15)+(3+(3+3))");
        Assert.assertEquals("29,36,20,15,-,*,+,3,3,3,+,+,+,,", tpn.convertToRPN());
    }
}
