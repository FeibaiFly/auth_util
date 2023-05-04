package BindWhiteCard;


import java.util.List;


public class WhiteCard {
    private String platformUserId;
    private String hdktStudentId;
    private String cardNum;
    private List<String> tels;
    private String relation;

    public String getPlatformUserId() {
        return platformUserId;
    }

    public void setPlatformUserId(String platformUserId) {
        this.platformUserId = platformUserId;
    }

    public String getHdktStudentId() {
        return hdktStudentId;
    }

    public void setHdktStudentId(String hdktStudentId) {
        this.hdktStudentId = hdktStudentId;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public List<String> getTels() {
        return tels;
    }

    public void setTels(List<String> tels) {
        this.tels = tels;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
