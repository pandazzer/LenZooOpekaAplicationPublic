package OpekaLenZooAplication.OpekaLenZooAplication.SortByComp;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;
import OpekaLenZooAplication.OpekaLenZooAplication.SortByComp.Exeptions.RenameExeption;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties.ReturnCompany;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.Callable;

public class RenameFileThread implements Callable<ReturnCompany> {
    private final String chBox;
    private final File companyDirect = new File(Constants.curatorsDirectoryPath);
    private final CheckSameWord checkSameWord = new CheckSameWord();
    private final File file;

    public RenameFileThread(File file, String chBox) {
        this.file = file;
        this.chBox = chBox;
    }

    @Override
    public ReturnCompany call() throws RenameExeption {
        String companyName = getCompanyName(file);
        String correctCompany = getCorrectCompanyFile(companyName);
        renameFile(file, correctCompany);
        return new ReturnCompany(companyName, correctCompany);
    }


    private String getCompanyName(File file) {
        PNGReader reader = new PNGReader(file.getPath());
        switch (chBox) {
            case "Акты сверок" -> {
                return reader.getNameCompanyActSverki();
            }
            case "Акты об оказании услуг" -> {
                return reader.getNameCompanyActs();
            }
            case "Счета" -> {
                return reader.getNameCompanySchet();
            }
            case "Счета-факутры" -> {
                return reader.getNameCompanySchetFacture();
            }
            default -> throw new RuntimeException();
        }
    }

    private void renameFile(File file, String correctCompany) throws RenameExeption {
        if (!file.renameTo(new File(String.format("%s/%s.png"
                , file.getParent()
                , correctCompany)))) {
            renameDuplicateFile(file, correctCompany);
        }
    }

    private void renameDuplicateFile(File file, String correctCompany) throws RenameExeption {
        for (int i = 2; i < 70; i++) {
            if (i == 69) {
                throw new RenameExeption();
            }
            if (file.renameTo(new File(String.format("%s/%s_(%d).png"
                    , file.getParent()
                    , correctCompany
                    , i)))) {
                break;
            }
        }
    }

    private String getCorrectCompanyFile(String companyName) {
        int maxSameCompany = 0;
        File correctCompany = null;
        for (File company : Objects.requireNonNull(companyDirect.listFiles())) {
            int maxNumber = checkSameWord.getCountMaxSameSymbols(companyName, company.getName());
            if (maxNumber > maxSameCompany) {
                maxSameCompany = maxNumber;
                correctCompany = company;
            }
        }
        return correctCompany.getName();
    }
}
