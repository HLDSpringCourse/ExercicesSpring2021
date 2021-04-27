package util;

public class Constants {

    private Constants() {
        super();
    }

    public static final String API_GOV_BASE_URL = "https://geo.api.gouv.fr";
    public static final String API_GOV_REGIONS_URL = "/regions";

    public static final String MESSAGE_NOT_FOUND = "Item non trouvée";
    public static final String MESSAGE_BAD_ITEM = "Item non conforme";
    public static final String MESSAGE_CODE_REGION_INVALID = "Le code region indiqué n'existe pas";
}
