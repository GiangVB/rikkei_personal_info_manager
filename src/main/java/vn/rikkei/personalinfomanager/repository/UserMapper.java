package vn.rikkei.personalinfomanager.repository;

import org.apache.ibatis.annotations.*;
import vn.rikkei.personalinfomanager.model.entity.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE userId = #{userId}")
    User findById(@Param("userId") Integer userId);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(@Param("email") String email);

    @Insert("INSERT INTO users(userId, email, passcode, fullName, sex, birthday, phoneNumber, address, idNumber, avatar) " +
            "VALUES (#{userId}, #{email}, #{passcode}, #{fullName}, #{sex}, #{birthday}, #{phoneNumber}, #{address}, #{idNumber}, #{avatar})")
    void addUser(User user);

    @Update("UPDATE users SET " +
            "fullName = #{fullName}, " +
            "sex = #{sex}, " +
            "birthday = #{birthday}, " +
            "phoneNumber = #{phoneNumber}, " +
            "address = #{address}, " +
            "idNumber = #{idNumber}, " +
            "avatar = #{avatar} " +
            "WHERE userId = #{userId}")
    void updateUser(User user);

    @Update("UPDATE users SET passcode = #{passcode} WHERE userId = #{userId}")
    void updateUserPassword(User user);
}
