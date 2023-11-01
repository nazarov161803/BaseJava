package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
@XmlAccessorType(XmlAccessType.FIELD)  //это надо для сериализатора jaxb тк он работает с сеттрами. в объектам шже нет сетера, он не сможет преобразовать в xml
public abstract class Section implements Serializable {

    public Section() {
    }

    private static final long serialVersionUID = 1L;
}
