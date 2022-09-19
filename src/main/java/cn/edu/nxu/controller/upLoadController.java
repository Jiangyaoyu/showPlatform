package cn.edu.nxu.controller;

import cn.edu.nxu.pojo.DataFormat;
import cn.edu.nxu.pojo.Info;
import cn.edu.nxu.utils.DeleteFileUtil;
import cn.edu.nxu.utils.ZipUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(("/uploadFile"))
public class upLoadController {
    public static String basePath= "E:\\SourceCode\\showPlatform\\target\\classes\\static\\FileRepo";
    private static int BUFFER = 1024;
    @CrossOrigin
    @PostMapping("/upload")
    public Info upload(@RequestParam("file")MultipartFile file){
        return saveFile(file);
    }
    @CrossOrigin
    @PostMapping("/multiUpload")
    public ArrayList multiUpload(@RequestParam("file")MultipartFile[] files) throws IOException {
        //文件上传的地址
//        String path = ResourceUtils.getURL("classpath:").getPath()+"static/FileRepo";
//        String basePath = path.replace('/', '\\').substring(1,path.length());

        ArrayList list = new ArrayList();
        if (files == null || files.length == 0) {
            return null;
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            //判断该文件名是否已经存在

            DataFormat dataFormat = new DataFormat();
            String filePath="";
            if(file.getOriginalFilename().endsWith(".rar") || file.getOriginalFilename().endsWith(".zip") || file.getOriginalFilename().endsWith(".7z")){
                 filePath = basePath + "\\tmp/" + file.getOriginalFilename();
            }else{
                 filePath = basePath + "/" + file.getOriginalFilename();
            }
            String root = file.getOriginalFilename().split("/")[0];
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            if(file.getOriginalFilename().endsWith(".rar") || file.getOriginalFilename().endsWith(".zip") || file.getOriginalFilename().endsWith(".7z")){
                //解压文件
                String newFilePath = basePath + "\\" + root.substring(0,root.lastIndexOf(".")).trim();
                makeDir(newFilePath+'/');
                System.out.println("filePath:"+filePath);
                System.out.println("new FilePath:"+newFilePath+"\\");
                ZipUtil.unZip(filePath, newFilePath+"\\");
                dataFormat.setName(root.substring(0,root.lastIndexOf(".")));
                dataFormat.setUrl("/"+root.substring(0,root.lastIndexOf(".")));
                list.add(dataFormat);
                //删除缓存文件
                System.out.println("deleteFilePath:"+filePath);

                DeleteFileUtil.delete(filePath);
            }else{
                dataFormat.setName(root.substring(0,root.lastIndexOf(".")));
                dataFormat.setUrl(filePath);
                list.add(dataFormat);
            }
        }
        return list;
    }

    // 删除ArrayList中重复元素，保持顺序
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        return list;
    }
    private Info saveFile(MultipartFile file){
        if (file.isEmpty()){
            return new Info(false,"未选择文件");
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = "C:\\Users\\JYY\\Desktop\\showPlatform\\src\\main\\resources\\static\\FileRepo\\";
        File temp = new File(filePath);
        if (!temp.exists()){
            temp.mkdirs();
        }

        File localFile = new File(filePath+filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return new Info(false,"未选择文件");
        }

        return new Info(true,filePath+filename);
    }

    /**
     * 确保目录存在，不存在则创建
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //request.getSession().getServletContext().getRealPath("static");
//        String serverpath = ResourceUtils.getURL("static").getPath();
//        System.out.println(serverpath);
//        String zipFilePath = "F:\\FileRepo\\tmp\\cpm17.zip";
//        String desDirectory = "F:\\FileRepo\\";
//        unzip(zipFilePath, desDirectory);
        DeleteFileUtil.deleteFile("F:\\\\FileRepo\\\\tmp\\\\cpm17.zip");
    }
}
