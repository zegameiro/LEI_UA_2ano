public class Vector {
    private double x;
    private double y;
    private boolean scalar = false;
    private boolean error = false;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(double x) {
        this.x = x;
        this.y = 0;
        this.scalar = true;
    }

    public Vector(String vec) {
        String vector = vec.substring(1, vec.length() - 1);
        String[] parts = vector.split(",");

        try {
            switch (parts.length) {
                case 1:
                    this.x = Double.parseDouble(vector);
                    this.y = 0;
                    break;

                case 2:
                    this.x = Double.parseDouble(parts[0]);
                    this.y = Double.parseDouble(parts[1]);
                    break;

                default:
                    this.error = true;
                    break;
            }
        } catch (NumberFormatException e) {
            this.error = true;
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isvector() {
        return scalar;
    }

    @Override
    public String toString() {
        if (!scalar) {
            if (this.y == 0) {
                return "[" + this.x + "]";
            } else {
                return "[" + this.x + "," + this.y + "]";
            }
        } else {
            return String.valueOf(this.x);
        }

    }
}
