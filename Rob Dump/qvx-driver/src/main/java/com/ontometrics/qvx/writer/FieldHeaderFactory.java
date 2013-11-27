package com.ontometrics.qvx.writer;

import com.ontometrics.qvx.model.*;

import java.math.BigInteger;

/**
 * FieldHeaderFactory.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public class FieldHeaderFactory {
    private static final FieldHeaderFactory instance = new FieldHeaderFactory();

    public static FieldHeaderFactory instance() {
        return instance;
    }

    public QvxTableHeader.Fields.QvxFieldHeader createFieldHeader(String fieldName, ObjectFactory objectFactory,
                                                                  HeaderConfiguration headerConfiguration) {
        QvxTableHeader.Fields.QvxFieldHeader fieldHeader = objectFactory.createQvxTableHeaderFieldsQvxFieldHeader();
        fieldHeader.setFieldName(fieldName);
        fieldHeader.setCodePage(CodePageFactory.instance().createCodePage(headerConfiguration));
        if (headerConfiguration.isQvxDualFormat()) {
            populateValueForQvxDual(fieldHeader);
        } else {
            populateValuesForPlainText(fieldHeader);
        }
        return fieldHeader;
    }

    private void populateValuesForPlainText(QvxTableHeader.Fields.QvxFieldHeader fieldHeader) {
        fieldHeader.setType(QvxFieldType.QVX_TEXT);
        fieldHeader.setNullRepresentation(QvxNullRepresentation.QVX_NULL_ZERO_LENGTH);
        fieldHeader.setBigEndian(false);
        fieldHeader.setExtent(QvxFieldExtent.QVX_ZERO_TERMINATED);
    }

    private void populateValueForQvxDual(QvxTableHeader.Fields.QvxFieldHeader fieldHeader) {
        fieldHeader.setType(QvxFieldType.QVX_QV_DUAL);
        fieldHeader.setFieldFormat(createFieldAttributes());
        fieldHeader.setBigEndian(true);
        fieldHeader.setExtent(QvxFieldExtent.QVX_QV_SPECIAL);

        fieldHeader.setNullRepresentation(QvxNullRepresentation.QVX_NULL_NEVER);
        fieldHeader.setByteWidth(BigInteger.ZERO);
        fieldHeader.setFixPointDecimals(BigInteger.ZERO);
    }

    private FieldAttributes createFieldAttributes() {
        FieldAttributes fieldAttributes = new FieldAttributes();
        fieldAttributes.setFmt("");
        fieldAttributes.setDec("");
        fieldAttributes.setThou("");

        fieldAttributes.setType(FieldAttrType.UNKNOWN);
        fieldAttributes.setUseThou(BigInteger.ZERO);
        fieldAttributes.setNDec(BigInteger.ZERO);
        return fieldAttributes;
    }
}
