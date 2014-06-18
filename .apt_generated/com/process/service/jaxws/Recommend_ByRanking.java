
package com.process.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Recommend_ByRanking", namespace = "http://service.process.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recommend_ByRanking", namespace = "http://service.process.com/")
public class Recommend_ByRanking {

    @XmlElement(name = "arg0", namespace = "")
    private com.process.model.Page arg0;

    /**
     * 
     * @return
     *     returns Page
     */
    public com.process.model.Page getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.process.model.Page arg0) {
        this.arg0 = arg0;
    }

}
