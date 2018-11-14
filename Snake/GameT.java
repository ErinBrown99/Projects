/*  Name: Erin Brown
 *  PennKey: erbr
 *  Recitation: 220
 *
 *  Execution: java GameT
 *
 *  This class creates and handles the methods related to
 *  the game itself, calling all other classes used for this
 *  project and running the game itself and routing all the 
 *  transitions. No input is necessary; to play the game Snake
 *  just press run on this class.
 *  
 */

import java.util.ArrayList;
public class GameT {
    
    private static SnakeT snake = new SnakeT();
    private static Square food;
    private static GridSq game;
    private static String score;
    private static ArrayList<Integer> allScores = new ArrayList<Integer>();
    private static ArrayList<String> names = new ArrayList<String>();
    
    //takes in String array of arguments (required for code to run)
    //return type == void
    //initiates game functions calling functions that call other
    //functions that make the game look beautiful
    public static void main(String[] args) {
        GridSq board = new GridSq(1);
        PennDraw.setCanvasSize(700, 700);
        PennDraw.clear(PennDraw.GRAY);
        PennDraw.setPenColor(PennDraw.BLACK);
        board.drawGrid();
        PennDraw.setFontBold();
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.setFontSize(40); //made for 700x700 screen
        PennDraw.setFont("Verdana");
        
        //runs start screen and waits for keypress
        welcome();
        
        PennDraw.clear(PennDraw.GRAY);
        PennDraw.setPenColor(PennDraw.BLACK);
        board.drawGrid();
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.setFontSize(48);
        
        //runs the choose level function
        chooseLevel();
    }
    
    //takes in an integer that is one-tenth the dimension of the
    //   playable board
    //return type void
    //creates and draws a food (type Square) on one of the grid squares
    public static void food(int size) {
        //creates random numbers cast to ints to be used as index for grid
        int foodX = (int) (Math.random() * size * 10);
        int foodY = (int) (Math.random() * size * 10);
        
        //uses ints to choose a random grid square coordinate to draw food
        double gridX = game.getXValue(foodX);
        double gridY = game.getYValue(foodY);
        
        //if on border
        if (gridX <= 3.0 / ((size * 10.0) + 6.0)) {
            food(size);
            return;
        }
        if (gridY <= 3.0 / ((size * 10.0) + 6.0)) {
            food(size);
            return;
        }
        if (gridX >= ((size * 10.0) + 2.5) / 
            ((size * 10) + 6.0)) {
            food(size);
            return;
        }
        if (gridY >= ((size * 10.0) + 2.5) / 
            ((size * 10) + 6.0))  {
            food(size);
            return;
        }
        
        //if on snake
        for (int i = 0; i < snake.size(); i++) {
            //marginalized to make up for decimal discrepancy
            if (gridX + (0.5 / ((size * 10.0) + 6.0)) > snake.getXValue(i) && 
                gridY + (0.5 / ((size * 10.0) + 6.0)) > snake.getYValue(i) &&
                gridX - (0.5 / ((size * 10.0) + 6.0)) < snake.getXValue(i) &&
                gridY - (0.5 / ((size * 10.0) + 6.0)) < snake.getYValue(i)) {
                food(size);
                return;
            }
        }
        food = new Square(gridX, gridY, 1.0 / ((size * 10) + 6));
        food.draw();
    }
    
