package OpekaLenZooAplication.OpekaLenZooAplication.Fx;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;
import OpekaLenZooAplication.OpekaLenZooAplication.Controllers.GenController;
import OpekaLenZooAplication.OpekaLenZooAplication.OpekaLenZooAplicationApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Objects;

@Component
public class FxApp extends Application {
    private ConfigurableApplicationContext applicationContext;
    private final Logger log = LogManager.getLogger();


    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(OpekaLenZooAplicationApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        stage.setTitle(Constants.nameApplication);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(Constants.iconPath))));
        showGenWindow(stage);
        log.warn("Application start");
    }

    private void showGenWindow(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        TabPane rootLayout = fxWeaver.loadView(GenController.class);
        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.show();
    }
}
