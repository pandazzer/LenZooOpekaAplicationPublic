package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Repository;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.CuratorGoogleSheet;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.DataForContracts;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.Individual;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Enteties.LegalPerson;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Exeption.NotFoundDataForContract;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Exeption.UncorrectedVisitPerson;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.GoogleSheetsStart;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.GoogleWithoutToken;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryGoogleSheet {

    private List<List<Object>> valuesCurators;
    private List<List<Object>> valuesContracts;


    public RepositoryGoogleSheet() {
        updateDataGoogleSheet();
    }

    public void updateDataGoogleSheet() {
        GoogleSheetsStart sheetsStart = new GoogleWithoutToken();
        Sheets sheets = sheetsStart.getSheetsService();
        final String spreadsheetId = Constants.spreadsheetId;
        final String rangeCurators = "Опекуны!A2:AC";
        final String rangeContracts = "Договоры!A2:L";
        ValueRange responseCurators;
        ValueRange responseContracts;
        try {
            responseCurators = sheets.spreadsheets().values()
                    .get(spreadsheetId, rangeCurators)
                    .execute();
            responseContracts = sheets.spreadsheets().values()
                    .get(spreadsheetId, rangeContracts)
                    .execute();
        } catch (IOException e) {
            System.out.println("Не удалось загрузить табличку");
            throw new RuntimeException(e);
        }
        List<List<Object>> valuesCurators = responseCurators.getValues();
        List<List<Object>> valuesContracts = responseContracts.getValues();
        if (valuesCurators == null || valuesCurators.isEmpty() || valuesContracts == null || valuesContracts.isEmpty()) {
            System.out.println("No data found.");
        }
        this.valuesCurators = valuesCurators;
        this.valuesContracts = valuesContracts;
    }

    public List<CuratorGoogleSheet> getDataList() {
        List<CuratorGoogleSheet> listResult = new ArrayList<>();
        for (List<Object> line : valuesCurators) {
            if (line.size() > 8 && !line.get(4).equals("") && !line.get(7).equals("")) {
                listResult.add(new CuratorGoogleSheet(String.valueOf(line.get(0))
                        , String.valueOf(line.get(4))
                        , String.valueOf(line.get(7))));
            }
        }
        return listResult;
    }

    public DataForContracts getDataForContract(String numberOfContract) throws NotFoundDataForContract, UncorrectedVisitPerson {
        for (List<Object> contract : valuesContracts) {
            if (contract.get(0).equals(numberOfContract) && contract.size() > 11) {
                String name = contract.get(1).toString();
                String animal = contract.get(2).toString();
                String dateStart = contract.get(4).toString();
                String dateEnd = contract.get(5).toString();
                String periodForPay = contract.get(6).toString();
                int sumPart = Integer.parseInt(contract.get(7).toString().replace(" ", ""));
                int sum = Integer.parseInt(contract.get(8).toString().replace(" ", ""));
                String billet = contract.get(11).toString();

                for (List<Object> curator : valuesCurators) {
                    if (curator.get(0).equals(name)) {
                        String nameForCert = curator.get(1).toString();
                        String numberPhone = curator.get(3).toString();
                        String mail = curator.get(4).toString();
                        String mailAddress = curator.get(6).toString();
                        if (curator.get(8).equals("Физ. лицо") && curator.size() > 28) {
                            boolean isIndividual = true;
                            String dateBirthday = curator.get(22).toString();
                            String locationBirth = curator.get(23).toString();
                            String numberPassport = curator.get(24).toString();
                            String locationGive = curator.get(25).toString();
                            String dateGive = curator.get(26).toString();
                            String code = curator.get(27).toString();
                            String addressRegistration = curator.get(28).toString();
                            String[] var = name.split(" ");
                            String signName = var[0] + " "
                                    + var[1].charAt(0)
                                    + "."
                                    + var[2].charAt(0)
                                    + ".";
                            return new Individual(name, animal, periodForPay, nameForCert, numberPhone, mail, dateStart,
                                    dateEnd, sumPart, sum, billet, isIndividual, dateBirthday, locationBirth,
                                    numberPassport, locationGive, dateGive, code, addressRegistration, mailAddress, signName);
                        } else if (curator.size() > 21) {
                            boolean isIndividual = false;
                            String visitPerson = curator.get(10).toString();
                            String[] nameArray = visitPerson.split(" ");
                            String signName;
                            switch (nameArray.length) {
                                case 2 -> signName = nameArray[nameArray.length - 2] + " "
                                        + nameArray[nameArray.length - 1].charAt(0)
                                        + ".";
                                case 3 -> signName = nameArray[nameArray.length - 3] + " "
                                        + nameArray[nameArray.length - 2].charAt(0)
                                        + "."
                                        + nameArray[nameArray.length - 1].charAt(0)
                                        + ".";
                                default -> throw new UncorrectedVisitPerson();
                            }
                            String post = curator.get(11).toString();
                            String reason = curator.get(12).toString();
                            String urAddress = curator.get(13).toString();
                            String INN = curator.get(14).toString();
                            String KPP = curator.get(15).toString();
                            String OKPO = curator.get(16).toString();
                            String OGRN = curator.get(17).toString();
                            String bank = curator.get(18).toString();
                            String rs = curator.get(19).toString();
                            String ks = curator.get(20).toString();
                            String bik = curator.get(21).toString();
                            return new LegalPerson(name, animal, periodForPay, nameForCert, numberPhone, mail,
                                    dateStart, dateEnd, sumPart, sum, billet, isIndividual, post, visitPerson, reason,
                                    urAddress, INN, KPP, OKPO, OGRN, bank, rs, ks, bik, signName);
                        }
                    }
                }
            }
        }
        throw new NotFoundDataForContract();
    }
}
