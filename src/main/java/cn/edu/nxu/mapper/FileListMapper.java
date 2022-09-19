package cn.edu.nxu.mapper;

import cn.edu.nxu.pojo.FileList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileList record);

    int insertSelective(FileList record);

    FileList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileList record);

    int updateByPrimaryKey(FileList record);

    @Select("select * from filelist")
    List<FileList> findAll();

    @Select("select * from filelist where catagoryid = #{id}")
    List<FileList> getFileByCategoryId(int id);
}