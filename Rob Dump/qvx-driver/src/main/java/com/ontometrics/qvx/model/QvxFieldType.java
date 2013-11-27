
package com.ontometrics.qvx.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QvxFieldType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QvxFieldType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="QVX_SIGNED_INTEGER"/>
 *     &lt;enumeration value="QVX_UNSIGNED_INTEGER"/>
 *     &lt;enumeration value="QVX_IEEE_REAL"/>
 *     &lt;enumeration value="QVX_PACKED_BCD"/>
 *     &lt;enumeration value="QVX_BLOB"/>
 *     &lt;enumeration value="QVX_TEXT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QvxFieldType")
@XmlEnum
public enum QvxFieldType {

    QVX_SIGNED_INTEGER,
    QVX_UNSIGNED_INTEGER,
    QVX_IEEE_REAL,
    QVX_PACKED_BCD,
    QVX_BLOB,
    QVX_TEXT,
    QVX_QV_DUAL;

    public String value() {
        return name();
    }

    public static QvxFieldType fromValue(String v) {
        return valueOf(v);
    }

}
