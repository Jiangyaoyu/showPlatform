package cn.edu.nxu.mapper;

import cn.edu.nxu.pojo.CodeList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CodeListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CodeList record);

    int insertSelective(CodeList record);

    CodeList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CodeList record);

    int updateByPrimaryKey(CodeList record);

    @Select("select * from codelist")
    List findAll();

    @Select("select * from codelist where fileid = #{id}")
    List getCodeByFileId(int id);
}