
import startypes.StarType;

import java.awt.*;

public class Star {
    private int x;
    private int y;
    private StarType starType;

    public Star(int x, int y, StarType starType) {
        this.x = x;
        this.y = y;
        this.starType = starType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public StarType getStarType() {
        return starType;
    }

    public void draw(Graphics g) {
        g.setColor(starType.getColor());
        g.fillOval(x, y, starType.getSize(), starType.getSize());
    }
}
