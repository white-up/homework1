package entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class User {
    @ExcelProperty("name")
    public String name;
    @ExcelProperty("id")
    public Integer id;
    @ExcelProperty("gender")
    public String gender;
    @ExcelProperty("basePay")
    public double basePay;
    @ExcelProperty("jobSalary")
    public double jobSalary;
    @ExcelProperty("jobSubsidy")
    public double jobSubsidy;
    @ExcelProperty("medicalInsurance")
    public double medicalInsurance;
    @ExcelProperty("accumulationFund")
    public double accumulationFund;
    public User(){}
    public User(String name, String gender, double basePay, double jobSalary, double jobSubsidy, double medicalInsurance, double accumulationFund) {
        this.name = name;
        this.gender = gender;
        this.basePay = basePay;
        this.jobSalary = jobSalary;
        this.jobSubsidy = jobSubsidy;
        this.medicalInsurance = medicalInsurance;
        this.accumulationFund = accumulationFund;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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