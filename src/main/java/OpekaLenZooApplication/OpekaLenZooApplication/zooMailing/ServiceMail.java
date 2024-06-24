package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing;


import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import OpekaLenZooApplication.OpekaLenZooApplication.Controllers.GenController;
import OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.ENUM.StatusCurator;
import OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.POJO.CuratorsBukkeeping;
import OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.POJO.MailPojo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceMail {
    @Autowired
    private H2Repository repository;
    private List<String> bookkeepingExist;  //переменная следит за найдеными папками и добавлет список в бд
    private final Logger log = LogManager.getLogger();
    private List<CuratorsBukkeeping> correctCurators;

    @Async
    public void startService(MailPojo mailPojo, GenController genController) {
        File curatorsDirectory = new File(Constants.curatorsDirectoryPath);
        SmtpSendMessage sendMessage = new SmtpSendMessage();
        repository.addColumn(mailPojo.bookkeepingList);
        if (!mailPojo.blackList.get(0).equals("")) {
            setBlackList(mailPojo.blackList, mailPojo.bookkeepingList);
        }
        int correctSendCount = 0;
        int alreadySendCount = 0;
        double sumCount = 0;
        int allFilesCount = Objects.requireNonNull(curatorsDirectory.listFiles()).length;
        for (File curatorDir : Objects.requireNonNull(curatorsDirectory.listFiles())) {
            sumCount++;
            String path = curatorDir.getName();
            String address;
            try {
                address = repository.getMailByPath(path);
            } catch (NotMailException e) {
                continue;
            }
            bookkeepingExist = new ArrayList<>();
            List<File> listFilePath = getListFilePath(curatorDir, mailPojo.bookkeepingList);
            if (address != null && !listFilePath.isEmpty()) {
                try {
                    sendMessage.send(address, mailPojo.subject, mailPojo.text, listFilePath);
                    log.warn(path + " -> ");
                    listFilePath.stream().map(File::getName).forEach(log::warn);
                    setSendCorrect(path, bookkeepingExist);
                    correctSendCount++;
                } catch (MessagingException e) {
                    genController.addLogText(String.format(path + " -> "));
                    genController.addLogText(String.format("%-50s - Ошибка отправки!!!", address));
                    log.warn(String.format("%-50s - Ошибка отправки!!!", address));
                    throw new RuntimeException(e);
                }
                log.warn(String.format("%-50s - Отправка завершена", address));
            } else {
                alreadySendCount++;
            }
            genController.setSendMailProgressBar(sumCount / allFilesCount);
        }
        genController.addLogText(String.format("%d - успешно отправлено%n%d - уже было отправлено", correctSendCount, alreadySendCount));
    }

    private List<File> getListFilePath(File curatorDir, List<String> bookkeepingList) {
        List<File> listFiles = new ArrayList<>();
        for (String bookkeeping : bookkeepingList) {
            File curBookkeeping = new File(curatorDir.getPath() + "\\" + bookkeeping);
            try {
                if (curBookkeeping.exists()
                        && !repository.isSend(curatorDir.getName()
                        , bookkeeping.replace("\\", "_"))) {
                    bookkeepingExist.add(bookkeeping);
                    for (File file : Objects.requireNonNull(curBookkeeping.listFiles())) {
                        if (file.getName().equals("Thumbs.db")) {
                            continue;
                        }
                        listFiles.add(file);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return listFiles;
    }

    private void setSendCorrect(String path, List<String> bookkeepingList) {
        for (String bookkeeping : bookkeepingList) {
            repository.setBooleanTrueWithColumn(path, bookkeeping.replace("\\", "_"));
        }
    }


    private void setBlackList(List<String> blackList, List<String> bookkeepingList) {
        for (String path : blackList) {
            for (String bookkeeping : bookkeepingList) {
                repository.setBooleanTrueWithColumn(path, bookkeeping.replace("\\", "_"));
            }
        }
    }
    public void findCorrectCurators () {
        List<String> bookkeepingList = List.of(directoryMailFilesField.getText().split(","));
        StatusCurator statusCurator;
        for (File curatorDir : Objects.requireNonNull(new File(Constants.curatorsDirectoryPath).listFiles())) {
            for (String bookkeeping : bookkeepingList) {
                File curBookkeeping = new File(curatorDir.getPath() + "/" + bookkeeping);
                if (curBookkeeping.exists()) {
                    String path = curatorDir.getName();
                    try {
                        repository.getMailByPath(path);
                        if (repository.isSend(path, bookkeeping.replace("\\", "_"))) {

                        }
                    } catch (NotMailException e) {
                        correctCurators
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    curratorsArea.appendText(curatorDir.getName() + " " + bookkeeping + "\n");
                }
            }
        }
    }
}
