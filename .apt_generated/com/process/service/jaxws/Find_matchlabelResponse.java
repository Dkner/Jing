
package com.process.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "find_matchlabelResponse", namespace = "http://service.process.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "find_matchlabelResponse", namespace = "http://service.process.com/")
public class Find_matchlabelResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.data.vo.Label> _return;

    /**
     * 
     * @return
     *     returns List<Label>
     */
    public List<com.data.vo.Label> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.data.vo.Label> _return) {
        this._return = _return;
    }

}
