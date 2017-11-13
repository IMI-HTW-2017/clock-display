import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.concurrent.TimeUnit;

/**
 * Displays a GUI for the clock that updates automatically.
 *
 * @author Luis Hankel
 * @version 2017.11.10
 */
public class AmericanClockGUI extends JFrame
{
    private JPanel panel;
    private JPanel panelTime;
    private JPanel panelAlarm;
    
    private Font fontTime;
    private Font fontAlarm;
    
    private JLabel lblHours;
    private JLabel lblMinutes;
    private JLabel lblColon;
    private JLabel lblDayTime;
    
    private JLabel lblAlarm;
    private JLabel lblAlarmState;

    /**
     * Constructor for objects of class ClockGUI
     */
    public AmericanClockGUI()
    {
        setTitle("American Clock");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setContentPane(panel);
        
        panelTime = new JPanel();
        panelTime.setLayout(new FlowLayout());
        panel.add(panelTime);
        
        panelAlarm = new JPanel();
        panelAlarm.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.add(panelAlarm);
        
        fontTime = new Font("Sans Serif", Font.PLAIN, 80);
        fontAlarm = new Font("Sans Serif", Font.PLAIN, 30);
        
        lblHours = new JLabel("12");
        lblHours.setFont(fontTime);
        panelTime.add(lblHours);
        
        lblColon = new JLabel(":");
        lblColon.setFont(fontTime);
        panelTime.add(lblColon);
        
        lblMinutes = new JLabel("00");
        lblMinutes.setFont(fontTime);
        panelTime.add(lblMinutes);
        
        lblDayTime = new JLabel("am");
        lblDayTime.setFont(fontTime);
        panelTime.add(lblDayTime);
        
        lblAlarm = new JLabel("00:00");
        lblAlarm.setFont(fontAlarm);
        panelAlarm.add(lblAlarm);
        
        lblAlarmState = new JLabel("off");
        lblAlarmState.setFont(fontAlarm);
        panelAlarm.add(lblAlarmState);
        
        pack();
        setVisible(true);
    }
    
    /**
     * Updates the clock GUI
     * @param displayTime The time String to display on the GUI
     */
    public void update(String displayTime)
    {
        String[] time = displayTime.split(":");
        
        lblHours.setText(time[0]);
        lblMinutes.setText(time[1].substring(0, 2));
        lblDayTime.setText(time[1].substring(2, time[1].length()));
        lblColon.setText(" ");
        revalidate();
        repaint();
        
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        lblColon.setText(":");
        revalidate();
        repaint();
    }
    
    public void updateAlarm(int alarmHour, int alarmMinute)
    {
        String hour = Integer.toString(alarmHour);
        String minute = Integer.toString(alarmMinute);
        lblAlarm.setText((hour.length() == 1 ? "0" + hour : hour) + ":"
                            + (minute.length() == 1 ? "0" + minute : minute));
    }
    
    public void toggleAlarm(boolean state)
    {
        String alarmState = state ? "on" : "off";
        lblAlarmState.setText(alarmState);
    }
}
