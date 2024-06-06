package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties;

import jakarta.persistence.*;

@Entity
@Table(name = "curators")
public class Curator {
    @Id
    @Column
    private String path;
    @Column
    private String name;
    @Column
    private String mail;



    public Curator() {
    }

    public Curator(String name, String mail, String path) {
        this.name = name;
        this.mail = mail;
        this.path = path;
    }
}
