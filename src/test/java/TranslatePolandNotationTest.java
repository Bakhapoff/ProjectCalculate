import org.junit.Assert;
import org.junit.Test;


public class TranslatePolandNotationTest {
    @Test
    public void newTpnSimpleExpression() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("1+32");
        Assert.assertEquals("1,32,+,", tpn.getPolandNotationString());
    }

    @Test
    public void newTpnSimpleExpressionWithNegativeNumber() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("-1+3");
        Assert.assertEquals("-1,3,+,", tpn.getPolandNotationString());
    }

    @Test
    public void newTpnSimpleExpressionWithParenthesesAndNegativeNumber() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("1+(-32)");
        Assert.assertEquals("1,-32,+,", tpn.getPolandNotationString());
    }


    @Test
    public void newTpnWithParentheses() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("(20-15)+5");
        Assert.assertEquals("20,15,-,5,+,", tpn.getPolandNotationString());
    }


    @Test
    public void newTpnRelativeCompoundExpression() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("29+36*(20-15)");
        Assert.assertEquals("29,36,20,15,-,*,+,", tpn.getPolandNotationString());
    }

    @Test
    public void newTpnRelativeCompoundExpressionWithFactorialNumbers() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("2.32+2.4+(2*10)");
        Assert.assertEquals("2.32,2.4,+,2,10,*,+,", tpn.getPolandNotationString());
    }

    @Test
    public void newTpnRelativeCompoundExpressionWithFactorialAndNegativeNumbers() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("2.32+(-2.4)+(-2*10)");
        Assert.assertEquals("2.32,-2.4,+,-2,10,*,+,", tpn.getPolandNotationString());
    }


    @Test
    public void newTpnComplexExpressionWithFactorialNumbers() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("-2.5*2.3+(-2.35+2.32)-1");
        Assert.assertEquals("-2.5,2.3,*-2.35,2.32,+,+,1,-,", tpn.getPolandNotationString());
    }

    @Test
    public void newTpnComplexExpression() {
        TranslatePolandNotation tpn = new TranslatePolandNotation("29+36*(20-15)+(3+(3+3))");
        Assert.assertEquals("29,36,20,15,-,*,+,3,3,3,+,+,+,,", tpn.getPolandNotationString());
    }
}
