package org.wlzhj.ucs_admin.dao;

import org.apache.ibatis.annotations.*;
import org.wlzhj.ucs_admin.pojo.Address;

import java.util.List;

@Mapper
public interface AddressDao {
    @Insert("insert into address values (0, #{userId}, #{address}, #{phone}, #{name}, #{check})")
    void addAddress(Address address);

    @Select("select * from address where userId=#{userId}")
    List<Address> showPersonalAllAddress(int userId);

    @Select("select * from address where check=1 and userId=#{userId}")
    Address showCheckAddress(int userId);

    @Delete("delete from address where id=#{id}")
    void deleteAddress(int id);

    @Update("update address set check=#{checkStatus} where userId=#{userId} and id=#{id}")
    void setCheckStatus(boolean checkStatus,int userId,int id);

    @Update("update address set check=2 where check=1 and userId=#{userId}")
    void setDeleteCheck(int userId);
}
