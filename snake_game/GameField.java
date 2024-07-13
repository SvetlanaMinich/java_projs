import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener 
{
    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] snakeX = new int[ALL_DOTS];
    private int[] snakeY = new int[ALL_DOTS];
    private int snakeSize;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
 
    public GameField()
    {
        setBackground(Color.BLACK);
        loadImageIcons();
        initGame(); 
        addKeyListener(new KeyFieldListener());
        setFocusable(true);

    }
    public void loadImageIcons()
    {
        ImageIcon imageApple = new ImageIcon("apple.png");
        ImageIcon imageDot = new ImageIcon("dot.png");
        apple = imageApple.getImage();
        dot = imageDot.getImage();
    }

    public void initGame()
    {
        snakeSize = 2;
        for(int i = 0; i < snakeSize; i++)
        {
            snakeX[i] = 32 - i*DOT_SIZE;
            snakeY[i] = 32;
        }
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }

    public void createApple()
    {
        appleX = new Random().nextInt(19) * DOT_SIZE;
        appleY = new Random().nextInt(19) * DOT_SIZE;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(inGame)
        {
            g.drawImage(apple, appleX, appleY, this);
            for(int i = 0; i < snakeSize; i++)
            {
                g.drawImage(dot, snakeX[i], snakeY[i], this);
            }
        }
        else
        {
            String gameOver = "Game over";
            Font f = new Font("Courier", Font.BOLD, 20);
            g.setColor(Color.RED);
            g.setFont(f);
            g.drawString(gameOver, 6 * DOT_SIZE, 10 * DOT_SIZE);
        }
    }

    public void move()
    {
        for(int i = snakeSize; i > 0; i--)
        {
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        if(left)
        {
            snakeX[0] -= DOT_SIZE;
        }
        else if(right)
        {
            snakeX[0] += DOT_SIZE;
        }
        else if(up)
        {
            snakeY[0] -= DOT_SIZE;
        }
        else if(down)
        {
            snakeY[0] += DOT_SIZE;
        }
        if(snakeX[0] > SIZE || snakeX[0] < 0 || snakeY[0] > SIZE || snakeY[0] < 0) inGame = false; 
    }

    public void checkApple()
    {
        if(snakeX[0] == appleX && snakeY[0] == appleY)
        {
            snakeSize++;
            createApple();
        }
    }

    public void checkSnakeCollision()
    {
        for(int i = snakeSize; i > 0; i--)
        {
            if(i > 4 && snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i])
            {
                inGame = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame)
        {
            move();
            checkApple();
            checkSnakeCollision();
        }
        repaint();
    }


    class KeyFieldListener extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right)
            {
                left = true;
                right = false;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left)
            {
                left = false;
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down)
            {
                left = false;
                right = false;
                up = true;
                down = false;
            }
            if(key == KeyEvent.VK_DOWN && !up)
            {
                left = false;
                right = false;
                up = false;
                down = true;
            }
        }
        
    }
}
