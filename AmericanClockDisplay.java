/**
 * The ClockDisplay class implements a digital clock display for a
 * American-style 12 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00am (midnight) to 11:59pm (one minute before 
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
public class AmericanClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;
    private String dayTime;
    private String americanHours;
    
    private boolean isAlarmActive;
    private int alarmHour;
    private int alarmMinute;
    private String alarmDayTime;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00am.
     */
    public AmericanClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        dayTime = "am";
        isAlarmActive = false;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public AmericanClockDisplay(int hour, int minute, boolean isPM)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        isAlarmActive = false;
        setTime(hour, minute, isPM);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            if (hours.getValue() == 0)
            {
                dayTime = dayTime.equals("am") ? "pm" : "am";
            }
        }
        
        if (isAlarmActive && hours.getValue() == alarmHour 
            && minutes.getValue() == alarmMinute && dayTime.equals(alarmDayTime))
        {
            System.out.println("Riiiiiiiing!");
        }
        
        updateDisplay();
    }
 
    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, boolean isPM)
    {
        if (hour < 0 || hour > 12 || minute < 0 || minute > 59)
        {
            throw new IllegalArgumentException("Please enter valid values for the time.");
        }
        hours.setValue(hour);
        minutes.setValue(minute);
        dayTime = isPM ? "pm" : "am";
        updateDisplay();
    }

    /**
     * Return the current time of this display in the american time format.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Sets the alarm to the time specified in the parameters
     * @param hour The alarm time hour
     * @param minute The alarm time minute
     * @param isPM Set to true for pm or to false for am
     */
    public void setAlarm(int hour, int minute, boolean isPM)
    {
        if (hour < 0 || hour > 12 || minute < 0 || minute > 59)
        {
            throw new IllegalArgumentException("Please enter valid values "
                                                + "for the alarm time.");
        }
        alarmHour = hour;
        alarmMinute = minute;
        alarmDayTime = isPM ? "pm" : "am";
    }
    
    /**
     * Activates the alarm
     */
    public void activateAlarm()
    {
        isAlarmActive = true;
    }
    
    /**
     * Deactivates the alarm
     */
    public void deactivateAlarm()
    {
        isAlarmActive = false;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        americanHours = (hours.getValue() == 0) ? "12" : hours.getDisplayValue();
        
        displayString = americanHours + ":" + 
                        minutes.getDisplayValue() + dayTime;
    }
}