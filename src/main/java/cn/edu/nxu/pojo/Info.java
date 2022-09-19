package cn.edu.nxu.pojo;

import lombok.Data;

/**
 * 用于封装返回数据
 */
@Data
public class Info {
    public Info(boolean flag,String message){
        this.flag = flag;
        this.message = message;
    }
    public boolean flag;
    public String message;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
