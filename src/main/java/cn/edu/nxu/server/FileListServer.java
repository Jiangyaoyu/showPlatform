package cn.edu.nxu.server;

import cn.edu.nxu.mapper.FileListMapper;
import cn.edu.nxu.pojo.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileListServer {

    @Autowired
    public FileListMapper fileListMapper;

    public List getAllFile(){
        return fileListMapper.findAll();
    }

    public FileList getFileById(int id){
        return fileListMapper.selectByPrimaryKey(id);
    }

    public int updateFileById(FileList fileList){
        return fileListMapper.updateByPrimaryKey(fileList);
    }

    public int addFile(FileList fileList){
        return fileListMapper.insert(fileList);
    }

    public int delFileById(int id){
        return fileListMapper.deleteByPrimaryKey(id);
    }

    public List getFileByCategoryId(int id){
        return fileListMapper.getFileByCategoryId(id);
    }
}
