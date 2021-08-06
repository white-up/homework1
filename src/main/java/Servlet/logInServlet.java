package Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import util.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/logInServlet")
public class logInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String checkInput = request.getParameter("check");//获取用户输入验证码
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        ResultInfo info = (ResultInfo) applicationContext.getBean("ResultInfo"); //回执信息
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkSystem = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if (checkSystem == null || !checkSystem.equalsIgnoreCase(checkInput)) {//验证码错误
            info.setFlag(false);//登录失败
            info.setErrorMsg("验证码错误!");//设置登录错误信息
        } else {
            Map<String, String[]> map = request.getParameterMap();//1.获取数据
            if ("abcde123456".equals(request.getParameter("username")) && "abcde123456".equals(request.getParameter("password"))) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("账号或密码错误!");
            }
        }
        //将info对象序列化为json
        ObjectMapper mapper = (ObjectMapper) applicationContext.getBean("ObjectMapper");
        String json = mapper.writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}