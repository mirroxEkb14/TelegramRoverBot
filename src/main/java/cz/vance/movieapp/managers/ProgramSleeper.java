package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.Random;
//</editor-fold>

/**
 * Contains a set of public static methods that pause the execution during different bot feature executions.
 */
public final class ProgramSleeper {

    public static void pauseSmartSearchBeforeSendingMood() { pauseExecution(900, 1300); }
    public static void pauseSmartSearchBeforeSendingMovie() { pauseExecution(1100, 1400); }
    public static void pauseSmartSearchBeforeReboot() { pauseExecution(800, 1000); }
    public static void pauseNoIdeaBeforeSendingFirstMovie() { pauseExecution(1000, 1300); }
    public static void pauseWeRecommendBeforeSendingFirstMovie() { pauseExecution(1200, 1500); }

    /**
     * Pauses the execution for a random duration between the specified minimum and maximum milliseconds.
     */
    private static void pauseExecution(int minMillis, int maxMillis) {
        try {
            final int pauseTime = minMillis + new Random().nextInt(maxMillis - minMillis);
            Thread.sleep(pauseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
