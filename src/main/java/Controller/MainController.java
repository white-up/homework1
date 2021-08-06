package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import database.UserMapperImpl;
import database.changeRecordMapperImpl;
import entity.User;
import entity.changeRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/addUserData")
    public String addUserData(HttpServletRequest req){
        String error=null;
        try {
            String name = URLDecoder.decode(req.getParameter("name"), "UTF-8");
            String gender = URLDecoder.decode(req.getParameter("gender"), "UTF-8");
            double basePay = Double.parseDouble(URLDecoder.decode(req.getParameter("basePay"), "UTF-8"));
            double jobSalary = Double.parseDouble(URLDecoder.decode(req.getParameter("jobSalary"), "UTF-8"));
            double jobSubsidy = Double.parseDouble(URLDecoder.decode(req.getParameter("jobSubsidy"), "UTF-8"));
            double medicalInsurance = Double.parseDouble(URLDecoder.decode(req.getParameter("medicalInsurance"), "UTF-8"));
            double accumulationFund = Double.parseDouble(URLDecoder.decode(req.getParameter("accumulationFund"), "UTF-8"));
            User user = new User(name, gender, basePay, jobSalary, jobSubsidy, medicalInsurance, accumulationFund);
            ApplicationContext applicationContext = new
                    ClassPathXmlApplicationContext("applicationContext.xml");
            UserMapperImpl userMapper = (UserMapperImpl) applicationContext.getBean("UserMapperImpl");
            userMapper.mapper.addUser(user);
            userMapper.sqlSession.commit();
        }catch (Exception e){
            System.out.println(e);
            error="添加发生错误";
        }
        req.setAttribute("error", error);
        return "addUser";
    }

    @RequestMapping("/changeRecordListServlet")
    public String changeRecordList(HttpServletRequest req,HttpServletResponse resp)  {
        String currentPageStr = req.getParameter("currentPageChange");
        //处理参数
        int currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        //分页实现
        PageHelper.startPage(currentPage, 5);
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        changeRecordMapperImpl changeRecordMapper=(changeRecordMapperImpl) applicationContext.getBean("changeRecordMapperImpl");
        List<changeRecord> changeRecords=changeRecordMapper.mapper.findAll();
        for (changeRecord changeRecord : changeRecords) {
            System.out.println(changeRecord);
        }
        PageInfo<changeRecord> pageInfo = new PageInfo<>(changeRecords);
        PageBean<changeRecord> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setList(changeRecords);
        pb.setTotalCount((int) pageInfo.getTotal());
        pb.setTotalPage(pageInfo.getPages());
        ObjectMapper Mapper =(ObjectMapper) applicationContext.getBean("ObjectMapper");
        resp.setContentType("application/json;charset=utf-8");
        try {
            Mapper.writeValue(resp.getOutputStream(), pb);
        }catch (IOException e){
            System.out.println(e);
        }
        return "informationChanges";
    }



}