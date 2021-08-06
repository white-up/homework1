package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.UserMapperImpl;
import entity.User;
import entity.statisticalData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/statisticalDataServlet")
public class StatisticalDataServlet extends HttpServlet {
    ApplicationContext applicationContext = new
            ClassPathXmlApplicationContext("applicationContext.xml");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        statisticalData statisticalData[]=getStatisticalData();
        ObjectMapper Mapper = (ObjectMapper) applicationContext.getBean("ObjectMapper");
        resp.setContentType("application/json;charset=utf-8");
        Mapper.writeValue(resp.getOutputStream(), statisticalData);
    }
    public statisticalData[] getStatisticalData(){
        statisticalData[] statisticalData=new statisticalData[3];
        UserMapperImpl userMapper=(UserMapperImpl) applicationContext.getBean("UserMapperImpl");
        List<User> userList= userMapper.mapper.findAll();
        double[] tempMax=new double[6];
        double[] tempMin=new double[6];
        double[] tempCenter=new double[6];
        double[] tempBasePay=new double[userList.size()];
        double[] tempJobSalary=new double[userList.size()];
        double[] tempJobSubsidy=new double[userList.size()];
        double[] tempMedicalInsurance=new double[userList.size()];
        double[] tempAccumulationFund=new double[userList.size()];
        double[] tempSum=new double[userList.size()];
        int i=0;
        int size=userList.size();
        for (User userDatum : userList) {
            tempBasePay[i]=userDatum.basePay;
            tempJobSalary[i]=userDatum.jobSalary;
            tempJobSubsidy[i]=userDatum.jobSubsidy;
            tempMedicalInsurance[i]=userDatum.medicalInsurance;
            tempAccumulationFund[i]=userDatum.accumulationFund;
            tempSum[i]=userDatum.basePay+userDatum.jobSubsidy+userDatum.jobSalary+userDatum.medicalInsurance+userDatum.accumulationFund;
            tempCenter[0]+=userDatum.basePay/size;
            tempCenter[1]+=userDatum.jobSubsidy/size;
            tempCenter[2]+=userDatum.jobSalary/size;
            tempCenter[3]+=userDatum.medicalInsurance/size;
            tempCenter[4]+=userDatum.accumulationFund/size;
            tempCenter[5]+=tempSum[i]*(1/size);
            i++;
        }
        for (int j=0;j<size-1;j++){
            tempMax[0]=Math.max(tempBasePay[j],tempBasePay[j+1]);
            tempMax[1]=Math.max(tempJobSalary[j],tempJobSalary[j+1]);
            tempMax[2]=Math.max(tempJobSubsidy[j],tempJobSubsidy[j+1]);
            tempMax[3]=Math.max(tempMedicalInsurance[j],tempMedicalInsurance[j+1]);
            tempMax[4]=Math.max(tempAccumulationFund[j],tempAccumulationFund[j+1]);
            tempMax[5]=Math.max(tempSum[j],tempSum[j+1]);
            tempMin[0]=Math.min(tempBasePay[j],tempBasePay[j+1]);
            tempMin[1]=Math.min(tempJobSalary[j],tempJobSalary[j+1]);
            tempMin[2]=Math.min(tempJobSubsidy[j],tempJobSubsidy[j+1]);
            tempMin[3]=Math.min(tempMedicalInsurance[j],tempMedicalInsurance[j+1]);
            tempMin[4]=Math.min(tempAccumulationFund[j],tempAccumulationFund[j+1]);
            tempMin[5]=Math.min(tempSum[j],tempSum[j+1]);
        }

        statisticalData[0]=new statisticalData(tempMax[5],tempMax[0],tempMax[1],tempMax[2],tempMax[3],tempMax[4]);
        statisticalData[1]=new statisticalData(tempMin[5],tempMin[0],tempMin[1],tempMin[2],tempMin[3],tempMin[4]);
        statisticalData[2]=new statisticalData(tempCenter[5],tempCenter[0],tempCenter[1],tempCenter[2],tempCenter[3],tempCenter[4]);
           return statisticalData;
    }
}