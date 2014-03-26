using System;
using System.Diagnostics;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;
using System.IO;
using System.Security.Authentication;
using System.Text.RegularExpressions;
using System.Xml;
using System.Threading;
using QlikView.Qvx.QvxLibrary;

using net.sf.jni4net;
using mojohive;
using log4net;

namespace QvBigDataConn
{
    class QvBigDataConnection : QvxConnection
    {
        protected SimpleLogger _logger;

        // Has been hardcoded, should preferably be done programatically.
        public override void Init()
        {
            // Set to true true to get more logging.
            //QvxLog.SetLogLevels(false, false);
            QvxLog.SetLogLevels(true, true);
            QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "Init()");

            _logger = new SimpleLogger("QvBigDataConn");
            _logger.EnableFileLogging(Environment.GetFolderPath(System.Environment.SpecialFolder.LocalApplicationData), log4net.Core.Level.Debug);

            _logger.LogMsg(log4net.Core.Level.Info, "Initializing QlikView API connection class.");

            _logger.LogMsg(log4net.Core.Level.Info, String.Format("Command queue path: [{0}].", Program.CommandPipe));

            //VerifyCredentials();

            var billToCodesFields = new QvxField[]
                {
                    new QvxField("billtocode", QvxFieldType.QVX_SIGNED_INTEGER, QvxNullRepresentation.QVX_NULL_ZERO_LENGTH, FieldAttrType.INTEGER),
                    new QvxField("billtodesc", QvxFieldType.QVX_SIGNED_INTEGER, QvxNullRepresentation.QVX_NULL_ZERO_LENGTH, FieldAttrType.INTEGER),
                    new QvxField("billtoeffdt", QvxFieldType.QVX_SIGNED_INTEGER, QvxNullRepresentation.QVX_NULL_ZERO_LENGTH, FieldAttrType.INTEGER),
                    new QvxField("billtoexpdt", QvxFieldType.QVX_SIGNED_INTEGER, QvxNullRepresentation.QVX_NULL_ZERO_LENGTH, FieldAttrType.INTEGER)
                };

            /*
            var applicationsEventLogFields = new QvxField[]
                {
                    new QvxField("Category", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("EntryType", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("Message", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("CategoryNumber", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("Index", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("MachineName", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("Source", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("TimeGenerated", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII)
                };

            var systemEventLogFields = new QvxField[]
                {
                    new QvxField("Category", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("EntryType", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("Message", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("CategoryNumber", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("Index", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("MachineName", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("Source", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII),
                    new QvxField("TimeGenerated", QvxFieldType.QVX_TEXT, QvxNullRepresentation.QVX_NULL_FLAG_SUPPRESS_DATA, FieldAttrType.ASCII)
                };
            */
            MTables = new List<QvxTable> {
                new QvxTable {
                    TableName = "BillToCodes",
                    GetRows = GetBillToCodes,
                    Fields = billToCodesFields
                }
            };

            /*
            MTables = new List<QvxTable> {
                new QvxTable {
                    TableName = "ApplicationsEventLog",
                    GetRows = GetApplicationEvents,
                    Fields = applicationsEventLogFields
                },
                new QvxTable {
                    TableName = "SystemEventLog",
                    GetRows = GetSystemEvents,
                    Fields = systemEventLogFields
                }
            };
             */
        }

