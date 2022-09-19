package cn.edu.nxu.mapper;

import cn.edu.nxu.pojo.FileCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileCategory record);

    int insertSelective(FileCategory record);

    FileCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileCategory record);

    int updateByPrimaryKey(FileCategory record);
    @Select("select * from filecategory order by reserve1")
    List<FileCategory> findAll();

    @Select("select * from filecategory where reserve1 = #{id}")
    List<FileCategory> getCategoryByRootDirectoryId(Integer id);
}