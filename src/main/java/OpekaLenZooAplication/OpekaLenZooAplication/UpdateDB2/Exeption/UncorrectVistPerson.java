package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Exeption;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

public class UncorrectVistPerson extends Exception {
    public UncorrectVistPerson(){
        super(Constants.VisitPersonExeption);
    }
}
