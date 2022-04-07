package de.gothaer.smartbank24kreditantragstore.domain.services.internal;


import de.gothaer.smartbank24kreditantragstore.adapter.mapper.KreditantragMapper;
import de.gothaer.smartbank24kreditantragstore.application.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragServiceException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KreditantragServiceImpl implements KreditantragService {

    public static final String FEHLER_BEIM_VERARBEITEN_DES_SCORINGS = "Fehler beim Verarbeiten des Scorings. Antrag mit der IS '%s'.";
    private final KreditantragRepository repo;
    private final KreditantragMapper mapper;

    @Override
    public void speichereKreditantrag(Kreditantrag antrag) throws KreditantragServiceException {
        try {
            throwExceptionIfApplicationenAlreadyExits(antrag);
            repo.save(mapper.convert(antrag));

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format("Fehler beim Speiochern des Antrags mit der IS '%s'.",antrag.getCreditApplicationId()) , e);
        }
    }

    @Override
    public Kreditantrag.StatusWechsel verarbeitePositivesScoring(String id) throws KreditantragServiceException {
        try {
            var kreditantrag = findeKreditantragMitId(id);
            System.out.println("############# s" + kreditantrag);
            var result = kreditantrag.behandlePositivesScoring();
            save(kreditantrag);

            return result;
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(FEHLER_BEIM_VERARBEITEN_DES_SCORINGS,id) , e);
        }
    }


    @Override
    public Kreditantrag.StatusWechsel verarbeitePositivesCityScoring(String id) throws KreditantragServiceException {
        try {
            var kreditantrag = findeKreditantragMitId(id);
            System.out.println("############# cs" + kreditantrag);
            var result = kreditantrag.behandlePositivesCityScoring();
            save(kreditantrag);
            return result;
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(FEHLER_BEIM_VERARBEITEN_DES_SCORINGS,id) , e);
        }
    }


    @Override
    public Kreditantrag.StatusWechsel verarbeiteNegativesScoring(String id) throws KreditantragServiceException {
        try {
            var kreditantrag = findeKreditantragMitId(id);
            var result = kreditantrag.behandleNegativesScoring();
            save(kreditantrag);
            return result;
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(FEHLER_BEIM_VERARBEITEN_DES_SCORINGS,id) , e);
        }
    }

    @Override
    public Kreditantrag.StatusWechsel verarbeiteNegativesCityScoring(String id) throws KreditantragServiceException {
        try {
            var kreditantrag = findeKreditantragMitId(id);
            var result = kreditantrag.behandleNegativesCityScoring();
            save(kreditantrag);
            return result;
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(FEHLER_BEIM_VERARBEITEN_DES_SCORINGS,id) , e);
        }
    }



    private void throwExceptionIfApplicationenAlreadyExits(Kreditantrag kreditantrag) throws KreditantragServiceException {
        if(repo.existsById(kreditantrag.getCreditApplicationId()))
            throw new KreditantragServiceException(String.format("Ein Antrag mit der ID '%s' existiert bereits.", kreditantrag.getCreditApplicationId()));
    }
    @Override
    public Kreditantrag findeKreditantragMitId(String id) throws KreditantragServiceException {
        return repo.findById(id).map(mapper::convert).orElseThrow(()->new KreditantragServiceException("Antrag nicht gefunden"));
    }

    private void save(Kreditantrag kreditantrag) {
        repo.save(mapper.convert(kreditantrag));
    }
}
