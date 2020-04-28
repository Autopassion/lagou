

import java.util.List;

public interface UserMapper {
    List<User> selectList();

    User selectOne(int User);

    Boolean updateOne(User user);

    void deleteOne(User user);

    Integer insertOne(User user);
}
