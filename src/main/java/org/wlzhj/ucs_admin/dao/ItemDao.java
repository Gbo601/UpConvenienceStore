package org.wlzhj.ucs_admin.dao;
import org.apache.ibatis.annotations.*;
import org.wlzhj.ucs_admin.pojo.Item;

import java.util.List;

@Mapper
public interface ItemDao {
    @Insert("insert into item values(0,#{itemName},#{category},#{isOnSale},#{price},#{detail},#{picUrl},#{addTime},#{amount})")
    void add(Item item);

    @Delete("delete from item where id =#{id}")
    void remove(int id);

    @Select("select * from item")
    List<Item> show();

    @Select("select * from item where id = #{id}")
    Item showById(int id);

    @Select("select * from item where category=#{category} and isOnSale=1")
    List<Item> showCategory(String category);

    @Select("select * from item where isOnSale=1")
    List<Item> showOnSaleItem();

    @Select("select count(*) from item")
    int showItemSum();

    @Update("update item set itemName=#{itemName}, category=#{category},isOnSale=#{isOnSale},price=#{price},detail=#{detail},addTime=#{addTime},amount=#{amount} where id = #{id}")
    void setNoPic(Item item);

    @Update("update item set itemName=#{itemName}, category=#{category},isOnSale=#{isOnSale},price=#{price},detail=#{detail},picUrl=#{picUrl},addTime=#{addTime},amount=#{amount} where id = #{id}")
    void sethavePic(Item item);

    @Update("update item set amount=#{amount} where id = #{id}")
    void setAmount(int amount,int id);

}
