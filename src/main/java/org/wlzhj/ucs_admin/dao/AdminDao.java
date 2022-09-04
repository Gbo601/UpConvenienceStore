package org.wlzhj.ucs_admin.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlzhj.ucs_admin.pojo.Admin;

import java.util.List;

@Mapper
public interface AdminDao {
    @Select("select * from admin where adminName = #{adminName} and adminPassword = #{adminPassword}")
    public Admin adminLogin(String adminName, String adminPassword);

    @Select("select * from admin where id=#{id}")
    public Admin getAdminById(int id);

    @Select("select * from admin")
    public List<Admin> getAllAdmin();


    @Insert("insert into admin values(0,#{adminName},#{adminPassword},#{powerLevel},#{avatar})")
    void add(Admin admin);

}
