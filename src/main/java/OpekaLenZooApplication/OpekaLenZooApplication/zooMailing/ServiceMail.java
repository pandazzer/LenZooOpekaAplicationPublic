package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing;


import OpekaLenZooApplication.OpekaLenZooApplication.Constants;
import OpekaLenZooApplication.OpekaLenZooApplication.Controllers.GenController;
import OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.ENUM.StatusCurator;
import OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.POJO.CuratorsBookkeeping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.*;

@Service
public class ServiceMail {
    @Autowired
    private H2Repository repository;
    private List<String> bookkeepingExist;  //переменная следит за найдеными папками и добавлет список в бд
    private final Logger log = LogManager.getLogger();
    private List<CuratorsBookkeeping> foundCorrectCurators;
    private String[] bookkeepingList;
    private String subject;
    private String text;
    private HashSet<String> blackList;

    @Async
    public void startService(GenController genController) {
        SmtpSendMessage sendMessage = new SmtpSendMessage();
        repository.addColumn(List.of(bookkeepingList));
        int correctSendCount = 0;
        int alreadySendCount = 0;
        double sumCount = 0;
        int allFilesCount = foundCorrectCurators.size();
        for (CuratorsBookkeeping curatorsBookkeeping : foundCorrectCurators) {
            sumCount++;
            if (curatorsBookkeeping.status() != StatusCurator.OK) continue;
            String name = curatorsBookkeeping.curator().getName();
            String address = curatorsBookkeeping.mailAddress();
            bookkeepingExist = new ArrayList<>();
            List<File> listFilePath = getListFilePath(curatorsBookkeeping.curator(), bookkeepingList);
            if (!listFilePath.isEmpty()) {
                try {
                    sendMessage.send(address, subject, text, listFilePath);
                    log.warn(name + " -> ");
                    listFilePath.stream().map(File::getName).forEach(log::warn);
                    setSendCorrect(name, bookkeepingExist);
                    correctSendCount++;
                } catch (MessagingException e) {
                    genController.addLogText(String.format(name + " -> "));
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

    private List<File> getListFilePath(File curatorDir, String[] bookkeepingList) {
        List<File> listFiles = new ArrayList<>();
        for (String bookkeeping : bookkeepingList) {
            File curBookkeeping = new File(curatorDir.getPath() + "\\" + bookkeeping);
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
        }
        return listFiles;
    }

    private void setSendCorrect(String name, List<String> bookkeepingList) {
        for (String bookkeeping : bookkeepingList) {
            repository.setBooleanTrueWithColumn(name, bookkeeping.replace("\\", "_"));
        }
    }


    private void setBlackList(String[] blackList, String[] bookkeepingList) {
        for (String path : blackList) {
            for (String bookkeeping : bookkeepingList) {
                repository.setBooleanTrueWithColumn(path, bookkeeping.replace("\\", "_"));
            }
        }
    }

    private void findCorrectCurators() {
        foundCorrectCurators = new ArrayList<>();
        StatusCurator statusCurator = StatusCurator.OK;
        for (File curatorDir : Objects.requireNonNull(new File(Constants.curatorsDirectoryPath).listFiles())) {
            for (String bookkeeping : bookkeepingList) {
                File curBookkeeping = new File(curatorDir.getPath() + "/" + bookkeeping);
                String email = null;
                if (curBookkeeping.exists()) {
                    String path = curatorDir.getName();
                    try {
                        email = repository.getMailByPath(path);
                    } catch (NotMailException e) {
                        statusCurator = StatusCurator.NO_MAIL;
                    }
                    if (repository.isSend(path, bookkeeping.replace("\\", "_"))) {
                        statusCurator = StatusCurator.ALREADY_SEND;
                    }
                    if (blackList.contains(curatorDir.getName())) {
                        statusCurator = StatusCurator.IN_BLACK_LIST;
                    }
                    foundCorrectCurators.add(new CuratorsBookkeeping(curatorDir, bookkeeping, email, statusCurator));
                }
            }
        }
    }

    public void setBookkeepingList(String[] bookkeepingList) {
        if (!Arrays.equals(this.bookkeepingList, bookkeepingList)) {
            this.bookkeepingList = bookkeepingList;
            findCorrectCurators();
        }
    }

    public List<CuratorsBookkeeping> getFoundCorrectCurators(String[] bookkeepingList) {
        setBookkeepingList(bookkeepingList);
        return foundCorrectCurators;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBlackList(HashSet<String> blackList) {
        this.blackList = blackList;
    }
}
