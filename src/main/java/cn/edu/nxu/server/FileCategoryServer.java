package cn.edu.nxu.server;

import cn.edu.nxu.mapper.FileCategoryMapper;
import cn.edu.nxu.pojo.FileCategory;
import cn.edu.nxu.pojo.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileCategoryServer {
    @Autowired
    public FileCategoryMapper fileCategoryMapper;
    @Autowired
    public FileListServer fileListServer;
    public List getAllCategory(){
        return fileCategoryMapper.findAll();
    }


    public FileCategory getCategoryById(int id){
        return fileCategoryMapper.selectByPrimaryKey(id);
    }

    public int updateCategoryById(FileCategory fileCategory){
      int flag =   fileCategoryMapper.updateByPrimaryKey(fileCategory);
      return flag;
    }

    public int addCategory(FileCategory fileCategory){

        return fileCategoryMapper.insert(fileCategory);
    }
    @Transactional
    public int delCategoryById(int id){
        List<FileList> list =  fileListServer.getFileByCategoryId(id);
        if(list.size()>0){
            for(int index=0;index<list.size();index++){
                fileListServer.delFileById(list.get(index).getId());
            }
        }
        return fileCategoryMapper.deleteByPrimaryKey(id);
    }

    public List getCategoryByRootDirectoryId(int id){
        return fileCategoryMapper.getCategoryByRootDirectoryId(id);
    }
}
