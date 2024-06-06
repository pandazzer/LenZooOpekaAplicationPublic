package OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Entety.CompanyShort;
import OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Exeption.ExistFileException;
import OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Exeption.IncorrectException;
import OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Repository.RepositoryDaData;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.DataForContracts;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.LegalPerson;
import ru.morpher.ws3.AccessDeniedException;
import ru.morpher.ws3.ArgumentEmptyException;
import ru.morpher.ws3.Client;
import ru.morpher.ws3.ClientBuilder;
import ru.morpher.ws3.russian.ArgumentNotRussianException;
import ru.morpher.ws3.russian.InvalidFlagsException;
import ru.morpher.ws3.russian.NumeralsDeclensionNotSupportedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LegalPersonHandler extends HandlerDocx {
    private final List<String> unCorrectList = new ArrayList<>();
    private final RepositoryDaData repositoryDaData = new RepositoryDaData();


    @Override
    public void replaceSample(DataForContracts dataForContracts) throws IOException, IncorrectException, ExistFileException {
        Client clientForTransWord = new ClientBuilder().build();
        InputStream in = getClass().getClassLoader().getResourceAsStream(Constants.exampleUrFace);
        XWPFDocumentOpeka urDoc = new XWPFDocumentOpeka(in);
        LegalPerson legalPerson = (LegalPerson) dataForContracts;
        CompanyShort companyShort = repositoryDaData.getCorrectCompany(legalPerson.getINN());
        fillUncorrectList(legalPerson, companyShort);

        String allSum = getWordRuble(legalPerson.getSum());
        String allSumNDS = getWordRuble(legalPerson.getSum() / 6);
        String partSum = getWordRuble(legalPerson.getSumPart());
        String partSumNDS = getWordRuble(legalPerson.getSumPart() / 6);
        String[] dateSplit = legalPerson.getDateStart().split("\\.");
        int numberMonth = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]) % 100;

        urDoc.replace("FULLNAME", companyShort.getFullCompanyName());
        urDoc.replace("SHORTNAME", companyShort.getShortCompanyName());
        urDoc.replace("NUMBERCONTRACT", dataForContracts.getContractNumber());
        urDoc.replace("DATE", dataForContracts.getMonth());
        try {
            urDoc.replace("ANIMAL", clientForTransWord.russian().declension(legalPerson.getAnimal()).genitive);
            if (companyShort.isLegal()) {
                urDoc.replace("POSTGEN", "в лице " + clientForTransWord.russian().declension(legalPerson.getPost()).genitive);
                urDoc.replace("VISITPERSON", clientForTransWord.russian().declension(legalPerson.getVisitPerson()).genitive);
                urDoc.replace("POST", legalPerson.getPost().substring(0, 1).toUpperCase() + legalPerson.getPost().substring(1));
                urDoc.replace("KPP", "КПП " + companyShort.getKpp());
                urDoc.replace("URADRESS", "Юридический адрес: " + companyShort.getUrAdress());
                urDoc.replace("OGRN", "ОГРН: " + companyShort.getOgrn());
            } else {
                urDoc.replace("POST", legalPerson.getName());
                urDoc.replace("POSTGEN", "в своём лице");
                urDoc.replace("VISITPERSON", "");
                urDoc.replace("KPP", "");
                urDoc.replace("URADRESS", "Почтовый адрес: " + legalPerson.getUrAddress());
                urDoc.replace("OGRN", "ОГРНИП: " + companyShort.getOgrn());
            }
        } catch (AccessDeniedException | ArgumentEmptyException | ArgumentNotRussianException | InvalidFlagsException |
                 NumeralsDeclensionNotSupportedException e) {
            throw new RuntimeException(e);
        }
        urDoc.replace("REASON", legalPerson.getReason());
        urDoc.replace("START", legalPerson.getDateStart());
        urDoc.replace("END", legalPerson.getDateEnd());
        urDoc.replace("ALLSUM", allSum);
        urDoc.replace("ALLSUMNDS", allSumNDS);
        switch (legalPerson.getPeriodForPay()) {
            case "Единовременная" -> {
                urDoc.replace("PARTSUM", "__________ (__________) рублей");
                urDoc.replace("NDSPARTSUM", "__________ (__________) рублей");
                urDoc.replace("PARONE", "\uF06F", "Wingdings");
                urDoc.replace("PARTWO", "\uF0FE", "Wingdings");
                urDoc.replace("PARMONTH", "\uF06F", "Wingdings");
                urDoc.replace("PARKVART", "\uF06F", "Wingdings");
            }
            case "Ежемесячная" -> {
                urDoc.replace("PARTSUM", partSum);
                urDoc.replace("NDSPARTSUM", partSumNDS);
                urDoc.replace("PARONE", "\uF0FE", "Wingdings");
                urDoc.replace("PARTWO", "\uF06F", "Wingdings");
                urDoc.replace("PARMONTH", "\uF0FE", "Wingdings");
                urDoc.replace("PARKVART", "\uF06F", "Wingdings");
            }
            case "Ежеквартальная" -> {
                urDoc.replace("PARTSUM", partSum);
                urDoc.replace("NDSPARTSUM", partSumNDS);
                urDoc.replace("PARONE", "\uF0FE", "Wingdings");
                urDoc.replace("PARTWO", "\uF06F", "Wingdings");
                urDoc.replace("PARMONTH", "\uF06F", "Wingdings");
                urDoc.replace("PARKVART", "\uF0FE", "Wingdings");
            }
            default -> {
                urDoc.replace("PARTSUM", partSum);
                urDoc.replace("NDSPARTSUM", partSumNDS);
                urDoc.replace("PARONE", "\uF0FE", "Wingdings");
                urDoc.replace("PARTWO", "\uF06F", "Wingdings");
                urDoc.replace("PARMONTH", "\uF06F", "Wingdings");
                urDoc.replace("PARKVART", "\uF06F", "Wingdings");
            }
        }
        urDoc.replace("INN", legalPerson.getINN());
        urDoc.replace("OKPO", companyShort.getOkpo());
        urDoc.replace("RS", legalPerson.getRs());
        urDoc.replace("KS", legalPerson.getKs());
        urDoc.replace("BANK", legalPerson.getBank());
        urDoc.replace("BIK", legalPerson.getBik());
        urDoc.replace("EMAIL", legalPerson.getMail());
        urDoc.replace("PHONENUMBER", legalPerson.getNumberPhone());
        urDoc.replace("SIGNNAME", legalPerson.getSignName());
        urDoc.replace("BILLET", legalPerson.getBillet());
        urDoc.save(Constants.NEW_CONTRACTS_PATH + MONTH.get(numberMonth) + "_" + year
                , dataForContracts.getContractNumber() + " " + legalPerson.getName() + ".docx");

        if (!unCorrectList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : unCorrectList) {
                stringBuilder.append(str);
            }
            throw new IncorrectException(stringBuilder.toString());
        }
    }

    private void fillUncorrectList(LegalPerson legalPerson, CompanyShort companyShort) {
        unCorrectList.add("Статус - " + companyShort.getStatus() + "\n");
        check(companyShort.getShortCompanyName(), legalPerson.getName());
        check(companyShort.getOkpo(), legalPerson.getOKPO());
        check(companyShort.getOgrn(), legalPerson.getOGRN());
        check(companyShort.getUrAdress(), legalPerson.getUrAddress());
        if (companyShort.isLegal()) {
            check(companyShort.getPost(), legalPerson.getPost());
            check(companyShort.getName(), legalPerson.getVisitPerson());
            check(companyShort.getKpp(), legalPerson.getKPP());
        }
    }

    private void check(String one, String two) {
        if (!one.equalsIgnoreCase(two)) {
            unCorrectList.add(one + "---" + two + "\n");
        }
    }
}
