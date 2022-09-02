package org.wlzhj.ucs_admin.dao;

import org.apache.ibatis.annotations.*;
import org.wlzhj.ucs_admin.pojo.User;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user where userName=#{userName} and userPassword=#{userPassword}")
    User login(String userName, String userPassword);

    @Insert("insert into user values (0, #{userName}, #{userPassword}, #{gender}, #{phone}, #{email}, #{avatar}, #{birthday}, #{money}, #{jointime})")
    void add(User user);

    @Select("select * from user")
    List<User> show();

    @Delete("delete from user where id =#{id}")
    void remove(int id);

    @Update("update user set userName=#{userName}, userPassword=#{userPassword}, gender=#{gender}, phone=#{phone},email=#{email}, avatar=#{avatar}, birthday=#{birthday}, money=#{money} where id=#{id}")
    void set(User user);

    @Select("select * from user where id = #{id}")
    User showById(int id);

    @Update("update user set userName=#{userName}, userPassword=#{userPassword}, gender=#{gender}, phone=#{phone},email=#{email}, avatar=#{avatar}, birthday=#{birthday} where id=#{id}")
    void userSetPersonal(User user);
}
