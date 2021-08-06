package Servlet;

import database.UserMapperImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/updateUserServlet")
public class updateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.valueOf(req.getParameter("id"));
        String property=req.getParameter("property");
        String error = null;
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImpl userMapper=(UserMapperImpl)applicationContext.getBean("UserMapperImpl");
        System.out.println(property);
        if("UserMoneyInformation".equals(property)){
            double basePay=Double.parseDouble(URLDecoder.decode(req.getParameter("basePay"), "UTF-8"));
            double jobSalary=Double.parseDouble(URLDecoder.decode(req.getParameter("jobSalary"), "UTF-8"));
            double jobSubsidy=Double.parseDouble(URLDecoder.decode(req.getParameter("jobSubsidy"), "UTF-8"));
            double medicalInsurance=Double.parseDouble(URLDecoder.decode(req.getParameter("medicalInsurance"), "UTF-8"));
            double accumulationFund=Double.parseDouble(URLDecoder.decode(req.getParameter("accumulationFund"), "UTF-8"));
            try {
                userMapper.mapper.updateBasePayById(id,basePay);
                userMapper.mapper.updateAccumulationFundById(id,accumulationFund);
                userMapper.mapper.updateJobSalaryById(id,jobSalary);
                userMapper.mapper.updateMedicalInsuranceById(id,medicalInsurance);
                userMapper.mapper.updateJobSubsidyById(id,jobSubsidy);
                userMapper.sqlSession.commit();
            }catch (Exception e){
                System.out.println(e);
                error = "发生错误";
            }
        }
        if("UserPersonalInformation".equals(property)) {
            String name = URLDecoder.decode(req.getParameter("name"), "UTF-8");
            String gender = URLDecoder.decode(req.getParameter("gender"), "UTF-8");
            try {
                if (!userMapper.mapper.findById(id).get(0).name.equals(name)) {
                    userMapper.mapper.updateNameById(id, name);
                    userMapper.sqlSession.commit();
                }
                if (!userMapper.mapper.findById(id).get(0).gender.equals(gender)) {
                    userMapper.mapper.updateGenderById(id, gender);
                    userMapper.sqlSession.commit();
                }

            } catch (Exception e) {
                System.out.println(e);
                error = "发生错误";
            }
        }
        req.setAttribute("error", error);
    }
}