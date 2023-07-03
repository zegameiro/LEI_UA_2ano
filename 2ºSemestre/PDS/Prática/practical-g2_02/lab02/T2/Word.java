public class Word {
    private String value;
    private int x;
    private int y;
    private int size;
    private Directions direction;

    public Word(String value){
        this.value = value;
        this.size = value.length();
    }

    public Word(String value, Directions direction, int x, int y){
        this.value = value;
        this.size = value.length();
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    // Getters
    public String getValue(){
        return this.value;
    }
    
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getSize(){
        return this.size;
    }

    public Directions getDirection(){
        return this.direction;
    }

    // Setters

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDirection(Directions direction){
        this.direction = direction;
    }

    @Override
    public String toString(){
        return String.format("%-20s %-5d (%-2d,%-2d) %s",this.value, this.size ,this.x, this.y, this.direction);
    }

}
