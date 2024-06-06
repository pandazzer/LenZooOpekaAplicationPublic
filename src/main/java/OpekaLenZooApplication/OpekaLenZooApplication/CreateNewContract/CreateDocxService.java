package OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract;


import OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Exeption.ExistFileException;
import OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Exeption.IncorrectException;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.DataForContracts;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Exeption.NotFoundDataForContract;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Exeption.UncorrectedVisitPerson;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Repository.RepositoryGoogleSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CreateDocxService {
    @Autowired
    private RepositoryGoogleSheet repositoryGoogleSheet;

    public void createContract(String contractNumber) throws NotFoundDataForContract, IncorrectException, ExistFileException, UncorrectedVisitPerson {
        repositoryGoogleSheet.updateDataGoogleSheet();
        DataForContracts dataForContracts = repositoryGoogleSheet.getDataForContract(contractNumber);
        dataForContracts.setContractNumber(contractNumber);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("«dd» MMMM yyyy г.");
        dataForContracts.setMonth(df.format(date));
        try {
            if (dataForContracts.isIndividual()) {
                new IndividualHandler().replaceSample(dataForContracts);
            } else {
                new LegalPersonHandler().replaceSample(dataForContracts);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
