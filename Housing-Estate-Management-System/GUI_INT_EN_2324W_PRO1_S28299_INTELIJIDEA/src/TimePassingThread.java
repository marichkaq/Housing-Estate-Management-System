import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TimePassingThread  extends Thread{
    private Date currentDate;

    public TimePassingThread(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);
                synchronized (currentDate) {

                    currentDate.setTime(currentDate.getTime() + TimeUnit.DAYS.toMillis(1));
                }
                //System.out.println("Current date: " + currentDate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
