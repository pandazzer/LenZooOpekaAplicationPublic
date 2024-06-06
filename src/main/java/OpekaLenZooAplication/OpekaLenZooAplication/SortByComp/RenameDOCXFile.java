package OpekaLenZooAplication.OpekaLenZooAplication.SortByComp;

import java.io.File;
import java.util.Objects;

public class RenameDOCXFile {
    String pathCompanies = "Z:\\Опека\\Опекуны";
    public void rename(String path){
        File direct = new File(path);
        File companyDirect = new File(pathCompanies);
        CheckSameWord checkSameWord = new CheckSameWord();
        for (File file : Objects.requireNonNull(direct.listFiles())){
            if (!file.getName().split("\\.")[1].equals("docx")){
                continue;
            }
            String companyName = file.getName();

            int maxSameCompany = 0;
            File correctCompany = null;
            for (File company : Objects.requireNonNull(companyDirect.listFiles())){
                int maxNumber = checkSameWord.getCountMaxSameSymbols(companyName, company.getName());
                if (maxNumber > maxSameCompany){
                    maxSameCompany = maxNumber;
                    correctCompany = company;
                }
            }
            if (!file.renameTo(new File(file.getParent() + "/" + correctCompany.getName() + ".docx"))){
                if (!file.renameTo(new File(file.getParent() + "/" + correctCompany.getName() + "_2.docx"))){
                    if (!file.renameTo(new File(file.getParent() + "/" + correctCompany.getName() +"_3.docx"))){
                        System.out.println("Ошибка переименовывания");
                    }
                }
            }
            System.out.println(companyName + " -- " + correctCompany.getName());
        }
    }
}
