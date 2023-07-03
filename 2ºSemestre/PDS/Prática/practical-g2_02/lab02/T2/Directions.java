public enum Directions {
        UP,
        DOWN,
        LEFT, 
        RIGHT,
        UP_LEFT,
        UP_RIGHT, 
        DOWN_LEFT, 
        DOWN_RIGHT;

        public static Directions getDirection(String direction) {
            switch (direction) {
                case "UP":
                    return UP;
                case "DOWN":
                    return DOWN;
                case "LEFT":
                    return LEFT;
                case "RIGHT":
                    return RIGHT;
                case "UP_LEFT":
                    return UP_LEFT;
                case "UP_RIGHT":
                    return UP_RIGHT;
                case "DOWN_LEFT":
                    return DOWN_LEFT;
                case "DOWN_RIGHT":
                    return DOWN_RIGHT;
                default:
                    return null;
            }
        }
}
