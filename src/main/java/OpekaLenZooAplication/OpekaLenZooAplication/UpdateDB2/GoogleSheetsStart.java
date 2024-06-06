package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2;

import com.google.api.services.sheets.v4.Sheets;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleSheetsStart {
    Sheets getSheetsService();
}
