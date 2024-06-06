package OpekaLenZooApplication.OpekaLenZooApplication.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/maket/loadView.fxml")
public class PreloaderController {
    @FXML
    private TextArea loadTextArea;
    public static TextArea staticTextArea;

    @FXML
    void initialize() {
        staticTextArea = loadTextArea;
    }
}
