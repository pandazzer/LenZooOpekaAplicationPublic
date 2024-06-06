package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing.POJO;

import java.util.List;

public class MailPojo {
    public String subject;
    public String text;
    public List<String> bookkeepingList;
    public List<String> blackList;

    public MailPojo(String subject, String text, List<String> blackList, List<String> bookkeepingList) {
        this.subject = subject;
        this.text = text;
        this.blackList = blackList;
        this.bookkeepingList = bookkeepingList;
    }
}
