public class Fraction {
    private int num, den;
    private boolean error = false;

    public Fraction(int num, int den) {
        this.num = num;
        this.den = den;

        if(den <= 0) {
            this.num = -num;
            this.den = -den;
        }
    }

    public Fraction(int num) {
        this.num = num;
        this.den = 1;
    }

    public Fraction(String frac) {
        String[] part = frac.split("/");
        try{
            switch(part.length) {
                case 1:
                    num = Integer.parseInt(part[0]);
                    break;
                case 2:
                    break;
                default:
                    error = true;
            }
        } catch(NumberFormatException e) {
            error = true;
        }        
    }

    public int num() {
        return num;
    }

    public int den() {
        return den;
    }

    public boolean error() {
        return error;
    }

    public Fraction reduce() {
        int mdc = mdc(num, den);
        return new Fraction(num / mdc, den / mdc);
    }

    public int mdc(int a, int b) {
        int res = a;
        if(b != 0) {
            res = mdc(b, a % b);
        } else if(a == 0) {
            res = 1;
        }

        return res;
    }

    @Override
    public String toString() {
        return "" + num + (den == 1 ? "" : "/" + den);
    }
}