    /* 
    * Input: input type integer that gives one-tenth playable board
    * Output: none
    * Description: controls the screen where the actual game is played
    * Based off of what was input in chooseLevel()
;
    */ 
    public static void play(int level) {
        PennDraw.enableAnimation(7);
        PennDraw.clear(PennDraw.GRAY);
        PennDraw.setPenColor(PennDraw.BLACK);
        game = new GridSq(level);
        game.drawGrid();
        snake.setGrid(level);
        
        //how board is divided
        double dimension = 1.0 / ((level + 0.6) * 10); 
        double first = game.getXValue((int) (5.0 * (level + 0.6)));
        
        //first three squares of snake
        snake.addSquare(new Square(first, first, dimension));
        snake.addSquare(new Square(first + dimension, first, dimension));
        snake.addSquare(new Square(first + dimension, first, dimension));
        
        //set initial direction to W
        snake.turn("W");
        
        //draw first food
        food(level);
        
        //intialize the score
        score = "0";
        
        while (snake.isgameOver() == false) {
            //draw background grid
            PennDraw.clear(PennDraw.GRAY);
            PennDraw.setPenColor(PennDraw.BLACK);
            game.drawGrid();
            
            //draw border
            PennDraw.setPenColor(PennDraw.GRAY);
            PennDraw.filledRectangle(0.5, ((level * 10.0) + 4.5) * dimension, 
                                              0.5, 1.5 * dimension);
            PennDraw.filledRectangle(1.5 * dimension, 0.5, 1.5 * dimension, 
                                     0.5);
            PennDraw.filledRectangle(((level * 10) + 4.5) * dimension, 0.5, 
                                     1.5 * dimension, 0.5);
            PennDraw.filledRectangle(0.5, 1.5 * dimension, 0.5, 
                                     1.5 * dimension);
            
            PennDraw.setPenColor(PennDraw.GREEN);
            snake.drawSnake();
            score = (snake.size() * 100) - 300 + "";
            PennDraw.text(0.85, ((level * 10.0) + 4.5) * dimension, score);
            food.draw();
            snake.move();
            PennDraw.advance();
            
            //take in key presses to (possibly) change directions
            if (PennDraw.hasNextKeyTyped() == true) {
                String s = "";
                s += PennDraw.nextKeyTyped();
                s = s.toUpperCase();
                snake.turn(s);
                food.draw();
            }
            
            //check to see if food has been gobbled up
            //multiplied by 50 and cast to int to neutralize some of the 
            //decimal discrepancy
            //adds new square of snake behind snake's next move
            if ((int) (snake.getXValue(0) * 50) == 
                (int) (food.getCenterX() * 50) &&
                (int) (snake.getYValue(0) * 50) == 
                (int) (food.getCenterY() * 50)) {
                Square eating = new Square(snake.getXValue(snake.size() - 1), 
                                           snake.getYValue(snake.size() - 1),
                                           dimension);
                food(level);
                snake.drawSnake();
                food.draw();
                snake.move();
                snake.addSquare(eating);
                PennDraw.advance();
            }
        }
        PennDraw.disableAnimation();
        int finalScore = Integer.parseInt(score);
        allScores.add(finalScore);
    }
    
    /*
     * Takes in no input
     * Return type is null
     * Creates the screen once you lose, called in chooseLevel()

     * after while loop breaks. Once keypressed, sends you to
     * high score list
     */
    public static void gameOver() {
        String s = "";
        PennDraw.setPenColor(PennDraw.RED);
        game.drawGrid();
        PennDraw.setFontSize(50);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(0.5, 0.75, "GAME OVER"); 
        PennDraw.setFontSize(28);
        PennDraw.text(0.5, 0.4, "PRESS ANY KEY TO CONTINUE");
        
        //loop that waits for a key to be pressed
        while (PennDraw.hasNextKeyTyped() == false) {
        
        }
        
        //once key is pressed, move on to high scores
        if (PennDraw.hasNextKeyTyped()) {
            //add to s anyway just so this keypress is "processed"
            s += PennDraw.nextKeyTyped();
            highScore();
        }
    }
    
