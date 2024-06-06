package OpekaLenZooAplication.OpekaLenZooAplication.CreateNewContract.Exeption;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

public class ExistFileExeption extends Exception{
    public ExistFileExeption(){
        super(Constants.FILE_ALREADY_EXIST);
    }
}
