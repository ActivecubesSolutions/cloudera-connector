using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Diagnostics;
using System.IO;

namespace QvBigDataConn
{
    public struct QlikViewResponseTypes 
    {
        public const String QvxOK = "QVX_OK";
        public const String QvxUnknownError = "QVX_UNKNOWN_ERROR";
        public const String QvxUnknownCommand = "QVX_UNKNOWN_COMMAND";
    }

    public struct QlikViewNodeNames
    {
        public const string QvParameters = "Parameters";
        public const String QvString = "String";
    }

    public struct QlikViewGenericCommandValues
    {
        public const String GetCustomCaption = "GetCustomCaption";
        public const String DisableQlikViewSelectButton = "DisableQlikViewSelectButton";
        public const String HaveStarField = "HaveStarField";
        public const String IsConnected = "IsConnected";
    }

    public struct QlikViewCommandTypes
    {
        public const String Connect = "QVX_CONNECT";
        public const String Execute = "QVX_EXECUTE";
        public const String EditConnect = "QVX_EDIT_CONNECT";
        public const String EditSelect = "QVX_EDIT_SELECT";
        public const String GenericCommand = "QVX_GENERIC_COMMAND";
        public const String Disconnect = "QVX_DISCONNECT";
        public const String Terminate = "QVX_TERMINATE";
        public const String Progress = "QVX_PROGRESS";
        public const String Abort = "QVX_ABORT";
    }


    public class QlikViewMessage
    {
        private String _xml = null;
        private XmlDocument _doc = null;
        private String _messageType = null;

        public String MessageType
        {
            get
            {
                return _messageType;
            }
        }

        public QlikViewMessage()
        {
            _xml = null;
            _doc = null;
        }

        public Boolean Load(String xml)
        {
            Boolean is_valid = false;
            try
            {
                _xml = xml;
                _doc = null;

                _doc = new XmlDocument();
                _doc.LoadXml(xml);

                XmlNode root = _doc.FirstChild;
                if (root.HasChildNodes)
                {

                    foreach (XmlNode row_node in root.ChildNodes)
                    {
                        if (row_node.Name == "Command")
                        {
                            _messageType = row_node.InnerText;

                            is_valid = true;
                        }
                    }
                }

                return is_valid;
            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in QlikViewMessage.Load(): " + ex.Message);
                throw ex;
            }

        }

        public void GetSQLStatementAndDataPipeName(out String sql, out String datapipename)
        {
            sql= null;
            datapipename = null;

            int param_count = 0;
            try {

                XmlNode root = _doc.FirstChild;
                if (root.HasChildNodes)
                {
                    foreach (XmlNode row_node in root.ChildNodes)
                    {
                        if (row_node.Name == QlikViewNodeNames.QvParameters)
                        {
                            foreach (XmlNode param_node in row_node.ChildNodes)
                            {
                                if (param_node.Name == QlikViewNodeNames.QvString)
                                {
                                    if (param_count == 0)
                                    {
                                        param_count++;
                                        sql = param_node.InnerText;
                                    }
                                    else if (param_count == 1)
                                    {
                                        datapipename = param_node.InnerText;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                }

            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in QlikViewMessage.GetSQLStatementAndDataPipeName(): " + ex.Message);
                throw ex;
            }
            return;
        }

        public String GetConnectionString()
        {
            String conn = null;
            try
            {

                XmlNode root = _doc.FirstChild;
                if (root.HasChildNodes)
                {
                    foreach (XmlNode row_node in root.ChildNodes)
                    {
                        if (row_node.Name == QlikViewNodeNames.QvParameters)
                        {
                            foreach (XmlNode param_node in row_node.ChildNodes)
                            {
                                if (param_node.Name == QlikViewNodeNames.QvString)
                                {
                                    conn = param_node.InnerText;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }

            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in QlikViewMessage.GetConnectionString(): " + ex.Message);
                throw ex;
            }
            return conn;
        }

        // TO DO: This can return an array of strings ?
        public void ParseGenericCommand(out String val)
        {
            val = null;
            try
            {

                XmlNode root = _doc.FirstChild;
                if (root.HasChildNodes)
                {
                    foreach (XmlNode row_node in root.ChildNodes)
                    {
                        if (row_node.Name == QlikViewNodeNames.QvParameters)
                        {
                            foreach (XmlNode param_node in row_node.ChildNodes)
                            {
                                if (param_node.Name == QlikViewNodeNames.QvString)
                                {
                                    val = param_node.InnerText;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in QlikViewMessage.ParseGenericCommand(): " + ex.Message);
                throw ex;
            }
        }

        
        public static String MakeGenericReplyMessage(String ResponseType, String[] OutputValues = null, String ErrorMessage = null)
        {
            String xml = null;

            // TO DO: do this with an XML document object instead of hard-coded strings!!

            xml = "<QvxReply>\r\n";

            String response = ResponseType;
            xml += String.Format("\t<Result>{0}</Result>\r\n", response );
   
            if( OutputValues != null && OutputValues.Length > 0 ) {
                xml += "\t<OutputValues>\r\n";
                for(int ii=0; ii < OutputValues.Length; ii++ )
                {
                    xml+= String.Format("\t\t<String>{0}</String>\r\n", OutputValues[ii]);
                }
                xml += "\t</OutputValues>\r\n";
            }
            
            if( !String.IsNullOrEmpty(ErrorMessage ))
            {
                xml += String.Format("\t<ErrorMessage>{0}</ErrorMessage>\r\n", ErrorMessage );
            }

            xml += "</QvxReply>";


            return xml;
        }

        public static String MakeGenericReplyMessage(Boolean isOK, String[] OutputValues = null, String ErrorMessage = null)
        {
            String xml = null;

            // TO DO: do this with an XML document object instead of hard-coded strings!!

            xml = "<QvxReply>\r\n";

            String response = ( isOK ? QlikViewResponseTypes.QvxOK : QlikViewResponseTypes.QvxUnknownError );
            xml += String.Format("\t<Result>{0}</Result>\r\n", response );
   
            if( OutputValues != null && OutputValues.Length > 0 ) {
                xml += "\t<OutputValues>\r\n";
                for(int ii=0; ii < OutputValues.Length; ii++ )
                {
                    xml+= String.Format("\t\t<String>{0}</String>\r\n", OutputValues[ii]);
                }
                xml += "\t</OutputValues>\r\n";
            }
            
            if( !String.IsNullOrEmpty(ErrorMessage ))
            {
                xml += String.Format("\t<ErrorMessage>{0}</ErrorMessage>\r\n", ErrorMessage );
            }

            xml += "</QvxReply>";


            return xml;
        }



    }
}
