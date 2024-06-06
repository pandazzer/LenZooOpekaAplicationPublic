package OpekaLenZooAplication.OpekaLenZooAplication.SortByComp;

import OpekaLenZooAplication.OpekaLenZooAplication.Controllers.GenController;

import java.io.File;

abstract class AbstractChangeScan {
    protected final File directory;
    protected final File directoryParent;
    protected final GenController genController;

    public AbstractChangeScan(GenController genController, String path) {
        this.directory = new File(path);
        this.genController = genController;
        this.directoryParent = new File(directory.getParent());
    }
}
