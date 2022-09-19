package cn.edu.nxu.controller;

import cn.edu.nxu.mapper.CasedMapper;
import cn.edu.nxu.pojo.*;
import cn.edu.nxu.server.FileCategoryServer;
import cn.edu.nxu.server.FileListServer;
import cn.edu.nxu.server.RootDirectoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/categoryManager")
public class categoryController {

    @Autowired
    FileCategoryServer fileCategoryServer;
    @Autowired
    RootDirectoryServer rootDirectoryServer;
    @Autowired
    FileListServer fileListServer;
    @Autowired
    CasedMapper casedMapper;
    /**
     * 获取一个目录树
     */

    @CrossOrigin
    @RequestMapping("/getMenuTree")
    public List getMenuTree() {
        List rootCategory = fileCategoryServer.getAllCategory();
        ArrayList rootMenuList = new ArrayList();
        //组装数据为Tree格式
        for (Object root : rootCategory) {//遍历一级目录获取耳机目录
            MenuTree menuTree = new MenuTree();
            FileCategory rootMenu = (FileCategory)root;
            menuTree.setMenuId(rootMenu.getId().toString());
            menuTree.setMenuName(rootMenu.getCategory());
            int categoryId = rootMenu.getId();
            //根据以及目录获取二级目录
            List<FileList> filesList = fileListServer.getFileByCategoryId(categoryId);
//            String child = JSON.toJSON(filesList).toString();
//            System.out.println("child:"+child);
            menuTree.setChildMenu(filesList);
            rootMenuList.add(menuTree);
        }
        return rootMenuList;
    }
    /**
     * 获取所有类别
     * @return
     */
    @RequestMapping("/getAllCategory")
    public List getAllCategory(){

        return fileCategoryServer.getAllCategory();
    }

    /**
     * 根据菜单id获取详细信息
     * @param id
     * @return
     */
    @RequestMapping("/getCategoryById")
    public FileCategory getCategoryById(@RequestParam int id){
        return fileCategoryServer.getCategoryById(id);
    }

    /**
     * 根据id修改数据集类别信息
     * @param fileCategory
     * @return
     */
    @RequestMapping("/updateCategoryById")
    public Info updateCategoryById(@RequestBody FileCategory fileCategory){

        int flag =  fileCategoryServer.updateCategoryById(fileCategory);
        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"更新失败！");
        }
    }

    /**
     * 添加数据集类别
     * @param fileCategory
     * @return
     */
    @RequestMapping("/addCategory")
    public Info addCategory(@RequestBody FileCategory  fileCategory){
        int flag = fileCategoryServer.addCategory(fileCategory);
        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"添加失败！");
        }
    }

    /**
     * 删除数据集类别
     * 同时删除类别下的所有数据集
     * @param categoryId
     * @return
     */
    @RequestMapping("/delCategoryById")
    public Info delCategoryById(@RequestParam int  categoryId){
        //TODO 删除类别下的数据集
        int flag = fileCategoryServer.delCategoryById(categoryId);
        if (flag!=0){
            return new Info(true, "Success");
        }else{
            return new Info(false,"删除失败！");
        }
    }

    /**
     * 封装级联数据，返回到前端
     * @return
     */
    @CrossOrigin
    @RequestMapping("/getCasedList")
    public List getCasedList(){

        ArrayList modelist = new ArrayList();
        for (Mode item : casedMapper.findAll()) {
            casedList mode = new casedList();
            mode.setLabel(item.getMode());
            mode.setValue(item.getModename());
            modelist.add(mode);
        }
        List<FileCategory> allCategory = fileCategoryServer.getAllCategory();
        ArrayList casedList = new ArrayList();
        for(FileCategory item : allCategory){
            casedList mode = new casedList();
            mode.setLabel(item.getCategory());
            mode.setValue(item.getId().toString());
            mode.setChildren(modelist);
            casedList.add(mode);
        }
        return casedList;
    }

//    /**
//     * 根据根目录id获取二级菜单
//     * @param id
//     * @return
//     */
//    @RequestMapping("/getCategoryByRootDirectoryId")
//    public List<FileCategory> getCategoryByRootDirectoryId(@RequestParam int  id){
//
//        return  fileCategoryServer.getCategoryByRootDirectoryId(id);
//    }
//
//    @RequestMapping("/getAllRootCategory")
//    @ResponseBody
//    public List getAllRootCategory(){
//
//        return rootDirectoryServer.getAllRootCategory();
//    }
//    @RequestMapping("/getRootCategoryById")
//    public RootDirectory getRootCategoryById(@RequestParam int id){
//
//        return rootDirectoryServer.getRootCategoryById(id);
//    }
//    @RequestMapping("/updateRootCategoryById")
//    public Info updateRootCategoryById(@RequestParam RootDirectory rootDirectory){
//
//        int flag = rootDirectoryServer.updateRootCategoryById(rootDirectory);
//        if (flag!=0){
//            return new Info(flag, "Success");
//        }else{
//            return new Info(flag,"Flase");
//        }
//    }
//    @RequestMapping("/addRootCategory")
//    public Info addRootCategory(@RequestParam RootDirectory  rootDirectory){
//
//        int flag =  rootDirectoryServer.addRootCategory(rootDirectory);
//        if (flag!=0){
//            return new Info(flag, "Success");
//        }else{
//            return new Info(flag,"Flase");
//        }
//    }
//    @RequestMapping("/delRootCategory")
//    public Info delRootCategoryById(@RequestParam int  id){
//
//        return  rootDirectoryServer.delRootCategoryById(id);
//
//    }
}
