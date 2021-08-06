package database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class changeRecordMapperImpl {
    public InputStream resourceAsStream;
    public SqlSessionFactory sqlSessionFactory;//sqlSession工厂对象
    public SqlSession sqlSession;//sqlSession对象
    public changeRecordMapper mapper;
    public changeRecordMapperImpl() {
        try {
            resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(changeRecordMapper.class);
    }
    public changeRecordMapper getMapper() {
        return mapper;
    }
}