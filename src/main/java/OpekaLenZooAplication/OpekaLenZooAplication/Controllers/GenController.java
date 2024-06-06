package OpekaLenZooAplication.OpekaLenZooAplication.Controllers;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;
import OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.CreateDocxService;
import OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.Exeption.ExistFileException;
import OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.Exeption.IncorrectException;
import OpekaLenZooAplication.OpekaLenZooAplication.SortByComp.DeleteScanInDirectory;
import OpekaLenZooAplication.OpekaLenZooAplication.SortByComp.RenamePNGFiles;
import OpekaLenZooAplication.OpekaLenZooAplication.SortByComp.SortService;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Exeption.NotFoundDataForContract;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Exeption.UncorrectedVisitPerson;
import OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.ServiceDB;
import OpekaLenZooAplication.OpekaLenZooAplication.zooMailing.H2Repository;
import OpekaLenZooAplication.OpekaLenZooAplication.zooMailing.NotMailException;
import OpekaLenZooAplication.OpekaLenZooAplication.zooMailing.POJO.MailPojo;
import OpekaLenZooAplication.OpekaLenZooAplication.zooMailing.ServiceMail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@FxmlView("/maket/12.fxml")
public class GenController {

    private final ObservableList<String> choiceBoxList = FXCollections
            .observableArrayList("Акты сверок", "Акты об оказании услуг", "Счета", "Счета-факутры");

    @FXML
    private TextArea blackListArea;

    @FXML
    private Button cr;

    @FXML
    private Button createCurratorsListButton;

    @FXML
    private TextArea curratorsArea;

    @FXML
    private TextArea dataArea;

    @FXML
    private TextField directoryMailFilesField;

    @FXML
    private TextField fileToPathTextField;

    @FXML
    private Button getDataButton;

    @FXML
    private TextArea logText;
    static TextArea staticLogText;

    @FXML
    private TextArea mailTextField;

    @FXML
    private Button renameButton;

    @FXML
    private ProgressBar renameProgBar;

    @FXML
    private Button sendButton;

    @FXML
    private ProgressBar sendMailProgressBar;

    @FXML
    private Button sortButton;

    @FXML
    private ProgressBar sortProgBar;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField txtcr;

    @FXML
    private CheckBox updateDbCheck;
    @FXML
    private CheckBox checkBoxNew;
    @FXML
    private ChoiceBox<String> chBox;
    @FXML
    private TextArea waste;
    @FXML
    private TextArea absent;
    @FXML
    private Button crListSite;
    @FXML
    private TextArea findDone;
    @FXML
    private TextField sitePathField;
    @FXML
    private Button clearLogs;
    @FXML
    private Button deleteScanButton;
    @FXML
    private Button createListKit;

    @Autowired
    ServiceDB serviceDB;
    @Autowired
    H2Repository repository;
    @Autowired
    ServiceMail serviceMail;
    @Autowired
    CreateDocxService createService;

    @FXML
    void initialize() {
        staticLogText = logText;
        chBox.setItems(choiceBoxList);
        renameButton.setOnAction(e -> handleRenameButton());
        sortButton.setOnAction(e -> handleSortButton());
        createListKit.setOnAction(e -> handleCreateListKit());
        createCurratorsListButton.setOnAction(e -> handleCurratorsListButton());
        sendButton.setOnAction(e -> handleSendButton());
        getDataButton.setOnAction(e -> handleGetDataButton());
        cr.setOnAction(e -> handleCreateButton());
        clearLogs.setOnAction(e -> logText.clear());
        deleteScanButton.setOnAction(e -> handleDeleteScan());
    }


    private void handleDeleteScan() {
        String path = fileToPathTextField.getText();
        if (path.equals("")) {
            showAlert("Нет пути!");
            return;
        }
        new Thread(new DeleteScanInDirectory(this, path)).start();
    }

    private void handleCreateButton() {
        try {
            createService.createContract(txtcr.getText());
            showAlert("Договор создан!");
        } catch (NotFoundDataForContract e) {
            showAlert("Ошибка создания договора" + e);
        } catch (IncorrectException | ExistFileException | UncorrectedVisitPerson e) {
            showAlert(e.getMessage());
        }
    }

