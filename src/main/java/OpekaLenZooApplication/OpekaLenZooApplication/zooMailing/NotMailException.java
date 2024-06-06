package OpekaLenZooApplication.OpekaLenZooApplication.zooMailing;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;

public class NotMailException extends Exception {
    public NotMailException(){
        super(Constants.NOT_MAIL_MESSAGE);
    }
}
