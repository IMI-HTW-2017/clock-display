import java.util.TimerTask;

/**
 * An automatic clock that increases the time for the connected ClockDisplay.
 *
 * @author Luis Hankel
 * @version 2017.11.10
 */
public class Clock extends TimerTask
{
    private ClockDisplay clockDisplay;
    
    private long nextTimeTick;
    
    /**
     * Creates a new Clock for a clock display
     * @param clockDisp The ClockDisplay the Clock should be taking care of.
     */
    public Clock(ClockDisplay clockDisp)
    {
        this.clockDisplay = clockDisp;
    }
    
    /**
     * Runs while the clockDisplay is set.
     * Executes the timeTick method for the connected ClockDisplay once per second.
     */
    public void run()
    {
        clockDisplay.timeTick();
    }
}