        private IEnumerable<QvxDataRow> GetBillToCodes()
        {
            String tableName = "BillToCodes";
            String xml_results = "";

            try
            {
                //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "GetBillToCodes()");
                _logger.LogMsg(log4net.Core.Level.Info, "Enter callback function GetBillToCodes()");

                // DEBUGGING: try to connect to named pipe from here.
                //Program.RunNamedPipeHandler(Program._parentWindowHandle, Program._commandQueue, _logger);

                //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, String.Format("GetEvents(log: {0}, tableName: {1})", log, tableName));

                // TO DO: put this back in?
               // VerifyCredentials();

                // TO DO: put in basic validation of query ?
                //if (!EventLog.Exists(log))
                //{
                //    throw new QvxPleaseSendReplyException(QvxResult.QVX_TABLE_NOT_FOUND,
                //        String.Format("There is no EventLog with name: {0}", tableName));
                //}

                // Run the query, get a dataset in XML ? 

                // Get the path to the executing program so we can add it to the Java CLASSPATH.
                String local_path = System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);
                _logger.LogMsg(log4net.Core.Level.Info, "Working folder: " + local_path);

                //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "Opening DotNet-Java bridge.");
                _logger.LogMsg(log4net.Core.Level.Info, "Opening DotNet-Java bridge.");

                var bridgeSetup = new BridgeSetup();
                bridgeSetup.Verbose = true;
                bridgeSetup.AddAllJarsClassPath(".");
                bridgeSetup.AddAllJarsClassPath(local_path);

                Bridge.CreateJVM(bridgeSetup);
                Bridge.RegisterAssembly(typeof(MojoHiveDriver).Assembly);

                String drivername = "org.apache.hive.jdbc.HiveDriver";
                //String url = "jdbc:hive2://54.218.97.70:21050/;auth=noSasl";
                String url = "jdbc:hive2://192.168.1.160:10000/default;";
                String username = "hive";
                String password = "hive";
                String queuename = "default";

                // *** Create the Java proxy class
                //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "Creating MojoHiveDriver proxy.");
                _logger.LogMsg(log4net.Core.Level.Info, "Creating MojoHiveDriver proxy.");
                IMojoHiveDriver driver = new MojoHiveDriver();

                // *** Test Cloudera connection
                //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, String.Format("Testing connection: driver={0} | url={1} | queuename={2} | username={3} | password={4}.", drivername, url, queuename, username, password));
                _logger.LogMsg(log4net.Core.Level.Info, String.Format("Testing connection: driver={0} | url={1} | queuename={2} | username={3} | password={4}.", drivername, url, queuename, username, password));
                int result = driver.TestConnection(drivername, url, queuename, username, password);
                if (result == 0)
                {
                    //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "Connection successful.");
                    _logger.LogMsg(log4net.Core.Level.Info, "Connection successful.");
                }
                else
                {
                    //QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "Connection failed: " + driver.GetLastExceptionMessage());
                    _logger.LogMsg(log4net.Core.Level.Info, "Connection failed: " + driver.GetLastExceptionMessage());
                }


                // *** Run an actual query
                //TO DO: get this from QlikView ? Must use named pipes?
                //String sql = "SELECT * FROM billtocodes WHERE billtocode='3DAKE'";
                //String sql = "SELECT * FROM billtocodes WHERE billtocode LIKE '4P%'";
                String sql = "SELECT * FROM billtocodes";// WHERE billtoeffdt > '2008-02-01 00:00:00' AND billtoeffdt < '2008-02-28 00:00:00'";
                //String sql = "";// WHERE billtoeffdt > '2008-02-01 00:00:00' AND billtoeffdt < '2008-02-28 00:00:00'";
                _logger.LogMsg(log4net.Core.Level.Info, "Running query: " + sql);

                xml_results = driver.QueryResultSetAsXML(drivername, url, queuename, username, password, sql);
                // int s = driver.QueryResultSetToPipe(drivername, url, queuename, username, password, sql,"");

                //_logger.LogMsg(log4net.Core.Level.Info, "XML results:\r\n" + xml_results);
            }
            catch (System.Exception ex)
            {
                _logger.LogMsg(log4net.Core.Level.Error, "Fatal exception: " + ex.Message);
            }

