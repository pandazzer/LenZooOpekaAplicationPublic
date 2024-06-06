package OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.Exeption;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

public class ExistFileException extends Exception{
    public ExistFileException(){
        super(Constants.FILE_ALREADY_EXIST);
    }
}
