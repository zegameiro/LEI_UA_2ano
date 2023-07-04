public class Complex {
    private int real;
    private int imaginary;

    public Complex(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(int real) {
        this(real, 0);
    }

    public Complex(String complex) {
        String[] parts;
        if (complex.contains("+")) {
            parts = complex.split("\\+");
            this.real = Integer.parseInt(parts[0]);
            this.imaginary = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        } else if (complex.contains("-")) {
            parts = complex.split("-");
            this.real = Integer.parseInt(parts[0]);
            if (parts[1].length() == 1) { // Caso seja apenas "i"
                this.imaginary = -1;
            } else {
                this.imaginary = -Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
            }
        } else {
            if (complex.contains("i")) {
                this.real = 0;
                if (complex.length() > 1) {
                    this.imaginary = Integer.parseInt(complex.substring(0, complex.length() - 1));
                } else {
                    this.imaginary = 1;
                }
            } else {
                this.real = Integer.parseInt(complex);
                this.imaginary = 0;
            }
        }
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        if (this.imaginary == 0) {
            return new Complex(this.real - other.real, other.imaginary);
        } else {
            return new Complex(this.real - other.real, this.imaginary - other.imaginary);
        }
    }

    public Complex multiply(Complex other) {
        return new Complex(this.real * other.real - this.imaginary * other.imaginary,
                this.real * other.imaginary + this.imaginary * other.real);
    }

    public Complex divide(Complex other) {
        System.out.println("real1: " + this.real);
        System.out.println("imaginary1: " + this.imaginary);
        System.out.println("real2: " + other.real);
        System.out.println("imaginary2: " + other.imaginary);

        return new Complex((this.real * other.real + this.imaginary * other.imaginary) /
                (other.real * other.real + other.imaginary * other.imaginary),
                (this.imaginary * other.real - this.real * other.imaginary) /
                        (other.real * other.real + other.imaginary * other.imaginary));
    }

    public int getReal() {
        return this.real;
    }

    public int getImaginary() {
        return this.imaginary;
    }

    public String toString() {
        if (this.imaginary == 0) {
            return this.real + "";
        } else if (this.real == 0) {
            return this.imaginary == 0 ? "0"
                    : (this.imaginary == 1 ? "i" : (this.imaginary == -1 ? "-i" : this.imaginary + "i"));

        } else {
            return this.real +
                    (this.imaginary == 0 ? ""
                            : (this.imaginary > 0 ? " + " : " - ") +
                                    (Math.abs(this.imaginary) == 1 ? "i" : Math.abs(this.imaginary) + "i"));

        }
    }
}
