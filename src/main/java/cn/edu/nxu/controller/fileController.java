package cn.edu.nxu.controller;

import cn.edu.nxu.pojo.*;
import cn.edu.nxu.server.FileListServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fileManager")
public class fileController {

    @Autowired
    public FileListServer fileListServer;

    /**
     * 获取所有数据集
     * @return
     */
    @RequestMapping("/getAllFile")
    @ResponseBody
    public List<FileList> getAllfile(){
        return fileListServer.getAllFile();
    }

    /**
     * 根据数据集id获取数据集
     * @param fileId
     * @return
     */
    @RequestMapping("/getFileById")
    public FileList getFileById(@RequestParam int fileId){

        return fileListServer.getFileById(fileId);
    }

    /**
     * 根据数据集id更新数据集信息
     * @param fileList
     * @return
     */
    @RequestMapping("/updateFileById")
    public Info updateFileById(@RequestBody FileList fileList){

        int flag =  fileListServer.updateFileById(fileList);
        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"上传失败！");
        }
    }

    /**
     * 添加数据集
     * @param fileList
     * @return
     */
    @RequestMapping("/addFile")
    public Info addFile(@RequestBody FileList  fileList){

        int flag = fileListServer.addFile(fileList);
        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"上传失败！");
        }
    }

    /**
     * 根据数据集id删除数据集
     * @param fileId
     * @return
     */
    @RequestMapping("/delFileById")
    public Info delFileById(@RequestParam int  fileId){
        int flag = fileListServer.delFileById(fileId);
        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"上传失败！");
        }
    }

    /**
     * 根据数据集类别获取数据集集合
     * @param categoryId
     * @return
     */
    @RequestMapping("/getFileByCategoryId")
    public List<FileList> getFileByCategoryId(@RequestParam int  categoryId){

        return fileListServer.getFileByCategoryId(categoryId);
    }

    /**
     * 数据集分页展示
     */
    @CrossOrigin
    @RequestMapping("/getDirectory")
    @ResponseBody
    public PageInfo getListByPageSize(@RequestParam String filePath, @RequestParam int pageSize, @RequestParam int currentPage) throws FileNotFoundException {
        System.out.println("fileurl:"+filePath+",pageSize:"+pageSize+",currentPage:"+currentPage);

        String basePath = "E:\\SourceCode\\showPlatform\\target\\classes\\static\\FileRepo";
        File dir = new File(basePath+filePath.trim());
        File[] files = dir.listFiles();
        try {
            if(files.length<=0){//路径下无文件
                return new PageInfo(currentPage,pageSize,0,null);
            }
        }catch (Exception e){
            return new PageInfo(currentPage,pageSize,0,null);
        }
        Map map= new HashMap();
        int  totalCount = files.length;//总数量
        int totalPageCount=0;
        if(totalCount%pageSize==0){
            totalPageCount =totalCount/pageSize;
        }else{
            totalPageCount =totalCount/pageSize+1;
        }
        if(totalCount < pageSize){//数据量小于页大小
            for(File file : files){
                if(file.isFile()){
                    map.put(file.getName(),true);//如果当前文件类型是文件，则存入map中，并标识为true
                }else{
                    map.put(file.getName(),false);//如果当前文件类型是目录则存入map中，并标识为false
                }
            }
        } else if(totalPageCount>currentPage) {//如果不是最后一页
            for (int index = (currentPage-1) * pageSize; index < currentPage * pageSize; index++) {
                if (files[index].isFile()) {
                    map.put(files[index].getName(), true);//如果当前文件类型是文件，则存入map中，并标识为true
                } else {
                    map.put(files[index].getName(), false);//如果当前文件类型是目录则存入map中，并标识为false
                }
            }
        } else if (totalPageCount==currentPage) {//如果是最后一页
            for (int index = (currentPage-1) * pageSize; index < totalCount; index++) {
                if (files[index].isFile()) {
                    map.put(files[index].getName(), true);//如果当前文件类型是文件，则存入map中，并标识为true
                } else {
                    map.put(files[index].getName(), false);//如果当前文件类型是目录则存入map中，并标识为false
                }
            }
        }
        return  new PageInfo(currentPage,pageSize,totalCount,map);
    }

    /**
     * 显示单个文件
     */
    @RequestMapping("/showFile")
    public void showFile(@RequestParam String fileUrl){
        System.out.println("显示文件");
    }

    /**
     * 添加一条文件数据
     */
    @CrossOrigin
    @RequestMapping("/addData")
    public Info addItemData(@RequestBody AddItem addItem){
        System.out.println(addItem);
        for(DataFormat df : addItem.getFileList()){
            FileList fileList = new FileList();
            fileList.setFilename(df.getName());
            fileList.setCatagoryid(Integer.parseInt(addItem.getCategory()[0]));
            fileList.setFileUrl(df.getUrl());
            fileList.setReserve2(addItem.getCategory()[1]);
            int flag = fileListServer.addFile(fileList);
            System.out.println("flag:"+flag);
        }

        return new Info(true,"上传成功");
    }

}
