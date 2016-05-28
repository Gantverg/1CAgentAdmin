package sample.console.ui.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public final class MainFrame
    extends JFrame
    implements Runnable
{
    public MainFrame(JPanel contents, String title)
    {
        setTitle(title);
        getContentPane().add(contents);
        pack();
    }

    @Override
    public void run()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
