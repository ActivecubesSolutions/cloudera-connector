
package com.ontometrics.qvx.writer;

import com.ontometrics.qvx.jaxb.JAXBUtility;
import com.ontometrics.qvx.model.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.math.BigInteger;
import java.util.*;
import javax.xml.datatype.*;

public final class QvxHeaderWriter {
    private static final QvxHeaderWriter instance = new QvxHeaderWriter();

    public static QvxHeaderWriter instance() {
        return instance;
    }

    public void write(DataOutput output, HeaderConfiguration headerConfiguration) throws Exception {
        QvxTableHeader tableHeader = createQvxTableHeader(headerConfiguration);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(2048);
        JAXBUtility.createMarshallerForObject(tableHeader, headerConfiguration.getCharset())
                .marshal(tableHeader, byteStream);
        output.write(byteStream.toByteArray());
    }

    private QvxTableHeader createQvxTableHeader(HeaderConfiguration headerConfiguration) throws Exception {
        ObjectFactory objectFactory = new ObjectFactory();

        QvxTableHeader qvxHeader = writeDocumentDescriptor(headerConfiguration, objectFactory);
        writeFields(headerConfiguration, objectFactory, qvxHeader);
        return qvxHeader;
    }

    private QvxTableHeader writeDocumentDescriptor(HeaderConfiguration headerConfiguration, ObjectFactory objectFactory)
            throws DatatypeConfigurationException {
        QvxTableHeader qvxHeader = objectFactory.createQvxTableHeader();

        qvxHeader.setMajorVersion(headerConfiguration.getMajorVersion());
        qvxHeader.setMinorVersion(headerConfiguration.getMinorVersion());
        qvxHeader.setTableName(headerConfiguration.getTableName());

        qvxHeader.setCreateUtcTime(createUtcTime());

        if (headerConfiguration.isQvxDualFormat()) {
            qvxHeader.setUsesSeparatorByte(true);
            qvxHeader.setBlockSize(BigInteger.ZERO);
        } else {
            qvxHeader.setUsesSeparatorByte(false);
        }
        return qvxHeader;
    }

    private XMLGregorianCalendar createUtcTime() throws DatatypeConfigurationException {
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar.setTimeZone(WriterConstants.UTC_TIME_ZONE);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

    private void writeFields(HeaderConfiguration headerConfiguration, ObjectFactory objectFactory,
                                    QvxTableHeader qvxHeader) {
        qvxHeader.setFields(objectFactory.createQvxTableHeaderFields());
        List<QvxTableHeader.Fields.QvxFieldHeader> fieldHeaders = qvxHeader.getFields().getQvxFieldHeader();
        for (FieldDescriptor fieldDescriptor : headerConfiguration.getFieldDescriptors()) {
            fieldHeaders.add(FieldHeaderFactory.instance().createFieldHeader(fieldDescriptor.getFieldName(),
                    objectFactory, headerConfiguration));
        }
    }

}
