package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class H2Repository {
    private final Connection connection;
    private static final Logger log = LogManager.getLogger();

    public H2Repository() {
        ConnectDB connectDB = new ConnectDB();
        this.connection = connectDB.getConnect();
    }

    public List<String> getData() {
        List<String> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM curators");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append(metaData.getColumnName(i));
            }
            result.add(stringBuilder.toString());
            while (resultSet.next()) {
                StringBuilder stringBuilder1 = new StringBuilder();
                for (int i = 1; i < columnCount; i++) {
                    stringBuilder1.append(resultSet.getString(i));
                }
                result.add(stringBuilder1.toString());
            }
            return result;
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                log.warn("Не удается закрыть соединение");
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }

    public String getMailByPath(String path) throws NotMailException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT mail FROM curators WHERE path = ?");
            statement.setString(1, path);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                log.info(String.format("%-50s - Данные не найдены", path));
                throw new NotMailException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSend(String path, String bookkeeping) {
        if (bookkeeping.equals("")) {
            return false;
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM curators WHERE path = '%s'", bookkeeping, path));
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(List<String> bookkeepingList) {
        for (String var : bookkeepingList) {
            String bookkeeping = var.replace("\\", "_");
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("ALTER TABLE curators ADD COLUMN " +
                        bookkeeping +
                        " BOOLEAN DEFAULT 'false'");
            } catch (SQLException e) {
                log.info(bookkeeping + " - колонка уже создана");
            }
        }
    }

    public void setBooleanTrueWithColumn(String path, String bookkeeping) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE curators SET %s = 'true' WHERE path = '%s'", bookkeeping, path));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
