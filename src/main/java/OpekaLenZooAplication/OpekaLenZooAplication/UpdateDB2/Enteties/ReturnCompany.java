package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties;

public class ReturnCompany {
    private final String nameCompany;
    private final String correctNameCompany;

    public ReturnCompany(String nameCompany, String correctNameCompany) {
        this.nameCompany = nameCompany;
        this.correctNameCompany = correctNameCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getCorrectNameCompany() {
        return correctNameCompany;
    }
}
