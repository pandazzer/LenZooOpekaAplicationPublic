package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Exeption.GoogleTokenException;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleWithoutToken implements GoogleSheetsStart {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final Logger LOG = LogManager.getLogger();

    private GoogleCredentials getCredentials() throws GoogleTokenException {
        GoogleCredentials credentials;
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(Constants.CREDENTIALS_FILE_PATH)) {
            credentials = ServiceAccountCredentials.fromStream(inputStream).createScoped(SCOPES);
            LOG.info("ServiceAccountJsonFile just read.");
        } catch (IOException | RuntimeException e) {
            throw new GoogleTokenException("Could not read JSON-file or wrong fields of JSON-file in path: "
                    + Constants.CREDENTIALS_FILE_PATH);
        }

        return credentials;
    }

    public Sheets getSheetsService() {
        try {
            GoogleCredentials credential = getCredentials();
            return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY,
                    new HttpCredentialsAdapter(credential)).setApplicationName(Constants.APPLICATION_NAME).build();
        } catch (GeneralSecurityException | GoogleTokenException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
