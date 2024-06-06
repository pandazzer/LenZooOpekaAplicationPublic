package OpekaLenZooAplication.OpekaLenZooAplication.SortByComp;


import OpekaLenZooAplication.OpekaLenZooAplication.Controllers.GenController;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties.ReturnCompany;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RenamePNGFiles implements Runnable {
    private final GenController genController;
    private final File direct;
    private final String chBox;
    private double count = 0;
    private final int countAllFile;

    public RenamePNGFiles(GenController genController, String path, String chBox) {
        this.genController = genController;
        this.direct = new File(path);
        this.chBox = chBox;
        this.countAllFile = Objects.requireNonNull(direct.listFiles()).length;
    }

    @Override
    public void run() {
        rename(5);
    }

    public void rename(int countThread) {
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        List<Future<ReturnCompany>> futureList = new ArrayList<>();
        for (File file : Objects.requireNonNull(direct.listFiles())) {
            if (!isPngFile(file)) {
                continue;
            }
            Future<ReturnCompany> future = executorService.submit(new RenameFileThread(file, chBox));
            futureList.add(future);
        }
        executorService.shutdown();
        for (Future<ReturnCompany> future : futureList) {
            try {
                ReturnCompany returnCompany = future.get();
                genController.addLogText(String.format("%-50s -- %s"
                        , returnCompany.getNameCompany()
                        , returnCompany.getCorrectNameCompany()));
                genController.setRenameProgBar(++count / countAllFile);
            } catch (InterruptedException | ExecutionException e) {
                genController.setRenameProgBar(++count / countAllFile);
                genController.addLogText("Ошибка переименования");
            }
        }
        genController.addLogText("Готово");
    }

    private boolean isPngFile(File file) {
        if (file.getName().split("\\.")[1].equals("png")) {
            return true;
        } else {
            genController.setRenameProgBar(++count / countAllFile);
            return false;
        }
    }
}
