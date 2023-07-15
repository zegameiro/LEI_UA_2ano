import java.lang.Math;

public class Fraction {

    //Atributs
    private int numerator;
    private int denominator;

    // Constructor definition
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Get the value of the numerator
    public int getNumerator() {
        return numerator;
    }

    // Get the value of the denominator
    public int getDenominator() {
        return denominator;
    }

    // Get exat value of the fraction
    public double fractionValue() {
        return numerator/denominator;
    }

    // Sum fractions
    public Fraction sumFraction(Fraction fra) {
        if(fra.getDenominator() == denominator)
            return new Fraction(numerator + fra.getNumerator(), denominator);
        else
            return new Fraction((numerator * fra.getDenominator() + fra.getNumerator() * denominator), (denominator * fra.getDenominator()));
    }

    // Subtract fractions
    public Fraction subFraction(Fraction fra) {
        if(fra.getDenominator() == denominator)
            return new Fraction(numerator - fra.getNumerator(), denominator);
        else
            return new Fraction((numerator * fra.getDenominator() - fra.getNumerator() * denominator), (denominator * fra.getDenominator()));
    }

    // Multiply fractions
    public Fraction multFraction(Fraction fra) {
        return new Fraction(numerator * fra.getNumerator(), denominator * fra.getDenominator());
    }

    // Divide fractions
    public Fraction divideFraction(Fraction fra) {
        return new Fraction(numerator * fra.getDenominator(), denominator * fra.getNumerator());
    }

    // Expoent fractions
    public Fraction expFraction(int exp) {
        if(exp >= 0)
            return new Fraction((int)Math.pow(numerator, exp), (int)Math.pow(denominator, exp));
        else
            return new Fraction((int)Math.pow(denominator, exp* (-1)), (int)Math.pow(numerator, exp*(-1)));
    }

    // Reduction of a fraction
    public Fraction reduce() {
        int mdc = MDC(numerator,denominator);
        if(mdc > 1)
            return new Fraction(numerator/mdc, denominator/mdc);
        else 
            return new Fraction(numerator, denominator);
    }
    
    public int MDC(int n, int d) {
        if(d == 0)
            return n;
        else
            return MDC(n, n%d);
    }

    public String toString() {
        return getNumerator() + "/" + getDenominator();
    }

}
