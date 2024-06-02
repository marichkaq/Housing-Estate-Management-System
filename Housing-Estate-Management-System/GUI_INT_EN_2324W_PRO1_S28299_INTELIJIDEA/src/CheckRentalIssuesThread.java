import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class CheckRentalIssuesThread extends Thread{
    private HousingEstate estate;
    private  Date currentDate;

    public CheckRentalIssuesThread(HousingEstate estate, Date currentDate) {
        this.estate = estate;
        this.currentDate = currentDate;
    }


    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.SECONDS.sleep(10);
                synchronized (currentDate){
                    estate.checkRentalIssues(currentDate);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
