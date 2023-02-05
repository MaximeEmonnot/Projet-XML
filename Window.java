import javax.swing.JFrame;

public class Window extends JFrame {
    private Window() {
        setTitle("XML Engine");
        setBounds(50, 50, width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(Keyboard.GetInstance());
        addMouseListener(Mouse.GetInstance());
        addMouseWheelListener(Mouse.GetInstance());
        addMouseMotionListener(Mouse.GetInstance());
    }

    public static Window GetInstance() {
        if (instance == null)
            instance = new Window();
        return instance;
    }

    private static Window instance = null;

    private final int width = 800;
    private final int height = 800;
}
