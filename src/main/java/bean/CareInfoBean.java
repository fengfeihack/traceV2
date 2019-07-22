package bean;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "care_info")
public class CareInfoBean implements Serializable{
@Column(name = "id")
private Long id;
@Column(name = "careID")
private Long careID;
@Column(name = "beCareID")
private Long beCareID;
@Column(name = "careTime")
private Long careTime;
@Column(name = "remark")
private String remark;

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getCareID() {
    return careID;
}

public void setCareID(Long careID) {
    this.careID = careID;
}

    public Long getBeCareID() {
        return beCareID;
    }

    public void setBeCareID(Long beCareID) {
        this.beCareID = beCareID;
    }

    public Long getCareTime() {
    return careTime;
}

public void setCareTime(Long careTime) {
    this.careTime = careTime;
}

public String getRemark() {
    return remark;
}

public void setRemark(String remark) {
    this.remark = remark;
}
}
