package cn.edu.nxu.pojo;

import lombok.Data;

/**
 * 用于封装数据库中Mode表
 */
@Data
public class Mode {
    private int id;
    private String modename;
    private String reserve1;
    private String reserve2;
    private String mode;
}
