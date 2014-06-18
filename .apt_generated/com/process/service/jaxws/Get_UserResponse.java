
package com.process.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "get_UserResponse", namespace = "http://service.process.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "get_UserResponse", namespace = "http://service.process.com/")
public class Get_UserResponse {

    @XmlElement(name = "return", namespace = "")
    private com.data.vo.User _return;

    /**
     * 
     * @return
     *     returns User
     */
    public com.data.vo.User getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.data.vo.User _return) {
        this._return = _return;
    }

}
