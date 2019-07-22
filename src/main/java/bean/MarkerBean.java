package bean;


import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "marker")
public class MarkerBean implements Serializable {
    /**
     *
     */
    @Column(name = "id")
    private Long id;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;
    /**
     * 标注横坐标
     */
    @Column(name = "pointX")
    private Double pointX;
    /**
     * 标注纵坐标
     */
    @Column(name = "pointY")
    private Double pointY;
    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Double getPointX() {
        return pointX;
    }

    public void setPointX(Double pointX) {
        this.pointX = pointX;
    }

    public Double getPointY() {
        return pointY;
    }

    public void setPointY(Double pointY) {
        this.pointY = pointY;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MarkerBean() {
    }
}