    private void handleGetDataButton() {
        for (String str : repository.getData()) {
            dataArea.appendText(str + "\n");
        }
    }

    private void handleSendButton() {
        String subject = subjectField.getText();
        String text = mailTextField.getText();
        List<String> blackList = Arrays.stream(blackListArea.getText().split("\n")).toList();
        List<String> bookkeepingList = List.of(directoryMailFilesField.getText().split(","));
        if (isEmptyField(subject) || isEmptyField(text) || isEmptyField(bookkeepingList.get(0))) {
            return;
        }
        MailPojo mailPojo = new MailPojo(subject, text, blackList, bookkeepingList);
        serviceMail.startService(mailPojo, this);
    }

    private void handleCurratorsListButton() {
        curratorsArea.clear();
        updateDB();
        appendCorrectCuratorsToArea();
        sendButton.setDisable(false);
    }

    private void updateDB() {
        if (!updateDbCheck.isSelected()) {
            serviceDB.start();
            updateDbCheck.setSelected(true);
        }
    }

    private void appendCorrectCuratorsToArea() {
        List<String> bookkeepingList = List.of(directoryMailFilesField.getText().split(","));
        for (File curatorDir : Objects.requireNonNull(new File(Constants.curatorsDirectoryPath).listFiles())) {
            for (String bookkeeping : bookkeepingList) {
                File curBookkeeping = new File(curatorDir.getPath() + "/" + bookkeeping);
                if (curBookkeeping.exists()) {
                    String path = curatorDir.getName();

                    try {
                        repository.getMailByPath(path);
                    } catch (NotMailException e) {
                        curratorsArea.appendText("(0)-");
                        showAlert(path + " " + e.getMessage());
                    }
                    try {
                        if (repository.isSend(path, bookkeeping.replace("\\", "_"))) {
                            curratorsArea.appendText("(1)-");
                        }
                    } catch (SQLException ignored) {
                    }
                    curratorsArea.appendText(curatorDir.getName() + " " + bookkeeping + "\n");
                }
            }
        }
    }

    private void handleCreateListKit() {
        curratorsArea.clear();
        appendNotKitCuratorsToArea();
    }

    private void appendNotKitCuratorsToArea() {
        List<String> bookkeepingList = List.of(directoryMailFilesField.getText().split(","));
        for (File curatorDir : Objects.requireNonNull(new File(Constants.curatorsDirectoryPath).listFiles())) {
            List<String> bookkeepingNotExist = new ArrayList<>();
            boolean isBookkeepingExist = false;
            int countExist = 0;
            for (String bookkeeping : bookkeepingList) {
                File curBookkeeping = new File(curatorDir.getPath() + "/" + bookkeeping);
                if (curBookkeeping.exists()) {
                    isBookkeepingExist = true;
                    countExist++;
                } else {
                    bookkeepingNotExist.add(bookkeeping.replace("\\", "!").split("!")[1]);
                }
            }
            if (isBookkeepingExist && countExist != bookkeepingList.size()){
                curratorsArea.appendText(String.format("%s нет:" + bookkeepingNotExist + "\n", curatorDir.getName()));
            }
        }
    }

    private void handleSortButton() {
        String path = fileToPathTextField.getText();
        if (path.equals("")) {
            showAlert("Нет пути!");
            return;
        }
        new Thread(new SortService(this, path)).start();
    }

    private void handleRenameButton() {
        String path = fileToPathTextField.getText();
        String chBoxString = chBox.getValue();
        if (path.equals("") || chBoxString == null) {
            showAlert("Нет пути или формата документа!");
            return;
        }
        new Thread(new RenamePNGFiles(this, path, chBoxString)).start();
    }

    private boolean isEmptyField(String str) {
        if (str.equals("")) {
            showAlert("Пустое поле");
            return true;
        }
        return false;
    }

    private void showAlert(String text) {
        new Alert(Alert.AlertType.WARNING, text).showAndWait();
    }

    public void setRenameProgBar(double prog) {
        renameProgBar.setProgress(prog);
    }

    public void addLogText(String log) {
        logText.appendText(log + "\n");
    }

    public void setSortProgBar(double progBar) {
        sortProgBar.setProgress(progBar);
    }

    public void setSendMailProgressBar(double progBar) {
        sendMailProgressBar.setProgress(progBar);
    }
}

