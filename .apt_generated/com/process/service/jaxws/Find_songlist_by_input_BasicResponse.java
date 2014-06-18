
package com.process.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "find_songlist_by_input_BasicResponse", namespace = "http://service.process.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "find_songlist_by_input_BasicResponse", namespace = "http://service.process.com/")
public class Find_songlist_by_input_BasicResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.data.vo.Song> _return;

    /**
     * 
     * @return
     *     returns List<Song>
     */
    public List<com.data.vo.Song> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.data.vo.Song> _return) {
        this._return = _return;
    }

}
