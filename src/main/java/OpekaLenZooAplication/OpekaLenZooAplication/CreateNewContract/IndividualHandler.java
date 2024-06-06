package OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;
import OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.Exeption.ExistFileException;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties.DataForContracts;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties.Individual;

import java.io.IOException;
import java.io.InputStream;

public class IndividualHandler extends HandlerDocx {

    @Override
    public void replaceSample(DataForContracts dataForContracts) throws IOException, ExistFileException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(Constants.exampleInFace);
        XWPFDocumentOpeka inDoc = new XWPFDocumentOpeka(in);
        Individual individual = (Individual) dataForContracts;

        String allSum = getWordRuble(individual.getSum());
        String partSum = getWordRuble(individual.getSumPart());
        String[] dateSplit = individual.getDateStart().split("\\.");
        int numberMonth = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]) % 100;

        inDoc.replace("NAME", individual.getName());
        inDoc.replace("NUMBERCONTRACT", dataForContracts.getContractNumber());
        inDoc.replace("DATE", dataForContracts.getMonth());
        inDoc.replace("START", individual.getDateStart());
        inDoc.replace("END", individual.getDateEnd());
        inDoc.replace("ANIMAL", individual.getAnimal());
        inDoc.replace("SUM", allSum);
        switch (individual.getPeriodForPay()) {
            case "Единовременная" -> {
                inDoc.replace("PARTSUM", "__________");
                inDoc.replace("WORDPARTSUM", "__________");
                inDoc.replace("NDSPARTSUM", "__________");
                inDoc.replace("WORDNDSPARTSUM", "__________");
                inDoc.replace("PARONE", "\uF06F", "Wingdings");
                inDoc.replace("PARTWO", "\uF0FE", "Wingdings");
                inDoc.replace("PARMONTH", "\uF06F", "Wingdings");
                inDoc.replace("PARKVART", "\uF06F", "Wingdings");
            }
            case "Ежемесячная" -> {
                inDoc.replace("PARTSUM", partSum);
                inDoc.replace("PARONE", "\uF0FE", "Wingdings");
                inDoc.replace("PARTWO", "\uF06F", "Wingdings");
                inDoc.replace("PARMONTH", "\uF0FE", "Wingdings");
                inDoc.replace("PARKVART", "\uF06F", "Wingdings");
            }
            case "Ежеквартальная" -> {
                inDoc.replace("PARTSUM", partSum);
                inDoc.replace("PARONE", "\uF0FE", "Wingdings");
                inDoc.replace("PARTWO", "\uF06F", "Wingdings");
                inDoc.replace("PARMONTH", "\uF06F", "Wingdings");
                inDoc.replace("PARKVART", "\uF0FE", "Wingdings");
            }
        }
        inDoc.replace("DATEBIRTHDAY", individual.getDateBirthday());
        inDoc.replace("LOCATIONBIRTH", individual.getLocationBirth());
        inDoc.replace("NUMBERPASSPORT", individual.getNumberPassport());
        inDoc.replace("LOCATIONGIVE", individual.getLocationGive());
        inDoc.replace("DATEGIVE", individual.getDateGive());
        inDoc.replace("CODE", individual.getCode());
        inDoc.replace("ADDRESSREGISTRATION", individual.getAddressRegistration());
        inDoc.replace("MAILADDRESS", individual.getMailAddress());
        inDoc.replace("EMAIL", individual.getMail());
        inDoc.replace("PHONENUMBER", individual.getNumberPhone());
        inDoc.replace("SIGNNAME", individual.getSignName());
        inDoc.replace("BILLET", individual.getBillet());
        inDoc.save(Constants.NEW_CONTRACTS_PATH + MONTH.get(numberMonth) + "_" + year
                ,dataForContracts.getContractNumber() + " " + individual.getName() + ".docx");
    }
}
