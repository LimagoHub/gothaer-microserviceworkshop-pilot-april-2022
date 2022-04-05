package de.gothaer.smartbank24kreditantragstore.domain.aggregates;

public enum KreditantragStatus {

    STORNO(Constants.FIXED_KREDITANTRAG_ZUSTAND),
    ACCEPTED(Constants.FIXED_KREDITANTRAG_ZUSTAND),
    DENIED(Constants.FIXED_KREDITANTRAG_ZUSTAND),
    SCORING_POSITIVE(Constants.SCORING_POSITIVE_KREDITANTRAG_ZUSTAND),
    CITY_SCORING_POSITIVE(Constants.CITY_SCORING_POSITIVE_KREDITANTRAG_ZUSTAND),
    IN_PROGRESS(Constants.IN_PROGRESS_KREDITANTRAG_ZUSTAND);

    KreditantragStatus(String classnameArg){classname= classnameArg;}
    private String classname;
    public String getClassname() {return classname;}

    private static class Constants {
        public static final String FIXED_KREDITANTRAG_ZUSTAND = "FixedKreditantragZustand";
        public static final String SCORING_POSITIVE_KREDITANTRAG_ZUSTAND = "ScoringPositiveKreditantragZustand";
        public static final String CITY_SCORING_POSITIVE_KREDITANTRAG_ZUSTAND = "CityScoringPositiveKreditantragZustand";
        public static final String IN_PROGRESS_KREDITANTRAG_ZUSTAND = "InProgressKreditantragZustand";
    }
}
