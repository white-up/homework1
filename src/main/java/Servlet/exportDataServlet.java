package Servlet;

import com.alibaba.excel.EasyExcel;
import database.UserMapperImpl;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exportDataServlet")
public class exportDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = null;
        try {
            ApplicationContext applicationContext = new
                    ClassPathXmlApplicationContext("applicationContext.xml");
            UserMapperImpl userMapper = (UserMapperImpl) applicationContext.getBean("UserMapperImpl");

            String fileName = "D:\\Java_work\\EmployeeSalaryManagementSystem\\test.xlsx";
            // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
            // 如果这里想使用03 则 传入excelType参数即可
            List<User> list=userMapper.mapper.findAll();
            EasyExcel.write(fileName, User.class).sheet("test").doWrite(list);
        }catch (Exception e){
            System.out.println(e);
            error="导出错误";
        }
        req.setAttribute("error", error);

    }
}