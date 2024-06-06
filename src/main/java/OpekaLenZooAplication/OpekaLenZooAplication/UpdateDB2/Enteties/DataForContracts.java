package OpekaLenZooAplication.OpekaLenZooAplication.UpdateDB2.Enteties;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Locale;

public class DataForContracts {
    private String name;
    private String contractNumber;
    private String month;
    protected String animal;
    protected String periodForPay;
    protected String nameForCert;
    protected String numberPhone;
    protected String mail;
    protected String dateStart;
    protected String dateEnd;
    protected int sumPart;
    protected int sum;
    protected String billet;
    protected boolean isIndividual;


    public DataForContracts(String name, String animal, String periodForPay, String nameForCert, String numberPhone, String mail, String dateStart, String dateEnd, int sumPart, int sum, String billet, boolean isIndividual) {
        this.name = name;
        this.animal = animal;
        this.periodForPay = periodForPay;
        this.nameForCert = nameForCert;
        this.numberPhone = numberPhone;
        this.mail = mail;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.sumPart = sumPart;
        this.sum = sum;
        this.billet = billet;
        this.isIndividual = isIndividual;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animal;
    }

    public String getPeriodForPay() {
        return periodForPay;
    }

    public String getNameForCert() {
        return nameForCert;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public String getMail() {
        return mail;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public int getSumPart() {
        return sumPart;
    }

    public int getSum() {
        return sum;
    }

    public String getBillet() {
        return billet;
    }

    public boolean isIndividual() {
        return isIndividual;
    }

    @Override
    public String toString() {
        return "DataForContracts{" +
                "name='" + name + '\n' +
                "animal='" + animal + '\n' +
                "periodForPay='" + periodForPay + '\n' +
                "nameForCert='" + nameForCert + '\n' +
                "numberPhone='" + numberPhone + '\n' +
                "mail='" + mail + '\n' +
                "dateStart='" + dateStart + '\n' +
                "dateEnd='" + dateEnd + '\n' +
                "sumPart='" + sumPart + '\n' +
                "billet='" + billet + '\n' +
                "isIndividual=" + isIndividual +
                '}';
    }
}
