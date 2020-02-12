package dumaya.dev.BibBatch;

import dumaya.dev.BibBatch.Service.EmailService;
import dumaya.dev.BibBatch.modelAPI.Pret;
import dumaya.dev.BibBatch.modelAPI.Usager;
import dumaya.dev.BibBatch.modelForm.PretEnCoursUsager;
import dumaya.dev.BibBatch.proxies.BibAppProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BibBatchApplicationTests {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private BibAppProxy bibAppProxy;

	private EmailService emailService;
	@Before
	public void before() {
		emailService = new EmailService(mailSender);
	}
	@Test
	public void testEnvoiMail() {
		Usager usager = new Usager();
		usager.setEmail("jojo@momo.com");
		List<PretEnCoursUsager> listePret = new ArrayList<>();
		PretEnCoursUsager pret = new PretEnCoursUsager();
		pret.setAuteur("auteur");
		pret.setDateFin(new Date());
		pret.setTitre("kilou");
		listePret.add(pret);
		listePret.add(pret);
		emailService.envoiEmailRelance(usager, listePret);
	}

	@Test
	public void listePretARelancer() throws Exception {
		List<Pret> prets = bibAppProxy.listePretARelancer(300);
		assertNotNull(prets);
		assertTrue(prets.size() > 0);
	}
}
