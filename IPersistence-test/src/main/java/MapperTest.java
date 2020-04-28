import com.bigfang.config.XMLConfigBuilder;
import com.bigfang.io.Resource;
import com.bigfang.pojo.Configuration;
import com.bigfang.sqlSession.DefaultSqlSessionFactory;
import com.bigfang.sqlSession.SqlSession;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * test mapper
 */
public class MapperTest {
    UserMapper userMapper;

    @Before
    public void init() throws DocumentException, PropertyVetoException, ClassNotFoundException {
        InputStream sqlMapResource = Resource.getResourceAsStream("sqlMapConfig.xml");
        Configuration configuration = new Configuration();
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configuration);
        xmlConfigBuilder.parseConfiguration(sqlMapResource);
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void  testSelect(){
        List<User> users = userMapper.selectList();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("cassy");
        userMapper.insertOne(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(10);
        user.setUsername("test-tom");
        userMapper.updateOne(user);
    }

    @Test
    public void testDelete(){
        List<User> users = userMapper.selectList();
        User user = users.get(1);
        userMapper.deleteOne(user);
    }







}
