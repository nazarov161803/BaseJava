package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)  //это надо для сериализатора jaxb тк он работает с сеттрами. в объектам шже нет сетера, он не сможет преобразовать в xml
public class TextSection extends Section {

    public TextSection() {
    }

    private static final long serialVersionUID = 1L;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    public String getContent() {
        return content;
    }

    public TextSection(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
