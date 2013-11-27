
package com.ontometrics.qvx.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldAttrType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FieldAttrType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="UNKNOWN"/>
 *     &lt;enumeration value="ASCII"/>
 *     &lt;enumeration value="INTEGER"/>
 *     &lt;enumeration value="REAL"/>
 *     &lt;enumeration value="FIX"/>
 *     &lt;enumeration value="MONEY"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="TIME"/>
 *     &lt;enumeration value="TIMESTAMP"/>
 *     &lt;enumeration value="INTERVAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FieldAttrType")
@XmlEnum
public enum FieldAttrType {

    UNKNOWN,
    ASCII,
    INTEGER,
    REAL,
    FIX,
    MONEY,
    DATE,
    TIME,
    TIMESTAMP,
    INTERVAL;

    public String value() {
        return name();
    }

    public static FieldAttrType fromValue(String v) {
        return valueOf(v);
    }

}
