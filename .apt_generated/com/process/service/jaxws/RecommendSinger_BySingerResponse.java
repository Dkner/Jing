
package com.process.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RecommendSinger_BySingerResponse", namespace = "http://service.process.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecommendSinger_BySingerResponse", namespace = "http://service.process.com/")
public class RecommendSinger_BySingerResponse {

    @XmlElement(name = "return", namespace = "")
    private List _return;

    /**
     * 
     * @return
     *     returns List
     */
    public List getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List _return) {
        this._return = _return;
    }

}
