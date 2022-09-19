package cn.edu.nxu.pojo;

import lombok.Data;


import java.util.ArrayList;

/**
 * 用于接受前端添加新数据时的类
 */
@Data
public class AddItem {
    private String name;//未使用
    private String[] category;
    private String desc;
    private ArrayList<DataFormat> fileList;
}
