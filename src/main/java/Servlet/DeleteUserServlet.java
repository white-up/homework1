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

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取id
        String id = req.getParameter("id");
        String error = null;
        if(id!=null&&id.length()>0){
        try {
            ApplicationContext applicationContext = new
                    ClassPathXmlApplicationContext("applicationContext.xml");
            UserMapperImpl userMapper = (UserMapperImpl) applicationContext.getBean("UserMapperImpl");
            userMapper.mapper.deleteById(Integer.valueOf(id));
            userMapper.sqlSession.commit();
        } catch (Exception e) {
            System.out.println(e);
            error = "修改失败";
        }}else {
            error="修改失败:传递参数错误";
        }
        req.setAttribute("error", error);
    }
}