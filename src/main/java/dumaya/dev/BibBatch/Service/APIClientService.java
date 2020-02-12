package dumaya.dev.BibBatch.Service;

import dumaya.dev.BibBatch.exceptions.APIException;
import dumaya.dev.BibBatch.exceptions.NotFoundException;
import dumaya.dev.BibBatch.modelAPI.Ouvrage;
import dumaya.dev.BibBatch.modelAPI.Pret;
import dumaya.dev.BibBatch.modelAPI.Reference;

import dumaya.dev.BibBatch.modelAPI.Usager;
import dumaya.dev.BibBatch.modelForm.PretEnCoursUsager;
import dumaya.dev.BibBatch.proxies.BibAppProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class APIClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(APIClientService.class);

    @Autowired
    private BibAppProxy bibAppProxy;

    /**
     * @return tous les prets en retard de cet usager
     */
    public List<PretEnCoursUsager> listePretARelancer(int id) {
        List<PretEnCoursUsager> pretEnCoursListe = new ArrayList<>();
        try {
            LOGGER.debug("Appel API prets à relancer");
            List<Pret> prets = bibAppProxy.listePretARelancer(id);
            for (Pret pret: prets) {
                Ouvrage ouvrage = recupererUnOuvrageService(pret.getIdOuvrage());
                Reference reference = recupererUneReferenceService(ouvrage.getIdReference());
                PretEnCoursUsager pretEnCoursUsager = new PretEnCoursUsager();
                pretEnCoursUsager.setId(pret.getId());
                pretEnCoursUsager.setIdOuvrage(pret.getIdOuvrage());
                pretEnCoursUsager.setIdUsager(pret.getIdUsager());
                pretEnCoursUsager.setAuteur(reference.getAuteur());
                pretEnCoursUsager.setTitre(reference.getTitre());
                pretEnCoursUsager.setDateFin(pret.getDateFin());
                pretEnCoursUsager.setDateRetour(pret.getDateRetour());
                pretEnCoursUsager.setTopProlongation(pret.getTopProlongation());

                pretEnCoursListe.add(pretEnCoursUsager);
            }
            return pretEnCoursListe;
        } catch (NotFoundException e) {
            return null;
        } catch (RuntimeException e) {
            throw new APIException("Get Liste des prets a relancer d'un usager" ,e.getMessage(),e.getStackTrace().toString());
        }
    }

    public List<Usager> listeUsager (){
        try {
            return bibAppProxy.listeDesUsagers();
        } catch (NotFoundException e) {
            return null;
        } catch (RuntimeException e) {
            throw new APIException("Get Liste des usagers" ,e.getMessage(),e.getStackTrace().toString());
        }
    }

    /**
     * @param id ouvrage
     * @return ouvrage
     */
    private Ouvrage recupererUnOuvrageService (int id) {
        try {
            LOGGER.debug("Appel API ouvrage");
            Ouvrage ouvrage = bibAppProxy.recupererUnOuvrage(id);
            return ouvrage;
        } catch (NotFoundException e) {
            return null;
        } catch (RuntimeException e) {
            throw new APIException("Get Ouvrage par id" ,e.getMessage(),e.getStackTrace().toString());
        }
    }

    /**
     * @param id reference
     * @return reference
     */
    private Reference recupererUneReferenceService (int id) {
        try {
            LOGGER.debug("Appel API reference");
            Reference reference = bibAppProxy.recupererUneReference(id);
            return reference;
        } catch (NotFoundException e) {
            Reference referenceNonTrouvée = new Reference();
            referenceNonTrouvée.setAuteur("Auteur non trouvé");
            referenceNonTrouvée.setTitre("Titre non trouvé");
            return referenceNonTrouvée;
        } catch (RuntimeException e) {
            throw new APIException("Get Reference par id" ,e.getMessage(),e.getStackTrace().toString());
        }
    }

}
