package dumaya.dev.BibBatch.Service;

import dumaya.dev.BibBatch.modelAPI.Usager;
import dumaya.dev.BibBatch.modelForm.PretEnCoursUsager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envoi un mail avec la liste des prets en retard à l'usager concerné
     * @param usager
     * @param listePret
     */
    public void envoiEmailRelance(Usager usager, List<PretEnCoursUsager> listePret) {
        LOGGER.debug("Envoi mail");
        StringBuilder ecriturePret = new StringBuilder();
        SimpleDateFormat formater = new SimpleDateFormat("'le' dd/MM/yyyy 'à' hh:mm:ss");

        String newLine = System.getProperty("line.separator");

        for (PretEnCoursUsager pret : listePret) {
            ecriturePret.append("Titre : " + pret.getTitre() + " Date de fin de pret : " + formater.format(pret.getDateFin()) + newLine);
        }
        String subject = "Date de retour de prêt dépassée !";
        String body = "La date de fin de prêt du(des) livre(s) que vous avez emprunté est dépassée." + newLine
                + "Veuillez effectuer le(s) retour de prêt dans les plus brefs délais."+ newLine+ ecriturePret
                + newLine + newLine + "Votre bibliothèque municipale";

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(usager.getEmail());
        mailMessage.setFrom("noreply@bibapp.fr");
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);
    }
}
