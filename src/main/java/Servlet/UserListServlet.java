package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import database.UserMapperImpl;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    /**
     * 获得数据
     */
    public List<User> findByAnything(String conditionName, String conditionVal) throws IOException {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        //2.调用Service查询
        UserMapperImpl userMapper=(UserMapperImpl)applicationContext.getBean("UserMapperImpl");
        if ("null".equals(conditionName)) {
            return userMapper.mapper.findAll();
        } else if("byGender".equalsIgnoreCase(conditionName)){
        return userMapper.mapper.findByGender(conditionVal);
        }else if("byId".equalsIgnoreCase(conditionName)){
            System.out.println(Integer.valueOf(conditionVal));
            return userMapper.mapper.findById(Integer.valueOf(conditionVal));
        }else if("byName".equalsIgnoreCase(conditionName)){
            return userMapper.mapper.findByName("%"+conditionVal+"%");
        }
        return null;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String conditionName = URLDecoder.decode(request.getParameter("conditionName"), "UTF-8");
        String conditionVal = URLDecoder.decode(request.getParameter("conditionVal"), "UTF-8");
        String currentPageStr = request.getParameter("currentPage");
        //处理参数
        int currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        //分页实现
        PageHelper.startPage(currentPage, 5);
        //执行sql语句
        List<User> userList = findByAnything(conditionName, conditionVal);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        //PageBean对象
        PageBean<User> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setList(userList);
        pb.setTotalCount((int) pageInfo.getTotal());
        pb.setTotalPage(pageInfo.getPages());

        ObjectMapper Mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        Mapper.writeValue(response.getOutputStream(), pb);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }



}