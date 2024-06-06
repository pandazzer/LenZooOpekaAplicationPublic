package OpekaLenZooApplication.OpekaLenZooApplication.CreateNewContract.Exeption;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;

public class ExistFileException extends Exception{
    public ExistFileException(){
        super(Constants.FILE_ALREADY_EXIST);
    }
}
