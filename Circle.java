import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public abstract class Circle implements GameBasics {
    
    public final int SIZE = 20;
    
    private int POS_X;
    
    private int POS_Y;
    
    
    Circle(int px, int py, int courtWidth, int courtHeight) {
        this.POS_X = px;
        this.POS_Y = py;
    }

    //getters 
    public int getPx() {
        return this.POS_X;
    }

    public int getPy() {
        return this.POS_Y;
    }
    


 // setters 
    public void setPx(int px) {
        this.POS_X = px;
    }

    public void setPy(int py) {
        this.POS_Y = py;

    }
    
    public void moveCircle () {
        Random random = new Random ();
        setPx((random.nextInt((500)/20)*20));
        setPy((random.nextInt((500)/20)*20));
    }
    
    
    public abstract void changeSnake(LinkedList<SnakeJoint> snake, Direction d);
    
    public abstract void draw(Graphics g);
    
}

 //==============================================================================
 // Circle subclasses
 //==============================================================================
    

class GoodCircle extends Circle {

    public static int POS_X;
    public static int POS_Y;
    private Color color;

    public GoodCircle(int courtWidth, int courtHeigth, Color c) {
        super(POS_X, POS_Y, COURT_WIDTH, COURT_HEIGHT);

        this.color = c;
    }
    


    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(getPx(), getPy(), SIZE, SIZE);

    }


    @Override
    public void changeSnake(LinkedList<SnakeJoint> snake, Direction d) {

        SnakeJoint lastJoint = snake.peekLast();
        SnakeJoint newJoint = lastJoint;
        
        if (d == null) {
            return;
        }
        
        switch (d) {
            case UP:
                    newJoint = new SnakeJoint (lastJoint.getX(), (lastJoint.getY())+gridSize);
                break;  
            case DOWN:
                    newJoint = new SnakeJoint (lastJoint.getX(), (lastJoint.getY())-gridSize );
                break;
            case LEFT:
                    newJoint = new SnakeJoint ((lastJoint.getX())+gridSize, lastJoint.getY());
                break;
            case RIGHT:
                    newJoint = new SnakeJoint ((lastJoint.getX())-gridSize, lastJoint.getY());
                break;
            default:
                break;
        }
        snake.addLast(newJoint);        
    }

}

class BadCircle extends Circle {
    
    public static int POS_X;
    public static int POS_Y;
    private Color color;

    public BadCircle(int courtWidth, int courtHeigth, Color c) {
        super(POS_X, POS_Y, COURT_WIDTH, COURT_HEIGHT);

        this.color = c;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(getPx(), getPy(), SIZE, SIZE);
    }

    @Override
    public void changeSnake(LinkedList<SnakeJoint> snake, Direction d) {
        snake.pollLast(); 
        
    }
    
}
