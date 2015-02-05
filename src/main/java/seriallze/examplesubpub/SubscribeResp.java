package seriallze.examplesubpub;

import java.io.Serializable;

/**
 * Created by jianying.wcj on 2015/2/2 0002.
 */
public class SubscribeResp implements Serializable{

    private static final long serialVersionUID = 1L;

    private int subReqID;

    private int respCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    private String desc;

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqID=" + subReqID +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
