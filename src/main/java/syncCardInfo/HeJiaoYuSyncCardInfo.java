package syncCardInfo;

import java.util.Objects;

public class HeJiaoYuSyncCardInfo {
    //统一用户id
    private String userId;

    private Integer xskStudentId;

    private Integer userType;

    //设备类型：1,学生证2.0;2,学生证3.0；3.腕表
    private String equipType;
    //RFID卡号
    private String rfid;
    //IMEI号
    private String imei;
    //校园卡插卡手机号
    private String equipPhone;
    //亲情号码名称1
    private String famiNumberName1;
    //亲情号码名称2
    private String famiNumberName2;
    //亲情号码名称3
    private String famiNumberName3;
    //SOS按键号码名称
    private String famiNumberNameSos;
    //亲情号码1
    private String famiNumber1;
    //亲情号码2
    private String famiNumber2;
    //亲情号码3
    private String famiNumber3;
    //SOS按键号码
    private String famiNumberSos;
    //学生实际插卡号码
    private String currentEquipPhone;
    //修改时间
    private String updateTime;

    private Integer isFail;

    private Integer failNum;

    private Integer thirdSource;

    public Integer getThirdSource() {
        return thirdSource;
    }

    public void setThirdSource(Integer thirdSource) {
        this.thirdSource = thirdSource;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getXskStudentId() {
        return xskStudentId;
    }

    public void setXskStudentId(Integer xskStudentId) {
        this.xskStudentId = xskStudentId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getEquipPhone() {
        return equipPhone;
    }

    public void setEquipPhone(String equipPhone) {
        this.equipPhone = equipPhone;
    }

    public String getFamiNumberName1() {
        return famiNumberName1;
    }

    public void setFamiNumberName1(String famiNumberName1) {
        this.famiNumberName1 = famiNumberName1;
    }

    public String getFamiNumberName2() {
        return famiNumberName2;
    }

    public void setFamiNumberName2(String famiNumberName2) {
        this.famiNumberName2 = famiNumberName2;
    }

    public String getFamiNumberName3() {
        return famiNumberName3;
    }

    public void setFamiNumberName3(String famiNumberName3) {
        this.famiNumberName3 = famiNumberName3;
    }

    public String getFamiNumberNameSos() {
        return famiNumberNameSos;
    }

    public void setFamiNumberNameSos(String famiNumberNameSos) {
        this.famiNumberNameSos = famiNumberNameSos;
    }

    public String getFamiNumber1() {
        return famiNumber1;
    }

    public void setFamiNumber1(String famiNumber1) {
        this.famiNumber1 = famiNumber1;
    }

    public String getFamiNumber2() {
        return famiNumber2;
    }

    public void setFamiNumber2(String famiNumber2) {
        this.famiNumber2 = famiNumber2;
    }

    public String getFamiNumber3() {
        return famiNumber3;
    }

    public void setFamiNumber3(String famiNumber3) {
        this.famiNumber3 = famiNumber3;
    }

    public String getFamiNumberSos() {
        return famiNumberSos;
    }

    public void setFamiNumberSos(String famiNumberSos) {
        this.famiNumberSos = famiNumberSos;
    }

    public String getCurrentEquipPhone() {
        return currentEquipPhone;
    }

    public void setCurrentEquipPhone(String currentEquipPhone) {
        this.currentEquipPhone = currentEquipPhone;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsFail() {
        return isFail;
    }

    public void setIsFail(Integer isFail) {
        this.isFail = isFail;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeJiaoYuSyncCardInfo)) return false;
        HeJiaoYuSyncCardInfo that = (HeJiaoYuSyncCardInfo) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
