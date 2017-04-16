
package data.binaryAPI.commandsUnused.connect_del;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * Social Connect Del Send
 * <p>
 * Delete existing social connect
 * 
 */
public class ConnectDelSend implements Serializable
{

    /**
     * Must be 1
     * 
     */
    @SerializedName("connect_del")
    @Expose
    private Integer connectDel;
    /**
     * provider, eg: google, taken from connect_list
     * 
     */
    @SerializedName("provider")
    @Expose
    private String provider;
    /**
     * Optional field, used to pass data through the websocket, which may be retrieved via the echo_req output field.
     * 
     */
    @SerializedName("passthrough")
    @Expose
    private Passthrough passthrough;
    /**
     * Optional field to map request to response
     * 
     */
    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    private final static long serialVersionUID = -1046839078151914042L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConnectDelSend() {
    }

    /**
     * 
     * @param connectDel
     * @param provider
     * @param passthrough
     * @param reqId
     */
    public ConnectDelSend(Integer connectDel, String provider, Passthrough passthrough, Integer reqId) {
        super();
        this.connectDel = connectDel;
        this.provider = provider;
        this.passthrough = passthrough;
        this.reqId = reqId;
    }

    /**
     * Must be 1
     * 
     */
    public Integer getConnectDel() {
        return connectDel;
    }

    /**
     * Must be 1
     * 
     */
    public void setConnectDel(Integer connectDel) {
        this.connectDel = connectDel;
    }

    /**
     * provider, eg: google, taken from connect_list
     * 
     */
    public String getProvider() {
        return provider;
    }

    /**
     * provider, eg: google, taken from connect_list
     * 
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * Optional field, used to pass data through the websocket, which may be retrieved via the echo_req output field.
     * 
     */
    public Passthrough getPassthrough() {
        return passthrough;
    }

    /**
     * Optional field, used to pass data through the websocket, which may be retrieved via the echo_req output field.
     * 
     */
    public void setPassthrough(Passthrough passthrough) {
        this.passthrough = passthrough;
    }

    /**
     * Optional field to map request to response
     * 
     */
    public Integer getReqId() {
        return reqId;
    }

    /**
     * Optional field to map request to response
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
        return new HashCodeBuilder().append(connectDel).append(provider).append(passthrough).append(reqId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConnectDelSend) == false) {
            return false;
        }
        ConnectDelSend rhs = ((ConnectDelSend) other);
        return new EqualsBuilder().append(connectDel, rhs.connectDel).append(provider, rhs.provider).append(passthrough, rhs.passthrough).append(reqId, rhs.reqId).isEquals();
    }

}