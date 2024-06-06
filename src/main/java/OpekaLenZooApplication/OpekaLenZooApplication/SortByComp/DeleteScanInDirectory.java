package OpekaLenZooApplication.OpekaLenZooApplication.SortByComp;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import OpekaLenZooApplication.OpekaLenZooApplication.Controllers.GenController;

import java.io.File;
import java.util.Objects;

public class DeleteScanInDirectory extends AbstractChangeScan implements Runnable {
    public DeleteScanInDirectory(GenController genController, String path) {
        super(genController, path);
    }

    @Override
    public void run() {
        deleteDirectory();
    }

    private void deleteDirectory() {
        double count = 0;
        File curatorsDir = new File(Constants.curatorsDirectoryPath);
        int countAllFiles = Objects.requireNonNull(curatorsDir.listFiles()).length;
        for (File curatorDir : Objects.requireNonNull(curatorsDir.listFiles())) {
            genController.setSortProgBar(++count / countAllFiles);
            File curBookkeeping = new File(curatorDir.getPath() + "/" + directoryParent.getName() + "/" + directory.getName());
            if (curBookkeeping.exists()) {
                delFileInDirectory(curBookkeeping);
                if (curBookkeeping.delete()) {
                    genController.addLogText(curBookkeeping.getPath() + " - удалён");
                }
            }
        }
    }

    private void delFileInDirectory(File dir) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            file.delete();
        }
    }
}
