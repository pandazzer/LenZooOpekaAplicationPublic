package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties;

public class LegalPerson extends DataForContracts{
    private String post;
    private String visitPerson;
    private String reason;
    private String urAddress;
    private String INN;
    private String KPP;
    private String OKPO;
    private String OGRN;
    private String bank;
    private String rs;
    private String ks;
    private String bik;
    private String signName;

    public LegalPerson(String name, String animal, String periodForPay, String nameForCert, String numberPhone,
                       String mail, String dateStart, String dateEnd, int sumPart, int sum, String billet,
                       boolean fizOrUr,String post, String visitPerson, String reason, String urAddress, String INN, String KPP,
                       String OKPO, String OGRN, String bank, String rs, String ks, String bik, String signName) {
        super(name, animal, periodForPay, nameForCert, numberPhone, mail, dateStart, dateEnd, sumPart, sum, billet, fizOrUr);
        this.post = post;
        this.visitPerson = visitPerson;
        this.reason = reason;
        this.urAddress = urAddress;
        this.INN = INN;
        this.KPP = KPP;
        this.OKPO = OKPO;
        this.OGRN = OGRN;
        this.bank = bank;
        this.rs = rs;
        this.ks = ks;
        this.bik = bik;
        this.signName = signName;
    }

    public String getVisitPerson() {
        return visitPerson;
    }

    public String getReason() {
        return reason;
    }

    public String getUrAddress() {
        return urAddress;
    }

    public String getINN() {
        return INN;
    }

    public String getKPP() {
        return KPP;
    }

    public String getOKPO() {
        return OKPO;
    }

    public String getOGRN() {
        return OGRN;
    }

    public String getBank() {
        return bank;
    }

    public String getRs() {
        return rs;
    }

    public String getKs() {
        return ks;
    }

    public String getBik() {
        return bik;
    }

    public String getPost() {
        return post;
    }

    public String getSignName() {
        return signName;
    }
}
