package cn.edu.nxu.pojo;

import lombok.Data;

@Data
public class RootDirectory {
    private Integer id;

    private String rootdirectory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRootdirectory() {
        return rootdirectory;
    }

    public void setRootdirectory(String rootdirectory) {
        this.rootdirectory = rootdirectory;
    }
}