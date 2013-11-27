
package com.ontometrics.qvx.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QvxNullRepresentation.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QvxNullRepresentation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="QVX_NULL_NEVER"/>
 *     &lt;enumeration value="QVX_NULL_ZERO_LENGTH"/>
 *     &lt;enumeration value="QVX_NULL_FLAG_WITH_UNDEFINED_DATA"/>
 *     &lt;enumeration value="QVX_NULL_FLAG_SUPPRESS_DATA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QvxNullRepresentation")
@XmlEnum
public enum QvxNullRepresentation {

    QVX_NULL_NEVER,
    QVX_NULL_ZERO_LENGTH,
    QVX_NULL_FLAG_WITH_UNDEFINED_DATA,
    QVX_NULL_FLAG_SUPPRESS_DATA;

    public String value() {
        return name();
    }

    public static QvxNullRepresentation fromValue(String v) {
        return valueOf(v);
    }

}
