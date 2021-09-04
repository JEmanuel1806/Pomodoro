import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JButton startButton;
    private JButton resetButton;
    private JLabel clock;
    private JButton pauseButton;
    public boolean timerStarted = false;

    public int seconds;
    public int minutes;
    public int time = 3000000;
    public boolean pause = false;


    ActionListener counter = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            time = time - 1000;
            seconds = (time / 1000) % 60;
            minutes = (time / 60000) % 60;
            clock.setText(minutes + ":" + seconds);
            if (time == 0 && !pause) {
                time = 600000;
            } else if (time == 0 && pause) {
                time = 3000000;
            }

        }
    };


    Timer timer = new Timer(1000, counter);


    // GUI Constructor
    public GUI(String title) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        this.startButton.addActionListener(this);
        this.resetButton.addActionListener(this);
        this.pauseButton.addActionListener(this);

    }


    public static void main(String[] args) {
        JFrame frame = new GUI("Pomodoro Timer");
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (timerStarted) {
                stop();
                timerStarted = false;
                startButton.setText("Start");
            } else {
                start();
                startButton.setText("Stop");
                timerStarted = true;

            }
        }

        if (e.getSource() == resetButton) {
            reset();
        }
        if(e.getSource() == pauseButton){
            if(pause == false){
                clock.setText("10:00");
                time = 600000;
                pauseButton.setText("WORK");
                pause = true;
            }
            else if(pause == true){
                time = 3000000;
                pauseButton.setText("START");
                pause = false;
            }
        }

    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        timerStarted = false;
        time = 3000000;
        clock.setText("50:00");
        startButton.setText("Start");
        pauseButton.setText("Pause");
        pause = false;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


