package org.wlzhj.ucs_admin.dao;
import org.apache.ibatis.annotations.*;
import org.wlzhj.ucs_admin.pojo.Item;

import java.util.List;

@Mapper
public interface ItemDao {
    @Insert("insert into item values(0,#{itemName},#{category},#{isOnSale},#{price},#{detail},#{picUrl},#{addTime},#{amount})")
    void add(Item item);

    @Select("select * from item")
    List<Item> show();

    @Delete("delete from item where id =#{id}")
    void remove(int id);

    @Select("select * from item where id = #{id}")
    Item showById(int id);

    @Update("update item set itemName=#{itemName}, category=#{category},isOnSale=#{isOnSale},price=#{price},detail=#{detail},picUrl=#{picUrl},addTime=#{addTime},amount=#{amount} where id = #{id}")
    void set(Item item);
}
