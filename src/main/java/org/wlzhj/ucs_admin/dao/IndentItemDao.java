package org.wlzhj.ucs_admin.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlzhj.ucs_admin.pojo.Cart;
import org.wlzhj.ucs_admin.pojo.Indent_Item;

import java.util.List;

@Mapper
public interface IndentItemDao {
    @Select("select * from indent_item where userId=#{userId}")
    List<Cart> showUserCart(int userId);

    @Select("select * from indent_item where orderId=#{orderId}")
    List<Indent_Item> getIndentItem(int orderId);

    @Insert("insert into indent_item values (0,#{orderId},#{itemId},#{itemName},#{price},#{number},#{picUrl},#{addTime})")
    void addIndentItem(Indent_Item indent_item);

    @Delete("delete from indent_item where id=#{id}")
    void deleteIndentItem(int id);
}
