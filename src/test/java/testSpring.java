import database.UserMapper;
import database.UserMapperImpl;
import database.changeRecordMapperImpl;
import entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class testSpring {
    @Test
    public void testImpl(){
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImpl userMapper = (UserMapperImpl) applicationContext.getBean("UserMapperImpl");
        UserMapper mapper=userMapper.mapper;
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }


}