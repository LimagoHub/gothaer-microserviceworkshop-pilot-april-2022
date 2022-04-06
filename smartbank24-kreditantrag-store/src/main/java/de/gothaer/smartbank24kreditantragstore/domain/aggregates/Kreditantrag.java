package de.gothaer.smartbank24kreditantragstore.domain.aggregates;

import lombok.*;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.time.LocalDateTime;


@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder


public class Kreditantrag implements Serializable {


    private final StateFactory factory=new StateFactory();

    public enum StatusWechsel{NO_CHANGE, STORNO, ACCEPTED, DENIED}

    private String creditApplicationId;

    private String firstName;

    private String lastName;

    private String city;
    private LocalDateTime applicationDate;
    private double monthlyIncome;
    private double monthlyExpenditure;
    private double creditSum;
    private long version;

    @Builder.Default
    private KreditantragStatus kreditantragState = KreditantragStatus.IN_PROGRESS;

    public StatusWechsel behandlePositivesScoring() {
        return factory.create(kreditantragState).behandlePositivesScoring();
    }
    public StatusWechsel behandleNegativesScoring() {
        return factory.create(kreditantragState).behandleNegativesScoring();
    }

    public StatusWechsel behandlePositivesCityScoring() {
        return factory.create(kreditantragState).behandlePositivesCityScoring();
    }
    public StatusWechsel behandleNegativesCityScoring() {
        return factory.create(kreditantragState).behandleNegativesCityScoring();
    }

/* ------------------------

Wilde Hilfsklassen zur Vermeidung von IF-Orgien
--------------------------- */


    interface KreditantragZustandHandler {

        StatusWechsel behandlePositivesScoring();
        StatusWechsel behandleNegativesScoring();
        StatusWechsel behandlePositivesCityScoring();
        StatusWechsel behandleNegativesCityScoring();
    }

    protected abstract static class AbstractKreditantragZustand implements KreditantragZustandHandler {


        @Override
        public StatusWechsel behandlePositivesScoring() {
            return StatusWechsel.NO_CHANGE;
        }

        @Override
        public StatusWechsel behandleNegativesScoring() {
            return StatusWechsel.NO_CHANGE;
        }

        @Override
        public StatusWechsel behandlePositivesCityScoring() {
            return StatusWechsel.NO_CHANGE;
        }

        @Override
        public StatusWechsel behandleNegativesCityScoring() {
            return StatusWechsel.NO_CHANGE;
        }
    }

    class InProgressKreditantragZustand extends AbstractKreditantragZustand {
        @Override
        public StatusWechsel behandlePositivesScoring() {
            kreditantragState = KreditantragStatus.SCORING_POSITIVE;
            return StatusWechsel.NO_CHANGE;
        }

        @Override
        public StatusWechsel behandleNegativesScoring() {
            return changeToDenied();
        }

        @Override
        public StatusWechsel behandlePositivesCityScoring() {
            kreditantragState = KreditantragStatus.CITY_SCORING_POSITIVE;
            return StatusWechsel.NO_CHANGE;
        }

        @Override
        public StatusWechsel behandleNegativesCityScoring() {
            return changeToDenied();
        }

        private StatusWechsel changeToDenied() {
            kreditantragState = KreditantragStatus.DENIED;
            return StatusWechsel.DENIED;
        }

    }
    class ScoringPositiveKreditantragZustand extends AbstractKreditantragZustand {

        @Override
        public StatusWechsel behandlePositivesCityScoring() {
            kreditantragState = KreditantragStatus.ACCEPTED;
            return StatusWechsel.ACCEPTED;
        }

        @Override
        public StatusWechsel behandleNegativesCityScoring() {
            kreditantragState = KreditantragStatus.DENIED;
            return StatusWechsel.DENIED;
        }
    }
    class CityScoringPositiveKreditantragZustand extends AbstractKreditantragZustand {

        @Override
        public StatusWechsel behandlePositivesCityScoring() {
            kreditantragState = KreditantragStatus.ACCEPTED;
            return StatusWechsel.ACCEPTED;
        }

        @Override
        public StatusWechsel behandleNegativesCityScoring() {
            kreditantragState = KreditantragStatus.DENIED;
            return StatusWechsel.DENIED;
        }
    }
    static class FixedKreditantragZustand extends AbstractKreditantragZustand {

        // Ok
    }
    class StateFactory implements Serializable{

        private static final String PREFIX = "de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag$";
        public KreditantragZustandHandler create(KreditantragStatus kreditantragState) {
            try {

                Class<?> clazz = Class.forName(PREFIX + kreditantragState.getClassname());
                Constructor<?> constructor = clazz.getDeclaredConstructor(Kreditantrag.class);
                return (KreditantragZustandHandler) constructor.newInstance(Kreditantrag.this);

            } catch (Exception e) {
                e.printStackTrace();
                return new FixedKreditantragZustand();
            }

        }
    }
}
