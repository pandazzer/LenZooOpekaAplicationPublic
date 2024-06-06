package OpekaLenZooApplication.OpekaLenZooApplication;


import OpekaLenZooApplication.OpekaLenZooApplication.Fx.FxApp;
import OpekaLenZooApplication.OpekaLenZooApplication.Fx.MyPreloader;
import com.sun.javafx.application.LauncherImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpekaLenZooApplication {

	public static void main(String[] args) {
		LauncherImpl.launchApplication(FxApp.class, MyPreloader.class, args);
	}
}
