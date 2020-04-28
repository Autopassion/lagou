import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class User {
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    public static class Configuration {
        private DataSource dataSource;
        private Map<String, MappedStatement> mappedStatementMap = new HashMap<String, MappedStatement>();


    }

    public static class MappedStatement {
        private Integer id;
        private String sql;
        private Class<?> parameterType;
        private Class<?> resultType;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<?> getParameterType() {
            return parameterType;
        }

        public void setParameterType(Class<?> parameterType) {
            this.parameterType = parameterType;
        }

        public Class<?> getResultType() {
            return resultType;
        }

        public void setResultType(Class<?> resultType) {
            this.resultType = resultType;
        }
    }
}
