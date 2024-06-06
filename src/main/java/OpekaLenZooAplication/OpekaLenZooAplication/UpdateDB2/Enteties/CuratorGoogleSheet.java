package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties;

public class CuratorGoogleSheet {
    private String name;
    private String mail;
    private String path;

    public CuratorGoogleSheet(String name, String mail, String path) {
        this.name = name;
        this.mail = mail;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPath() {
        return path;
    }
}
