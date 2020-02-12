package dumaya.dev.BibBatch.proxies;

import dumaya.dev.BibBatch.modelAPI.Ouvrage;
import dumaya.dev.BibBatch.modelAPI.Pret;
import dumaya.dev.BibBatch.modelAPI.Reference;

import dumaya.dev.BibBatch.modelAPI.Usager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Accès à l'API (avec Feign)
 */

@FeignClient(name = "BibApp", url = "localhost:8001")
public interface BibAppProxy {

    @GetMapping(value = "/references/{id}")
    Reference recupererUneReference(@PathVariable("id") int id);

    @GetMapping(value = "/ouvrages/{id}")
    Ouvrage recupererUnOuvrage (@PathVariable("id") int id);

    @GetMapping(value = "/prets/arelancer/{id}")
    List<Pret> listePretARelancer (@PathVariable("id") int id);

    @GetMapping(value = "/usagers")
    List<Usager> listeDesUsagers();

}
