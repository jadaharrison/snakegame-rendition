import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;


@SuppressWarnings("serial")
public class GameCourt extends JPanel implements GameBasics {

    // the state of the game logic
    public LinkedList<SnakeJoint> snake; // the Green snake, what is moved around by player 
    private GoodCircle goodCircle; // the circle that makes the snake grow
    private BadCircle badCircle; // the circle that makes the snake shrink

    private boolean playing = false; // whether the game is running 
    private JLabel status; // Current status text, i.e. "Running..."

    private Direction snakeDirection; 

    public static final int INTERVAL = 100;

    public GameCourt(JLabel status) {
        snake = new LinkedList<SnakeJoint>();

        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); 
        
        setFocusable(true);

        // determines what direction is desired by the player, changes it, then moves the snake
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (getDirection()!=Direction.RIGHT) {
                        changeDirection(Direction.LEFT);
                        moveSnake(Direction.LEFT);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (getDirection()!=Direction.LEFT) {
                        changeDirection(Direction.RIGHT);
                        moveSnake(Direction.RIGHT);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (getDirection()!=Direction.UP) {
                        changeDirection(Direction.DOWN);
                        moveSnake(Direction.DOWN);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (getDirection()!=Direction.DOWN) {
                        changeDirection(Direction.UP);
                        moveSnake(Direction.UP);
                    }
                }
            }

        });

        this.status = status;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        snake = new LinkedList<SnakeJoint>();
        initializeSnake();
        goodCircle = new GoodCircle(COURT_WIDTH, COURT_HEIGHT, Color.GREEN);
        goodCircle.moveCircle();
        badCircle = new BadCircle(COURT_WIDTH, COURT_HEIGHT, Color.RED);
        badCircle.moveCircle();

        playing = true;
        status.setText("Running...");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * This method is called every time the timer defined in the constructor triggers.
     */
    void tick() {
        if (playing) {
            // advance the square and snitch in their current direction.
            moveSnake(getDirection());
            if (snake.peekFirst().getX() == goodCircle.getPx() 
                    && snake.peekFirst().getY() == goodCircle.getPy()) {
                goodCircle.changeSnake(snake, getDirection());;
                System.out.print("anything");
                goodCircle.moveCircle();
                status.setText("Good Job!");
            } else {
            
            if (snake.peekFirst().getX() == badCircle.getPx() 
                    && snake.peekFirst().getY() == badCircle.getPy()) {
                    badCircle.changeSnake(snake, getDirection());;
                    badCircle.moveCircle();
                    
                    status.setText("Oh no!");
            } else {
               if (crashesIntoWall() || snake.size() == 0) {
                    playing = false;
                    status.setText("Oh no! Game Over :(");
               }
            }
            }
        }

            // update the display
            repaint();
    
    }
    //code to draw background 
    public void drawGrid(Graphics g) {
        Color gridColor = new Color (240, 162, 179); 
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, COURT_WIDTH, COURT_HEIGHT);
        g.setColor(gridColor);
        for (int col = gridSize; col < (COURT_WIDTH * gridSize); col+=gridSize) {
            g.drawLine(col, 0, col,COURT_WIDTH * gridSize);
        }
        for (int row = gridSize; row < (COURT_HEIGHT * gridSize); row+=gridSize) {
            g.drawLine(0, row, COURT_HEIGHT * gridSize, row);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);

        for (SnakeJoint s: snake) { //draws each joint separately 
            s.draw(g);
        }
        badCircle.draw(g);
        goodCircle.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
    
    
  //==============================================================================
  // Methods to handle the snake, its directions and how the components react
  //==============================================================================
    
    // when starting off the game, snake starts with three joints in the same position every time 
    public void initializeSnake() {
        snake.add(new SnakeJoint (3*GameCourt.gridSize, 3*GameCourt.gridSize));
        snake.addLast(new SnakeJoint (4*GameCourt.gridSize, 3*GameCourt.gridSize));
        snake.addLast(new SnakeJoint (5*GameCourt.gridSize, 3*GameCourt.gridSize));

    }
    
    public Direction getDirection() {
        return this.snakeDirection;
    }
    
    public void changeDirection(Direction d) {
        if (d == Direction.DOWN)
        
        if (d == null) {
            return;
        }
        
        switch (d) {
            case UP:
                    if (getDirection() != Direction.DOWN) {
                        this.snakeDirection = d;
                    }
                break;  
            case DOWN:
                if (getDirection() != Direction.UP) {
                    this.snakeDirection = d;
                }
            case LEFT:
                if (getDirection() != Direction.RIGHT) {
                    this.snakeDirection = d;
                }
                break;
            case RIGHT:
                if (getDirection() != Direction.LEFT) {
                    this.snakeDirection = d;
                }
                break;
            default:
                break;
        }
    }
    
    /* gives the illusion of a moving snake by adding a joint to the front of the snake then 
     * removes the head 
     */
    public void moveSnake(Direction d) {
        SnakeJoint headJoint = snake.peekFirst();
        int headX = headJoint.getX();
        int headY = headJoint.getY();
        SnakeJoint newJoint = headJoint;
        SnakeJoint lastJoint = snake.peekLast(); 
        if (d == null) {
            return;
        }
        
        switch (d) {
            case UP:
                    newJoint = new SnakeJoint (headX, headY-gridSize);
                    snake.addFirst(newJoint);
                break;  
            case DOWN:
                    newJoint = new SnakeJoint (headX, headY+gridSize);
                    snake.addFirst(newJoint);
                break;
            case LEFT:
                    newJoint = new SnakeJoint (headX-gridSize, headY);
                    snake.addFirst(newJoint);

                break;
            case RIGHT:
                    newJoint = new SnakeJoint (headX+gridSize, headY);
                    snake.addFirst(newJoint);
                break;
            default:
                break;
        }
        
        snake.remove(lastJoint);   
    }
    
    
    // if snake crashes into board parameters 
    public boolean crashesIntoWall() {
        int headX = snake.peekFirst().getX();
        int headY = snake.peekFirst().getY();
        
        return (headX < 0 || headX >= COURT_WIDTH || headY < 0 
                || headY >= COURT_HEIGHT - gridSpacing);
    }
}
