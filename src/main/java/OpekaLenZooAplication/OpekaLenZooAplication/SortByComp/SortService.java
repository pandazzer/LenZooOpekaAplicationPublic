package OpekaLenZooAplication.OpekaLenZooAplication.SortByComp;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;
import OpekaLenZooAplication.OpekaLenZooAplication.Controllers.GenController;
import OpekaLenZooAplication.OpekaLenZooAplication.SortByComp.Exeptions.NotFoundCurator;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.Objects;

public class SortService extends AbstractChangeScan implements Runnable {

    public SortService(GenController genController, String path) {
        super(genController, path);
    }

    @Override
    public void run() {
        sortFilesByCompanies();
    }

    private void sortFilesByCompanies() {
        double count = 0;
        int countAllFiles = Objects.requireNonNull(directory.listFiles()).length;
        for (File originFile : Objects.requireNonNull(directory.listFiles())) {
            genController.setSortProgBar(++count / countAllFiles);
            if (!originFile.isFile() || originFile.getName().equals("Thumbs.db")) {
                continue;
            }
            try {
                copyFile(originFile);
            } catch (NotFoundCurator ignored) {
            }
        }
    }

    private void copyFile(File originFile) throws NotFoundCurator {
        File newFile = getNewFile(originFile);
        try {
            Files.copy(originFile.toPath(), newFile.toPath());
            genController.addLogText(originFile.getName() + "-->" + newFile.getPath());
        } catch (FileAlreadyExistsException e) {
            genController.addLogText(String.format("%-60s - файл уже существует", originFile.getName()));
        } catch (IOException e) {
            genController.addLogText("Ошибка копирования!!!");
            throw new RuntimeException(e);
        }
    }

    private File getNewFile(File originFile) throws NotFoundCurator {
        String nameCompany = getNameCompanyFromFile(originFile);
        File companyDirectory = new File(Constants.curatorsDirectoryPath + "\\" + nameCompany);
        if (!companyDirectory.exists()) {
            genController.addLogText("нет опекуна: " + nameCompany);
            throw new NotFoundCurator();
        }
        File filePath = new File(companyDirectory + "\\" + directoryParent.getName() + "\\" + directory.getName());
        createDirectory(filePath.getParentFile());
        createDirectory(filePath);
        String path = filePath + "/"
                + directoryParent.getName() + "_"
                + directory.getName() + "_"
                + originFile.getName();
        return new File(path);
    }

    private String getNameCompanyFromFile(File file) {
        if (file.getName().contains("_(")) {
            return file.getName().substring(0, file.getName().indexOf("_("));
        } else {
            return file.getName().substring(0, file.getName().indexOf('.'));
        }
    }

    private void createDirectory(File directory) {
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                genController.addLogText("Ошибка создания директории - " + directory.getPath());
            }
        }
    }
}
