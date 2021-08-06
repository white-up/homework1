package Servlet;


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

@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        //2.调用Service查询
        UserMapperImpl userMapper=(UserMapperImpl)applicationContext.getBean("UserMapperImpl");
        List<User> user = userMapper.mapper.findById(Integer.valueOf(id));
        User user1 = user.get(0);
        //3.将user存入request
        request.setAttribute("user", user1);
        //4.转发到update.jsp
        request.getRequestDispatcher("/informationChanges.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}