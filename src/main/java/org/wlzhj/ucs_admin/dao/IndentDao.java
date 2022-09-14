package org.wlzhj.ucs_admin.dao;

import org.apache.ibatis.annotations.*;
import org.wlzhj.ucs_admin.pojo.Indent;

import java.util.List;
@Mapper
public interface IndentDao {
    @Select("select * from indent")
    List<Indent> show();

    @Select("select count(*) from indent where orderStatus=1")
    int showNoDeliverIndentSum();

    @Select("select * from indent where id = #{id}")
    Indent showById(int id);

    @Select("select * from indent where orderSn=#{orderSn}")
    Indent showByOrderSn(String orderSn);

    @Select("select * from indent where userId=#{userId}")
    List<Indent> showUserAllIndent(int userId);

    @Select("select * from indent where userId=#{userId} and orderStatus=#{orderStatus}")
    List<Indent> showUserStatusIndent(int userId,int orderStatus);

    @Select("select * from indent where orderStatus=1")
    List<Indent> showNoDeliverIndent();

    @Delete("delete from indent where id =#{id}")
    void remove(int id);

    @Insert("insert into indent values(0,#{userId},#{orderSn},#{orderStatus},#{phone},#{address},#{message},#{itemPrice},#{payTime},#{addTime},#{refundTime},#{confirmTime})")
    void add(Indent indent);

    @Update("update indent set userId=#{userId},orderSn=#{orderSn},orderStatus=#{orderStatus},phone=#{phone},address=#{address},message=#{message},itemPrice=#{itemPrice},payTime=#{payTime},addTime=#{addTime},refundTime=#{refundTime},confirmTime=#{confirmTime} where id=#{id}")
    void set(Indent indent);

    @Update("update indent set orderStatus=2 where id=#{id}")
    void setHasDeliver(int id);
}
