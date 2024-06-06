package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Repository;


import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties.Curator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryDB extends JpaRepository<Curator, String> {
}
