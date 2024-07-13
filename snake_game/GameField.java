import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Timer;

public class GameField extends JPanel 
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
    private int snakeSize = 3;
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
    }
    public void loadImageIcons()
    {
        ImageIcon imageApple = new ImageIcon("apple.png");
        ImageIcon imageDot = new ImageIcon("dot.png");
        this.apple = imageApple.getImage();
        this.dot = imageDot.getImage();
    }
}