            _logger.LogMsg(log4net.Core.Level.Info, "Loading XML into parser." + xml_results);
            System.Xml.XmlDocument doc = new XmlDocument();
            doc.LoadXml(xml_results);
            XmlNode root = doc.FirstChild;
            if (root.HasChildNodes)
            {
                _logger.LogMsg(log4net.Core.Level.Info, String.Format("Query returned {0} rows.", root.ChildNodes.Count.ToString()));

                _logger.LogMsg(log4net.Core.Level.Info, "Moving data from XML to QlikView objects." + xml_results);
                foreach (XmlNode row_node in root.ChildNodes)
                {
                    //Debug.WriteLine(row_node.Name);
                    yield return MakeEntry(row_node, FindTable(tableName, MTables));

                    //foreach (XmlNode field_node in row_node.ChildNodes)
                    //{
                    //    Debug.WriteLine(field_node.Name + " = " + field_node.InnerText);                        
                    //}
                }
            }
            else
            {
                _logger.LogMsg(log4net.Core.Level.Warn, "No rows returned!" + xml_results);
            }
        }

        private QvxDataRow MakeEntry(XmlNode rowNode, QvxTable table)
        {
            var row = new QvxDataRow();

            foreach (XmlNode field_node in rowNode.ChildNodes)
            {
                switch (field_node.Name.ToLower())
                {
                    case "billtocode":
                        row[table.Fields[0]] = field_node.InnerText;
                        break;
                    case "billtodesc":
                        row[table.Fields[1]] = field_node.InnerText;
                        break;
                    case "billtoeffdt":
                        row[table.Fields[2]] = field_node.InnerText;
                        break;
                    case "billtoexpdt":
                        row[table.Fields[3]] = field_node.InnerText;
                        break;
                }
            }

            //row[table.Fields[0]] = "CODE VALUE 1";
            //row[table.Fields[1]] = "CODE DESCRIPTION 1";
            //row[table.Fields[2]] = DateTime.Now.ToString();
            //row[table.Fields[3]] = DateTime.Now.ToString();

            //row[table.Fields[0]] = evl.Category;
            //row[table.Fields[1]] = evl.EntryType.ToString();
            //row[table.Fields[2]] = evl.Message;
            //row[table.Fields[3]] = evl.CategoryNumber.ToString();
            //row[table.Fields[4]] = evl.Index.ToString();
            //row[table.Fields[5]] = evl.MachineName;
            //row[table.Fields[6]] = evl.Source;
            //row[table.Fields[7]] = evl.TimeGenerated.ToString();

            return row;
        }

        //private IEnumerable<QvxDataRow> GetApplicationEvents()
        //{
        //    QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "GetApplicationEvents()");
        //    return GetEvents("Application", "ApplicationsEventLog");
        //}

        //private IEnumerable<QvxDataRow> GetSystemEvents()
        //{
        //    QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "GetSystemEvents()");
        //    return GetEvents("System", "SystemEventLog");
        //}

        //private IEnumerable<QvxDataRow> GetEvents(string log, string tableName)
        //{
        //    QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Debug, String.Format("GetEvents(log: {0}, tableName: {1})", log, tableName));
        //    VerifyCredentials();

        //    if (!EventLog.Exists(log))
        //    {
        //        throw new QvxPleaseSendReplyException(QvxResult.QVX_TABLE_NOT_FOUND,
        //            String.Format("There is no EventLog with name: {0}", tableName));
        //    }

        //    var ev = new EventLog(log);

        //    foreach (var evl in ev.Entries)
        //    {
        //        yield return MakeEntry(evl as EventLogEntry, FindTable(tableName, MTables));
        //    }
        //}

        //private QvxDataRow MakeEntry(EventLogEntry evl, QvxTable table)
        //{
        //    var row = new QvxDataRow();
        //    row[table.Fields[0]] = evl.Category;
        //    row[table.Fields[1]] = evl.EntryType.ToString();
        //    row[table.Fields[2]] = evl.Message;
        //    row[table.Fields[3]] = evl.CategoryNumber.ToString();
        //    row[table.Fields[4]] = evl.Index.ToString();
        //    row[table.Fields[5]] = evl.MachineName;
        //    row[table.Fields[6]] = evl.Source;
        //    row[table.Fields[7]] = evl.TimeGenerated.ToString();
        //    return row;
        //}

        private void VerifyCredentials()
        {
            QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "VerifyCredentials()");

            string username, password;
            this.MParameters.TryGetValue("UserId", out username);
            this.MParameters.TryGetValue("Password", out password);

            //username = "bluestar";
            //password = "Bsil@123";

            if (username != "hive" || password != "hive")
            {
                var error = "Username and/or password is incorrect";
                QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, String.Format("VerifyCredentials() - {0}", error));
                throw new AuthenticationException(error);
            }
        }

        public override QvxDataTable ExtractQuery(string query, List<QvxTable> qvxTables)
        {
            return base.ExtractQuery(query, qvxTables);
        }
    }
}