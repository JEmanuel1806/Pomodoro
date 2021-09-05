import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JButton startButton;
    private JButton resetButton;
    private JLabel clock;
    private JButton pauseButton;
    private JButton exitButton;
    private JLabel roundLabel;
    private JLabel status;
    private JCheckBox autoStart;
    public boolean timerStarted = false;

    public int seconds;
    public int minutes;
    public int time = 3000000;
    public boolean pause = false;
    public int round;


    ActionListener counter = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            time = time - 1000;
            seconds = (time / 1000) % 60;
            minutes = (time / 60000) % 60;
            clock.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            //Work ends, Pause starts
            if (time == 0 && !pause) {
                if (round == 4){
                    time = 3600000;
                    round = 0;}
                else
                    time = 600000;
            }
            //Pause ends, Work starts
            else if (time == 0 && pause) {
                round = round + 1;
                roundLabel.setText("Round: " + round+ "/4");
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
        this.exitButton.addActionListener(this);

        this.autoStart.addActionListener(this);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton)
                    System.exit(0);
            }
        });
        autoStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==autoStart) {
                    if (time == 0) {
                        System.out.println("Test");
                        timer.stop();
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new GUI("Pomodoro Timer");
        frame.setResizable(false);
        frame.setBounds(300, 300, 400, 400);

        frame.dispose();
        frame.setUndecorated(true);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (timerStarted) {
                stop();
                status.setText("");
                timerStarted = false;
                startButton.setText("START");
            } else {
                start();
                status.setText("Working hard...:)");
                startButton.setText("STOP ");
                timerStarted = true;

            }
        }

        if (e.getSource() == resetButton) {
            reset();
        }
        if (e.getSource() == pauseButton) {
            if (pause == false) {
                status.setText("relax a bit...");
                clock.setText("10:00");
                time = 600000;
                pauseButton.setText("WORK");
                pause = true;
            } else if (pause == true) {
                status.setText("Working hard...");
                clock.setText("50:00");
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
        status.setText("");
        timer.stop();
        timerStarted = false;
        time = 3000000;
        round = 0;
        clock.setText("50:00");
        startButton.setText("START");
        pauseButton.setText("PAUSE");
        pause = false;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}


