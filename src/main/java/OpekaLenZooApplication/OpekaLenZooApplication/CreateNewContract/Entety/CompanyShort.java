package OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Entety;

public class CompanyShort {
    private String name;
    private String post;
    private String fullCompanyName;
    private String shortCompanyName;
    private String kpp;
    private String ogrn;
    private String okpo;
    private String urAdress;
    private boolean isLegal;
    private String status;

    public CompanyShort(String name, String post, String fullCompanyName, String shortCompanyName, String kpp,
                        String ogrn, String okpo, String urAdress, boolean isLegal, String status) {
        this.name = name;
        this.post = post;
        this.fullCompanyName = fullCompanyName;
        this.shortCompanyName = shortCompanyName;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.okpo = okpo;
        this.urAdress = urAdress;
        this.isLegal = isLegal;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getFullCompanyName() {
        return fullCompanyName;
    }

    public String getShortCompanyName() {
        return shortCompanyName;
    }

    public String getKpp() {
        return kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getOkpo() {
        return okpo;
    }

    public String getUrAdress() {
        return urAdress;
    }

    public boolean isLegal() {
        return isLegal;
    }
    public String getStatus(){return status;}
}
