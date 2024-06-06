package OpekaLenZooAplication.OpekaLenZooAplication.zooMailing;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

public class NotMailExeption extends Exception {
    public NotMailExeption(){
        super(Constants.NOT_MAIL_MESSAGE);
    }
}