    /*
     * Input: integer array, and two other ints
     * Output type void
     * Switches the position of two ints in int array
     */
    public static void swapInt(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    
    /*
     * Input: String array, and two ints
     * Output type void
     * Switches the position of two Strings in int array
     */
    public static void swapStr(String[] arr, int i, int j) {
        String temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    
    /*
     * Input: none
     * Output type void
     * Sorts all of the scores least to greatest (to be reversed later)
     * Because the scores and names line up (the first index of allScores is
     * paired with the first index of names), also sorts names
     */
    public static void sort() {
        //temporary arrays to be sorted (bc harder to use swap for ArrayLists)
        int[] scoreTemp = new int[allScores.size()];
        String[] nameTemp = new String[names.size()];
        
        //transfers scores and names to temp arrays
        for (int i = 0; i < allScores.size(); i++) {
            scoreTemp[i] = allScores.get(i);
            nameTemp[i] = names.get(i);
        }
        
        //actual sorting (selection sort)
        for (int i = 0; i < scoreTemp.length - 1; i++) {
            int min = scoreTemp[i];
            int minIndex = i;
            for (int j = i + 1; j < scoreTemp.length; j++) {
                if (scoreTemp[j] < min) {
                    min = scoreTemp[j]; 
                    minIndex = j;
                }
            }
            swapInt(scoreTemp, i, minIndex);
            swapStr(nameTemp, i, minIndex);
        }
        
        //clear out ArrayLists to make way for sorted data
        for (int i = 0; i < scoreTemp.length; i++) {
            allScores.remove(0);
            names.remove(0);
        }
        
        //transfer sorted data from arrays to ArrayLists
        for (int i = 0; i < scoreTemp.length; i++) {
            allScores.add(scoreTemp[i]);
            names.add(nameTemp[i]);
        }
    }
    
    /*
     * Input: none
     * Output type void
     * Displays high score screen, lets you type three letters
     */
    public static void highScore() {
        PennDraw.clear(PennDraw.GRAY);
        GridSq board = new GridSq(1);
        board.drawGrid();
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.setFontSize(45);
        PennDraw.text(0.5, 0.8, "TYPE 3 INITIALS NOW");
        String s = "";
        
        //wait for key press
        while (PennDraw.hasNextKeyTyped() == false) {
            
        }
        
        //once key is pressed, collect 3 keypresses 
        if (PennDraw.hasNextKeyTyped()) {
            while (s.length() < 3) {
                if (PennDraw.hasNextKeyTyped()) {
                    s += PennDraw.nextKeyTyped();
                }
            }
            names.add(s.toUpperCase());
            sort();
            
            //print names and scores
            for (int i = 0; i < names.size(); i++) {
                PennDraw.setFontSize(28);
                PennDraw.text(0.4, 0.7 - (0.1 * i), 
                              names.get(names.size() - 1 - i));
                PennDraw.text(0.6, 0.7 - (0.1 * i), 
                              allScores.get(names.size() - 1 - i) + "");
            }
        }
        PennDraw.text(0.5, 0.9, "TO REPLAY, TYPE R");
        
        //loop can go on forever because I'm calling another function anyway
        while (true) {
            while (PennDraw.hasNextKeyTyped() == false) {
                
            }
            
            if (PennDraw.nextKeyTyped() == 'r') {
                snake = new SnakeT();
                String[] args = new String[1];
                main(args);
            }
        }
    }
    
    /*
     * No input
     * Return type void
     * Creates the screen in which you are able to choose 
     * which size grid you want
     * Is called in the main function. First calls the play
     * function based on keypresses, then later calls gameOver()
     */
    public static void chooseLevel()
 {
        while (snake.isgameOver() == false) {
            
            PennDraw.setFontSize(54);
            PennDraw.text(0.5, 0.8, "PICK A LEVEL");
            
            PennDraw.setFontSize(50);
            PennDraw.text(0.35, 0.6, "10 x 10");
            PennDraw.text(0.35, 0.4, "20 x 20");
            PennDraw.text(0.35, 0.2, "30 x 30");
            
            PennDraw.setFontSize(22);
            PennDraw.text(0.7, 0.6, "FOR THIS, TYPE 1");
            PennDraw.text(0.7, 0.4, "FOR THIS, TYPE 2");
            PennDraw.text(0.7, 0.2, "FOR THIS, TYPE 3");
            
            String s = "";
            if (PennDraw.hasNextKeyTyped()) {
                s += PennDraw.nextKeyTyped();
                if (s.equals("1") || s.equals("2") || s.equals("3")) {
                    play(Integer.parseInt(s));
                }
            }
        }
        gameOver();
    }
    
    /*
     * No input
     * Nothing returned
     * Called from main function, just waits for key press to start game
     */
    public static void welcome()
 {
        while (PennDraw.hasNextKeyTyped() == false) {
            PennDraw.text(0.5, 0.5, "PRESS ANY KEY TO START");
        }
    }
}