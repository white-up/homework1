package entity;

public class statisticalData {
    public String measure;
    public double sum;
    public double basePay;
    public double jobSalary;
    public double jobSubsidy;
    public double medicalInsurance;
    public double accumulationFund;

    public statisticalData() {
    }

    public statisticalData(double sum, double basePay, double jobSalary, double jobSubsidy, double medicalInsurance, double accumulationFund) {
        this.sum = sum;
        this.basePay = basePay;
        this.jobSalary = jobSalary;
        this.jobSubsidy = jobSubsidy;
        this.medicalInsurance = medicalInsurance;
        this.accumulationFund = accumulationFund;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getBasePay() {
        return basePay;
    }

    public void setBasePay(double basePay) {
        this.basePay = basePay;
    }

    public double getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(double jobSalary) {
        this.jobSalary = jobSalary;
    }

    public double getJobSubsidy() {
        return jobSubsidy;
    }

    public void setJobSubsidy(double jobSubsidy) {
        this.jobSubsidy = jobSubsidy;
    }

    public double getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(double medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public double getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(double accumulationFund) {
        this.accumulationFund = accumulationFund;
    }
}