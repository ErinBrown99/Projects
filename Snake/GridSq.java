/*  Name: Erin Brown
 *  PennKey: erbr
 *  Recitation: 220
 *
 *  Execution: java GridSq
 *
 *  This class creates and handles the methods related to
 *  the grid background in the game. There are also functions
 *  to make aspects of the grid available in other classes of 
 *  game.
 *  
 */
public class GridSq {
    private Square[][] board;
    private int boardSize;
    
    /*
     * Input: integer "size"
     * Output: none, is object constructor, creates an object
     * Contructor of Grid background, composed using
     * double array of squares
     */
    public GridSq(int size) {
        boardSize = size;
        board = new Square[(size * 10) + 6][(size * 10) + 6];
        double dimension =  1.0 / ((size + 0.6) * 10);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Square((0.5 + j) * dimension, 
                                         (0.5 + i) * dimension, 
                                         dimension - .0025);
            }
        }
    }
    
    /*
     * No input
     * Return int
     * Returns size value used to construct the grid,
     * made so that this int will be accessible outside
     * of this function
     */
    public int getSize() {
        return boardSize;
    }
    
    /*
     * no input
     * returns int
     * Gives length of board, or the number of squares
     * created for the board
     */
    public int getLength() {
        return board.length;
    }
    
    /*
     * Input: integer
     * Output: double value
     * Gets the X coordinate for a certain column in the grid
     */
    public double getXValue(int index) {
        return board[index][index].getCenterX();
    }
    
    /*
     * Input: Integer
     * Output: double value
     * Gets the Y coordinate for a certain row in the grid
     */
    public double getYValue(int index) {
        return board[index][index].getCenterY();
    }
    
    /*
     * No input
     * return type void, none
     * Draws out all the Squares of the grid, making one
     * collective grid to be used as background for game
     */
    public void drawGrid() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j].draw();
            }
        }
    }
}