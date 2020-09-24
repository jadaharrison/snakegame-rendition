import java.awt.Color;
import java.awt.Graphics;


public class SnakeJoint implements GameBasics {

    private int x;
    private int y;

    public SnakeJoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(getX(), getY(), gridSize, gridSize);
        
    }

}
