package cn.edu.nxu.mapper;

import cn.edu.nxu.pojo.RootDirectory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RootDirectoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RootDirectory record);

    int insertSelective(RootDirectory record);

    RootDirectory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RootDirectory record);

    int updateByPrimaryKey(RootDirectory record);

    @Select("select * from rootdirectory")
    List<RootDirectory> findAll();


}