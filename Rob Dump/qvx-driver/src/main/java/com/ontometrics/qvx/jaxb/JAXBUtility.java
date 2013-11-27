package com.ontometrics.qvx.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.charset.Charset;

/**
 * JAXBUtility.java
 * Created on 10/09/2013 by Nikolay Chorniy
 */
public class JAXBUtility {

    public static final String JAXB_FORMATTED_OUTPUT = "jaxb.formatted.output";
    public static final String JAXB_ENCODING = "jaxb.encoding";

    public static Marshaller createMarshallerForObject(Object object, Charset charset) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass().getPackage().getName());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(JAXB_ENCODING, charset.toString());
        marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

}
