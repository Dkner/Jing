
package com.process.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "find_song_by_idResponse", namespace = "http://service.process.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "find_song_by_idResponse", namespace = "http://service.process.com/")
public class Find_song_by_idResponse {

    @XmlElement(name = "return", namespace = "")
    private com.data.vo.Song _return;

    /**
     * 
     * @return
     *     returns Song
     */
    public com.data.vo.Song getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.data.vo.Song _return) {
        this._return = _return;
    }

}
