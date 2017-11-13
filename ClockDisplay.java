import java.util.Calendar;
import java.util.Timer;

/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kolling and David J. Barnes
 * @author Luis Hankel and Pascal Polchow
 * @version 2017.11.07
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayString;
    private String dayTime;
    
    private int alarmHour, alarmMinute;
    private boolean isAlarmActive;
    
    private ClockGUI gui;
    private AmericanClockGUI americanGui;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set to the current system time.
     */
    public ClockDisplay()
    {
        this(Calendar.getInstance().getTime().getHours(), 
                Calendar.getInstance().getTime().getMinutes(), 
                Calendar.getInstance().getTime().getSeconds());
    }
    
    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, int second)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        gui = new ClockGUI();
        americanGui = new AmericanClockGUI();
        setTime(hour, minute, second);
        new Timer().schedule(new Clock(this), (System.currentTimeMillis() % 1000), 1000);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        seconds.increment();
        if(seconds.getValue() == 0)
        {
            minutes.increment();
            if(minutes.getValue() == 0)
            {
                hours.increment();
            }
        }
        
        if (isAlarmActive && hours.getValue() == alarmHour 
            && minutes.getValue() == alarmMinute)
        {
            System.out.println("Riiiiiiiing!");
        }
        
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, int second)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Returns the current time of this display in the american time format.
     */
    public String getAmericanTime()
    {
        int americanHours;
        
        if (hours.getValue() >= 12)
        {
            dayTime = "pm";
        }
        else 
        {   
            dayTime = "am";
        }
            
        americanHours = hours.getValue() % 12;
        
        if(americanHours == 0)
        {
            americanHours = 12;
        }
        
        return americanHours+ ":" + minutes.getDisplayValue()+ dayTime;
    }
    
    /**
     * Sets the alarm to the time specified in the parameters
     * @param hour The alarm time hour
     * @param minute The alarm time minute
     */
    public void setAlarm(int hour, int minute)
    {
        alarmHour = hour;
        alarmMinute = minute;
        gui.updateAlarm(alarmHour, alarmMinute);
    }
    
    /**
     * Activates the alarm
     */
    public void activateAlarm()
    {
        isAlarmActive = true;
        gui.toggleAlarm(true);
    }
    
    /**
     * Deactivates the alarm
     */
    public void deactivateAlarm()
    {
        isAlarmActive = false;
        gui.toggleAlarm(false);
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + ":" + seconds.getDisplayValue();
        gui.update(displayString);
        
        americanGui.update(getAmericanTime());
    }
}
