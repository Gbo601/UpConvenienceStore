package org.wlzhj.ucs_admin.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlzhj.ucs_admin.pojo.Cart;

import java.util.List;

@Mapper
public interface CartDao {
    @Select("select * from cart where userId=#{userId}")
    List<Cart> showUserCart(int userId);

    @Insert("insert into cart values (0,#{userId},#{itemId},#{itemName},#{price},#{number},#{picUrl},#{addTime},#{checked})")
    void addCart(Cart cart);

    @Delete("delete from cart where id=#{id}")
    void deleteCart(int id);

    @Delete("delete from cart where userId=#{userId}")
    void deleteUserAllCart(int userId);
}
