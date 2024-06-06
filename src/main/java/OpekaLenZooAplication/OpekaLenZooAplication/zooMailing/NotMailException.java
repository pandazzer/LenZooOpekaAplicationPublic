package OpekaLenZooAplication.OpekaLenZooAplication.zooMailing;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

public class NotMailException extends Exception {
    public NotMailException(){
        super(Constants.NOT_MAIL_MESSAGE);
    }
}
