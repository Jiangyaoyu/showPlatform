package cn.edu.nxu.server;

import cn.edu.nxu.mapper.RootDirectoryMapper;
import cn.edu.nxu.pojo.Info;
import cn.edu.nxu.pojo.RootDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RootDirectoryServer {

    @Autowired
    public RootDirectoryMapper rootDirectoryMapper;

    @Autowired
    public FileCategoryServer fileCategoryServer;

    public List getAllRootCategory(){
        return rootDirectoryMapper.findAll();
    }


    public RootDirectory getRootCategoryById(int id){
        return rootDirectoryMapper.selectByPrimaryKey(id);
    }

    public int updateRootCategoryById(RootDirectory rootDirectory){
        int flag =   rootDirectoryMapper.updateByPrimaryKey(rootDirectory);
        return flag;
    }

    public int addRootCategory(RootDirectory rootDirectory){

        return rootDirectoryMapper.insert(rootDirectory);
    }

    @Transactional
    public Info delRootCategoryById(int id){
        int flag = rootDirectoryMapper.deleteByPrimaryKey(id);

        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"上传失败！");
        }
    }

}
