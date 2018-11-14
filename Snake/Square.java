/*  Name: Erin Brown
 *  PennKey: erbr
 *  Recitation: 220
 *
 *  Execution: java Square
 *
 *  This class creates and handles the methods related to
 *  the squares that make up the snake, the gridboard, and 
 *  the food.
 *  
 */
public class Square {
    private double x; 
    private double y;
    private double sideLength;
    
    /*
     * Input: a double, a double, and another double
     * Output: none (constructor)
     * Constructs Square by initialing parameters in input
     */
    public Square(double x, double y, double sideLength) {
        this.x = x;
        this.y = y;
        this.sideLength = sideLength;
    }
    
    /*
     * input: none
     * output: double
     * Returns x coordinate of center of Square
     */
    public double getCenterX() {
        return x;
    }
    
    /*
     * input: none
     * output: double
     * Returns y coordinate of center of Square
     */
    public double getCenterY() {
        return y;
    }
    
    /*
     * input: none
     * output: none (void)
     * Draws the Square
     */
    public void draw() {
        PennDraw.filledSquare(x, y, sideLength * 0.5);
    }
}