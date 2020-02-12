package dumaya.dev.BibBatch.ScheduledTask;

import dumaya.dev.BibBatch.Service.APIClientService;
import dumaya.dev.BibBatch.Service.EmailService;
import dumaya.dev.BibBatch.modelAPI.Usager;
import dumaya.dev.BibBatch.modelForm.PretEnCoursUsager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanifBatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanifBatch.class);
    @Autowired
    private EmailService emailService;
    private final APIClientService clientService;

    public PlanifBatch(APIClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Planification du batch
     */
    //toutes les 2 minutes
    @Scheduled(cron = "0 */2 * ? * *")
    //une fois par jour à midi
    //@Scheduled(cron = "0 0 12 * * ?")
    public void planifBatchCron() {
        LOGGER.debug("Lancement du batch");
        Date date = new Date();
        System.out.println(
                "Passage du batch d'envoi des mails de relance - " + date.toString());
        List<Usager> listeUsager = clientService.listeUsager();
        for (Usager usager : listeUsager)  {
            List<PretEnCoursUsager> listePretARelancer=  clientService.listePretARelancer(usager.getId());
            if (!listePretARelancer.isEmpty()) {
                emailService.envoiEmailRelance(usager, listePretARelancer);
            }
        }

    }
}
