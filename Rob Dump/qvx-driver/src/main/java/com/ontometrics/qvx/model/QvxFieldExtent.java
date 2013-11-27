
package com.ontometrics.qvx.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QvxFieldExtent.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QvxFieldExtent">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="QVX_FIX"/>
 *     &lt;enumeration value="QVX_COUNTED"/>
 *     &lt;enumeration value="QVX_ZERO_TERMINATED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QvxFieldExtent")
@XmlEnum
public enum QvxFieldExtent {

    QVX_FIX,
    QVX_COUNTED,
    QVX_ZERO_TERMINATED,
    QVX_QV_SPECIAL;

    public String value() {
        return name();
    }

    public static QvxFieldExtent fromValue(String v) {
        return valueOf(v);
    }

}
