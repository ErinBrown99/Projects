/*  Name: Erin Brown
 *  PennKey: erbr
 *  Recitation: 220
 *
 *  Execution: java SnakeT
 *
 *  This class creates and handles the methods related to
 *  the actual snake of the game. The snake is composed of
 *  Squares (the object) which are set into an ArrayList.
 *  This class controls the movement, the turning, checking
 *  to see if the game is over, as well as methods that
 *  give useful information to the game class.
 * 
 */
import java.util.ArrayList;
import java.util.List;
public class SnakeT {
    //snake is a List because I wanted to use function that
    //add at a certain index, for move function
    private List<Square> snake = new ArrayList<Square>();
    private String direction;
    private int gridSize;
    private double dimension;
    
    /*
     * no input
     * no return (constructor)
     * Constructs snake through implicit inialization bc of 
     * the private variables above
     */
    public SnakeT() {
        direction = "W";
        }
    
    /*
     * no input
     * return type int
     * Returns the size (length) of the snake, to be called
     * on by other classes to get this value
     */
    public int size() {
        return snake.size();
    }
    
    /*
     * Input: Square (the object)
     * Return type void, nothing
     * Adds a square to the ArrayList snake, to be called
     * on in the GameT class
     */
    public void addSquare(Square square) {
        snake.add(square);
    }
    
    /*
     * Input: int
     * Output: none
     * Aligns gridsize set in game class with this class
     * so that this variable can be used to know
     * what width to make the Squares of the snake and stuff
     */
    public void setGrid(int gridSize) {
        this.gridSize = gridSize;
        dimension = 1.0 / ((gridSize * 10) + 6);
    }
    
    /*
     * Input: int
     * Output: double
     * Gets the x coordinate of a certain Square in the snake
     */
    public double getXValue(int index) {
        return snake.get(index).getCenterX();
    }
    
    /*
     * Input: int
     * Output: double
     * Gets the y coordinate of a certain Square in the snake
     */
    public double getYValue(int index) {
        return snake.get(index).getCenterY();
    }
     /*
     * Input: none
     * Output: none (void)
     * Draws out each Square in the ArrayList to create
     * the snake on the board
     */
    public void drawSnake() {
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw();
        }
    }
    
    /*
     * Input: none
     * Output: none
     * Adds a new Square in front of the previously first
     * Square and removes the Square from the back to 
     * make it appear as though the snake is moving.
     * Where the new Square is added depends on what the
     * direction is set to.
     */
    public void move() {
        //if moving up the screen
        if (direction.equals("W")) {
            snake.add(0, new Square(snake.get(0).getCenterX(), 
                                 snake.get(0).getCenterY() + 
                                 dimension,
                                 dimension));
            
            snake.remove(snake.size() - 1);
        }
        
        //if moving right
        if (direction.equals("D")) {
            snake.add(0, new Square(snake.get(0).getCenterX() + 
                                 dimension, snake.get(0).getCenterY(),
                                 dimension));
            
            snake.remove(snake.size() - 1);
        }
        
        //if moving down
        if (direction.equals("S")) {
            snake.add(0, new Square(snake.get(0).getCenterX(), 
                                 snake.get(0).getCenterY() - 
                                 dimension,
                                 dimension));
            
            snake.remove(snake.size() - 1);
        }
        
        //if moving left
        if (direction.equals("A")) {
            snake.add(0, new Square(snake.get(0).getCenterX() - 
                                 dimension, snake.get(0).getCenterY(),
                                 dimension));
            
            snake.remove(snake.size() - 1);
        }
    }
    
    /*
     * Input: String
     * Output: none (void)
     * Changes the String direction that tells move() where
     * to place new Squares. Only accepts W, A, S, and D, and
     * doesn't allow one-press 180 degree turns that would
     * kill snake
     */
    public void turn(String s) {
        if (direction.equals("W")) {
            if (s.equals("A") || s.equals("D")) {
                direction = s;
            }
            else {
                direction = "W";
            }
        }
        
        if (direction.equals("D")) {
            if (s.equals("W") || s.equals("S")) {
                direction = s;
            }
            else {
            direction = "D";
            }
        }
        
        if (direction.equals("S")) {
            if (s.equals("A") || s.equals("D")) {
                direction = s;
            }
            else {
            direction = "S";
            }
        }
        
        if (direction.equals("A")) {
            if (s.equals("W") || s.equals("S")) {
                direction = s;
            }
            else {
            direction = "A";
            }
        }
    }
    
    /*
     * Input: none
     * Output: boolean
     * Sets up the parameters for the snake's survival. If the
     * head of the snake is at any point of its tail or the border,
     * the game is over.
     */
    public boolean isgameOver() {
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).getCenterX() == snake.get(0).getCenterX() && 
                snake.get(i).getCenterY() == snake.get(0).getCenterY()) {
                return true;
            }
        }
        if (snake == null || snake.size() == 0) {
            return false; //game hasn't even started
        }
        if (snake.get(0).getCenterX() <= 3.0 / ((gridSize * 10) + 6.0)) {
            return true;
        }
        if (snake.get(0).getCenterY() <= 3.0 / ((gridSize * 10) + 6.0)) {
            return true;
        }
        if (snake.get(0).getCenterX() >= ((gridSize * 10) + 3.0) / 
            ((gridSize * 10) + 6.0)) {
            return true;
        }
        if (snake.get(0).getCenterY() >= ((gridSize * 10) + 3.0) / 
            ((gridSize * 10) + 6.0)) {
            return true;
        }
        return false;
    }
}