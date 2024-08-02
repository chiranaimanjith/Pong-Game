import javax.swing.JFrame;

public class PongGame extends JFrame {

    public PongGame() {
        setTitle("Pong Game");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new GamePanel());
    }
}
