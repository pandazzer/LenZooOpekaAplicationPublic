package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2;


import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.Curator;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.CuratorGoogleSheet;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Repository.RepositoryDB;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Repository.RepositoryGoogleSheet;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceDB {
    public RepositoryGoogleSheet repositoryGoogleSheet;
    public RepositoryDB repositoryDB;

    public ServiceDB(RepositoryGoogleSheet repositoryGoogleSheet, RepositoryDB repositoryDB) {
        this.repositoryDB = repositoryDB;
        this.repositoryGoogleSheet = repositoryGoogleSheet;
    }

    public void start(){
        repositoryGoogleSheet.updateDataGoogleSheet();
        List<CuratorGoogleSheet> list = repositoryGoogleSheet.getDataList();
        for (CuratorGoogleSheet curatorGoogleSheet : list){
            repositoryDB.save(new Curator(curatorGoogleSheet.getName()
                    , curatorGoogleSheet.getMail()
                    , curatorGoogleSheet.getPath()));
        }
    }
}
