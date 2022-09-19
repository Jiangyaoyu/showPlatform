package cn.edu.nxu.mapper;

import cn.edu.nxu.pojo.Mode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CasedMapper {

    @Select("select * from mode")
    List<Mode> findAll();

    @Select("select * from mode where id = #{id}")
    List<Mode> getModeById(int id);
}
