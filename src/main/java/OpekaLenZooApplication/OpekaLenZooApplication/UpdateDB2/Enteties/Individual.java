package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties;

public class Individual extends DataForContracts{
    private String dateBirthday;
    private String locationBirth;
    private String numberPassport;
    private String locationGive;
    private String dateGive;
    private String code;
    private String addressRegistration;
    private String mailAddress;
    private String signName;

    public Individual(String name, String animal, String periodForPay, String nameForCert, String numberPhone,
                      String mail, String dateStart, String dateEnd, int sumPart, int sum, String billet,
                      boolean fizOrUr, String dateBirthday, String locationBirth, String numberPassport,
                      String locationGive, String dateGive, String code, String addressRegistration,
                      String mailAddress, String signName) {
        super(name, animal, periodForPay, nameForCert, numberPhone, mail, dateStart, dateEnd, sumPart, sum, billet, fizOrUr);
        this.dateBirthday = dateBirthday;
        this.locationBirth = locationBirth;
        this.numberPassport = numberPassport;
        this.locationGive = locationGive;
        this.dateGive = dateGive;
        this.code = code;
        this.addressRegistration = addressRegistration;
        this.mailAddress = mailAddress;
        this.signName = signName;
    }

    public String getDateBirthday() {
        return dateBirthday;
    }

    public String getLocationBirth() {
        return locationBirth;
    }

    public String getNumberPassport() {
        return numberPassport;
    }

    public String getLocationGive() {
        return locationGive;
    }

    public String getDateGive() {
        return dateGive;
    }

    public String getCode() {
        return code;
    }

    public String getAddressRegistration() {
        return addressRegistration;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public String getSignName() {
        return signName;
    }
}
