package OpekaLenZooApplication.OpekaLenZooApplication.UpdateDB2.Exeption;

import OpekaLenZooApplication.OpekaLenZooApplication.Constants;

public class UncorrectedVisitPerson extends Exception {
    public UncorrectedVisitPerson(){
        super(Constants.VisitPersonExeption);
    }
}
