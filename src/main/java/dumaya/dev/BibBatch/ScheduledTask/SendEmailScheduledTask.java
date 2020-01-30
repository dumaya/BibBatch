package dumaya.dev.BibBatch.ScheduledTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SendEmailScheduledTask {
    @Scheduled(cron = "0 */2 * ? * *")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);
    }
}
