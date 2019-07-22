package bean;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "image")
public class ImageBean implements Serializable{
    @Column(name = "id")
    private Integer id;
    @Column(name = "path")
    private String path;
    @Column(name = "create_time")
    private Long createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


}
