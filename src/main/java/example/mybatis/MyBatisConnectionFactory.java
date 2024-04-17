package example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatisConnectionFactory {
    private static SqlSessionFactory sqlSessionFactory;

    public static synchronized SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            initializeSqlSessionFactory();
        }
        return sqlSessionFactory;
    }

    private static void initializeSqlSessionFactory() {
        try {
            String resource = "SqlMapConfig.xml";
            // 설정 가져오기. 파일에 대한 스트림 열기
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다!" + e.getMessage());
            e.printStackTrace();
        }
    }
}
