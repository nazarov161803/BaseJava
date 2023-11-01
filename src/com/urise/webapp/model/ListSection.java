package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)  //это надо для сериализатора jaxb тк он работает с сеттрами. в объектам шже нет сетера, он не сможет преобразовать в xml
public class ListSection extends Section {

    public ListSection() {
    }

    private static final long serialVersionUID = 1L;
    private List<String> items;

    public ListSection(String... items) {
        this(Arrays.asList(items)); //varargs для удобства
    }

    public ListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);

    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}

