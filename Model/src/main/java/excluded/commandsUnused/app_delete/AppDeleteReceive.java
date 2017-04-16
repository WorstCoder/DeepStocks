
package data.binaryAPI.commandsUnused.app_delete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * App Delete Receive
 * <p>
 * 
 * 
 */
public class AppDeleteReceive implements Serializable, Message
{

    /**
     * Echo request
     * <p>
     * Echo of the request made
     * (Required)
     * 
     */
    @SerializedName("echo_req")
    @Expose
    private Object echoReq;
    /**
     *  1 on success
     * 
     */
    @SerializedName("app_delete")
    @Expose
    private String appDelete;
    /**
     * app_delete
     * (Required)
     * 
     */
    @SerializedName("msg_type")
    @Expose
    private String msgType;
    /**
     * Optional field send in request to map to response, present only when request contains req_id
     * 
     */
    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    private final static long serialVersionUID = 3195413236356210080L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AppDeleteReceive() {
    }

    /**
     * 
     * @param msgType
     * @param echoReq
     * @param appDelete
     * @param reqId
     */
    public AppDeleteReceive(Object echoReq, String appDelete, String msgType, Integer reqId) {
        super();
        this.echoReq = echoReq;
        this.appDelete = appDelete;
        this.msgType = msgType;
        this.reqId = reqId;
    }

    /**
     * Echo request
     * <p>
     * Echo of the request made
     * (Required)
     * 
     */
    public Object getEchoReq() {
        return echoReq;
    }

    /**
     * Echo request
     * <p>
     * Echo of the request made
     * (Required)
     * 
     */
    public void setEchoReq(Object echoReq) {
        this.echoReq = echoReq;
    }

    /**
     *  1 on success
     * 
     */
    public String getAppDelete() {
        return appDelete;
    }

    /**
     *  1 on success
     * 
     */
    public void setAppDelete(String appDelete) {
        this.appDelete = appDelete;
    }

    /**
     * app_delete
     * (Required)
     * 
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * app_delete
     * (Required)
     * 
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * Optional field send in request to map to response, present only when request contains req_id
     * 
     */
    public Integer getReqId() {
        return reqId;
    }

    /**
     * Optional field send in request to map to response, present only when request contains req_id
     * 
     */
    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(echoReq).append(appDelete).append(msgType).append(reqId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AppDeleteReceive) == false) {
            return false;
        }
        AppDeleteReceive rhs = ((AppDeleteReceive) other);
        return new EqualsBuilder().append(echoReq, rhs.echoReq).append(appDelete, rhs.appDelete).append(msgType, rhs.msgType).append(reqId, rhs.reqId).isEquals();
    }

}