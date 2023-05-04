import java.io.Serializable;
import java.util.Date;

/**
 * 密钥信息表(IotVideoSecret)实体类
 *
 * @author makejava
 * @since 2021-02-02 09:53:53
 */
public class IotVideoSecret implements Serializable {
    private static final long serialVersionUID = -84993490167513533L;
    /**
    * APP序号
    */
    private Long id;
    /**
    * 第三方appid加密用，现在用的是申网的appid
    */
    private String appId;
    /**
    * 私钥，现在用的是申网的APP 证书
    */
    private String secret;
    /**
    * 类型
    */
    private String type;
    /**
    * 名称
    */
    private String name;
    /**
    * 状态
    */
    private String status;
    /**
    * 创建人
    */
    private String creator;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新人
    */
    private String updator;
    /**
    * 最后更新时间
    */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}