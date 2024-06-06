package OpekaLenZooAplication.OpekaLenZooAplication;


import OpekaLenZooAplication.OpekaLenZooAplication.Fx.FxApp;
import OpekaLenZooAplication.OpekaLenZooAplication.Fx.MyPreloader;
import com.sun.javafx.application.LauncherImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpekaLenZooAplicationApplication {

	public static void main(String[] args) {
		LauncherImpl.launchApplication(FxApp.class, MyPreloader.class, args);
	}


}
