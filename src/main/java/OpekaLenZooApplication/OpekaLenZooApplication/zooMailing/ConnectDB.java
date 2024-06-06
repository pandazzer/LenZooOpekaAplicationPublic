package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public Connection getConnect(){
        try {
            Class.forName ("org.h2.Driver");
            return DriverManager.getConnection(Constants.pathDB, Constants.userDB, Constants.passDB);
        } catch (SQLException e) {
            System.out.println("Не удалось подключится к бд " + e);
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти драйвер к H2");
            throw new RuntimeException(e);
        }
    }
}
