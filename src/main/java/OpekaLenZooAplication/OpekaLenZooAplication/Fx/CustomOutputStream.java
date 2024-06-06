package OpekaLenZooAplication.OpekaLenZooAplication.Fx;

import OpekaLenZooAplication.OpekaLenZooAplication.Controllers.PreloaderController;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class CustomOutputStream extends OutputStream {
    @Override
    public void write(int b) throws IOException {
        write(new byte[] { (byte) b }, 0, 1);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        final String text = new String(b, off, len);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PreloaderController.staticTextArea.appendText (text);
            }
        });
    }
}
