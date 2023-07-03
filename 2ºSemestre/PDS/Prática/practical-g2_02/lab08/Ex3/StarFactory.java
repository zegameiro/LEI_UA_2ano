
import startypes.*;

import java.util.*;

public class StarFactory {

    private static HashMap<Character, StarType> stars;

    public StarFactory() {
        stars = new HashMap<Character, StarType>();
    }

    public Star createStar(char type) {
        
        int x = random(0, Demo.CANVAS_SIZE);
        int y = random(0, Demo.CANVAS_SIZE);
        StarType star=null;

        if(stars.containsKey(type)) {
            star = stars.get(type);
            return new Star(x, y, star);
        } 

        switch (type) {
            case 'O' : star = new OStar(x, y); break;
            case 'A' : star = new AStar(x, y); break;
            case 'B' : star = new BStar(x, y); break;
            case 'F' : star = new FStar(x, y); break;
            case 'G' : star = new GStar(x, y); break;
            case 'K' : star = new KStar(x, y); break;
            case 'M' : star = new MStar(x, y); break;
        }
        
        stars.put(type, star);

        return new Star(x, y, star);
    }

	public static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


}