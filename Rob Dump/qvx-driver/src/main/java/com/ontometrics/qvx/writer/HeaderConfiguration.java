package com.ontometrics.qvx.writer;

import com.ontometrics.qvx.writer.type.DateQvxDataType;
import com.ontometrics.qvx.writer.type.NumberQvxDataType;
import com.ontometrics.qvx.writer.type.StringQvxDataType;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * HeaderConfiguration.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public class HeaderConfiguration {
    private String tableName;
    private Charset charset;
    private boolean qvxDualFormat;
    private BigInteger majorVersion;
    private BigInteger minorVersion;


    private List<FieldDescriptor> fieldDescriptors;

    public HeaderConfiguration(String tableName, List<String> fieldNames, Charset charset, boolean qvxDualFormat, BigInteger majorVersion, BigInteger minorVersion, List<Class> fieldTypes) {
        this.tableName = tableName;
        this.fieldDescriptors = createFieldDescriptors(fieldNames, fieldTypes);
        this.charset = (charset == null) ? WriterConstants.CHARSET_UTF_8 : charset;
        this.qvxDualFormat = qvxDualFormat;
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
    }

    public String getTableName() {
        return tableName;
    }


    public Charset getCharset() {
        return charset;
    }

    public boolean isQvxDualFormat() {
        return qvxDualFormat;
    }

    public BigInteger getMajorVersion() {
        return majorVersion;
    }

    public BigInteger getMinorVersion() {
        return minorVersion;
    }


    public List<FieldDescriptor> getFieldDescriptors() {
        return fieldDescriptors;
    }

    private final QvxDataType[] SUPPORTED_TYPES = new QvxDataType[] {
            new StringQvxDataType(), new NumberQvxDataType(), new DateQvxDataType(WriterConstants.UTC_TIME_ZONE, "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    };

    private List<FieldDescriptor> createFieldDescriptors(List<String> fieldNames, List<Class> fieldTypes) {
        List<FieldDescriptor> fieldDescriptors = new ArrayList<FieldDescriptor>();

        for (int r = 0; r < fieldTypes.size(); r++) {
            Class aClass = fieldTypes.get(r);
            QvxDataType qvxDataType = resolveDataType(aClass);
            if (qvxDataType == null) {
                throw new IllegalArgumentException("Class "+aClass+" is not supported");
            }
            FieldDescriptor fieldDescriptor = new FieldDescriptor(fieldNames.get(r), qvxDataType);
            fieldDescriptors.add(fieldDescriptor);
        }
        return fieldDescriptors;
    }


    private QvxDataType resolveDataType(Class fieldType) {
        QvxDataType qvxDataType = null;
        Class clazz = fieldType;
        for (QvxDataType supportedType : SUPPORTED_TYPES) {
            if (supportedType.canHandle(clazz)) {
                qvxDataType = supportedType;
                break;
            }
        }
        return qvxDataType;
    }

    public boolean isBigEndian() {
        return isQvxDualFormat();
    }
}
