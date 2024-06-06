package OpekaLenZooApplication.OpekaLenZooApplication.SortByComp;

import OpekaLenZooApplication.OpekaLenZooApplication.Controllers.GenController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RenamePNGFilesTest {
    private static RenamePNGFiles renamePNGFiles;

    @BeforeAll
    static void beforeAll() {
        GenController genController = Mockito.mock(GenController.class);
        String path = "C:\\Users\\komm2\\Desktop\\Новая папка";
        String chBox = "Счета";
        renamePNGFiles = new RenamePNGFiles(genController, path, chBox);
    }

    @Test
    void run() {
    }

    @Test
    void rename() {
        for (int i = 1; i < 70; i++) {
            long start = System.currentTimeMillis();
            renamePNGFiles.rename(i);
            long end = System.currentTimeMillis();
            System.out.println(i +" потоков - " + (end - start)/1000 + "с.");
        }
    }
}