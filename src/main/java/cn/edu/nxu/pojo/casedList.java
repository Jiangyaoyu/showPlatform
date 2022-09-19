package cn.edu.nxu.pojo;

import lombok.Data;

import java.util.ArrayList;

/**
 * 用于封装级联菜单数据的类
 */
@Data
public class casedList {
    private String label;
    private String value;
    private ArrayList children;
}
