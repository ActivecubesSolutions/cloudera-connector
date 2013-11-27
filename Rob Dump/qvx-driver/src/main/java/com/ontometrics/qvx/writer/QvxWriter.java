package com.ontometrics.qvx.writer;

import java.io.IOException;
import java.io.DataOutput;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;

public class QvxWriter {

    private static final int QVX_DUAL_DATA_RECORD_START = 30;
    private static final int QVX_DUAL_ENTRY_START = 6;


    private static final int DUAL_STREAM_TERMINATOR = 28;
    private static final String ZERO_TERMINATOR = "\0";
    private static final byte [] ZERO_TERMINATOR_BYTES = ZERO_TERMINATOR.getBytes();

    private HeaderConfiguration headerConfiguration;
    private List<FieldDescriptor> fieldDescriptors;

    private DataOutput dataOutput;

    private ByteBuffer _byteBuffer;


    public QvxWriter() {
        _byteBuffer = ByteBuffer.allocate(8);
        _byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }


    public void generateQvx(QvxDataSource qvxDataSource, DataOutput dataOutput) throws Exception {
        this.headerConfiguration = qvxDataSource.geHeaderConfiguration();

        fieldDescriptors = headerConfiguration.getFieldDescriptors();
        this.dataOutput = dataOutput;

        writeHeader();
        Iterator<Object[]> dataIterator = qvxDataSource.getDataIterator();
        while (dataIterator.hasNext()) {
            Object[] rowData = dataIterator.next();
            writeDataRecord(rowData);
        }
        finish();
    }

    private void writeHeader() throws Exception {
        if (headerConfiguration.getFieldDescriptors().isEmpty()) {
            throw new IllegalArgumentException("Field names are empty");
        } else {
            QvxHeaderWriter.instance().write(dataOutput, headerConfiguration);
            dataOutput.write(ZERO_TERMINATOR.getBytes(headerConfiguration.getCharset().toString()));
        }
    }



    private boolean writeDataRecord (Object rowData[]) throws IOException {
        if (headerConfiguration.isQvxDualFormat())
            dataOutput.write(QVX_DUAL_DATA_RECORD_START);

        for (int i = 0; i < rowData.length; i++) {
            Object writtenValue = rowData[i];
            QvxDataType qvxDataType = fieldDescriptors.get(i).getDataType();
            if (headerConfiguration.isQvxDualFormat()) {
                if (writtenValue == null) {
                    dataOutput.write(ZERO_TERMINATOR_BYTES);
                } else {
                    writeAsDouble(writtenValue, qvxDataType);
                }
            }
            if (writtenValue != null) {
                @SuppressWarnings("unchecked")
                byte [] textViewBytes = qvxDataType.getTextRepresentation(writtenValue, headerConfiguration.getCharset());
                dataOutput.write(textViewBytes);
            }
        }

        return true;
    }

    private void finish() throws IOException {
        if (headerConfiguration.isQvxDualFormat()) {
            dataOutput.write(DUAL_STREAM_TERMINATOR);
        }
        _byteBuffer = null;
    }

    private void writeAsDouble(Object writtenValue, QvxDataType qvxDataType) throws IOException {
        @SuppressWarnings("unchecked")
        double convertedToDouble = qvxDataType.toDouble(writtenValue);
        dataOutput.write(QVX_DUAL_ENTRY_START);
        _byteBuffer.putDouble(convertedToDouble);
        _byteBuffer.flip();
        dataOutput.write(_byteBuffer.array());
        _byteBuffer.clear();
    }


}

