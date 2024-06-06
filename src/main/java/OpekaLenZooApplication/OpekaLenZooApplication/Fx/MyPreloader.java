package OpekaLenZooApplication.OpekaLenZooApplication.Fx;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Objects;
@Component
public class MyPreloader extends Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.preloaderStage = stage;
        stage.setTitle(Constants.nameApplication);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.iconPath))));
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.loadWindowXMLpath)));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.show();

        PrintStream printStream = new PrintStream(new CustomOutputStream());
        System.setOut(printStream);
    }


    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START){
            preloaderStage.hide();
        }
    }

}
