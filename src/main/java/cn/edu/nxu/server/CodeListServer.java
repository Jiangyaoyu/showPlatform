package cn.edu.nxu.server;


import cn.edu.nxu.mapper.CodeListMapper;
import cn.edu.nxu.pojo.CodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeListServer {
    @Autowired
    public CodeListMapper codeListMapper;

    public List getAllCode(){
        return codeListMapper.findAll();
    }

    public CodeList getCodeById(int id){
        return codeListMapper.selectByPrimaryKey(id);
    }

    public int updateFileById(CodeList codeList){
        return codeListMapper.updateByPrimaryKey(codeList);
    }

    public int addFile(CodeList codeList){
        return codeListMapper.insert(codeList);
    }

    public int delCodeById(int id){
        return codeListMapper.deleteByPrimaryKey(id);
    }

    public List getCodeByFileId(int id){
        return codeListMapper.getCodeByFileId(id);
    }
}
