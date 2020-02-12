package dumaya.dev.BibBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("dumaya.dev.BibBatch")
@SpringBootApplication
public class BibBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibBatchApplication.class, args);
	}

}
