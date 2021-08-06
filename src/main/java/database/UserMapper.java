package database;


import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> findAll();
    public List<User> findByName(String name);
    public List<User> findById(int id);
    public List<User> findByGender(String gender);
    public void updateNameById(@Param("id") int id, @Param("name") String name);
    public void updateGenderById(@Param("id") int id,@Param("gender") String gender);
    public void updateBasePayById(@Param("id")int id,@Param("basePay") double basePay);
    public void updateJobSalaryById(@Param("id")int id,@Param("jobSalary") double jobSalary);
    public void updateJobSubsidyById(@Param("id")int id,@Param("jobSubsidy") double jobSubsidy);
    public void updateMedicalInsuranceById(@Param("id")int id,@Param("medicalInsurance") double medicalInsurance);
    public void updateAccumulationFundById(@Param("id")int id,@Param("accumulationFund") double accumulationFund);
    public void deleteById(int id);
    public void addUser(User user);
}