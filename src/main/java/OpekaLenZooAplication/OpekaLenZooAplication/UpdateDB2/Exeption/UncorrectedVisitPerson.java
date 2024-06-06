package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Exeption;

import OpekaLenZooAplication.OpekaLenZooAplication.Constants;

public class UncorrectedVisitPerson extends Exception {
    public UncorrectedVisitPerson(){
        super(Constants.VisitPersonExeption);
    }
}
