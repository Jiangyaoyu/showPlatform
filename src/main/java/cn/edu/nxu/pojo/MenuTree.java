package cn.edu.nxu.pojo;

import lombok.Data;

import java.util.List;

/**
 * 用于封装菜单栏数据
 */
@Data
public class MenuTree {
    private String menuId;//编号
    private String parentId;//父级编号
    private String menuName;//名称
    private String menuPath;//路径
    private String menuIcon;//图标
    private List childMenu;//子菜单项


}
