import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.stream.XMLInputFactory;


public class MiniFrame extends JFrame implements KeyListener {

    public Frame frame;
    public String airline;
    public ArrayList<Passenger> passengers;


    public MiniFrame(JFrame frame, String airline) {
        addKeyListener(this);
        setFocusable(true);
        this.frame = frame;
        this.airline = airline;

    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            frame.setVisible(false);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public MiniFrame() {
        addKeyListener(this);
        setFocusable(true);
    }
    public MiniFrame(String airline, ArrayList<Passenger> passengers) {
        this.airline = airline;
        this.passengers = passengers;

        addKeyListener(this);
        setFocusable(true);

    }
//MORE STUFF

    public void displayPopUp() {
        JFrame frame = new JFrame();
        JFrame miniFrame = new MiniFrame(frame, this.airline);
        GridBagLayout gridBagLayout = new GridBagLayout();
        miniFrame.setLayout(gridBagLayout);
        miniFrame.setTitle("");
        miniFrame.setResizable(false);
        miniFrame.setSize(200, 200);
        miniFrame.setMinimumSize(new Dimension(200, 200));
        JPanel pane = new JPanel();
        BorderLayout layout = new BorderLayout();
        pane.setLayout(layout);
        Font font = new Font("serif", Font.BOLD, 15);
        JLabel label1;
        if (airline.equals("Delta Airlines"))
            label1 = new JLabel(this.airline + passengers.size() + " / " + " 200");
        else if (airline.equals("Southwest Airlines"))
            label1 = new JLabel(this.airline + passengers.size() + " / " + " 100");
        else
            label1 = new JLabel(this.airline + passengers.size() + " / " + " 100");
        label1.setFont(font);
        pane.add(label1, BorderLayout.PAGE_START);

        JTextArea textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(150, 80));
        textArea.setEditable(false);

        JScrollPane scroll = new JScrollPane();
        //getContentPane().add(scroll);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().add(textArea);
        scroll.setPreferredSize(new Dimension(150, 70));
        scroll.getViewport().add(textArea);
        //scroll.setWheelScrollingEnabled(true);
        //scroll.setBounds(0,0,100,50);

        //pane.add(textArea, BorderLayout.CENTER);
        for (Passenger p : passengers) {
            textArea.append(p.toString() + "\n");
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        pane.add(scroll);
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                miniFrame.setVisible(false);
            }
        });
        exit.setPreferredSize(new Dimension(20, 20));
        pane.add(exit, BorderLayout.PAGE_END);

        miniFrame.add(pane);
        miniFrame.pack();
        miniFrame.setVisible(true);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });

        miniFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    miniFrame.setVisible(false);
                }
            }
        });


    }

    public static void main(String[] args) {
        new MiniFrame().displayPopUp();
    }
}