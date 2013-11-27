using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;
using System.Reflection;
using System.Xml;

using mojohive;
using net.sf.jni4net;


namespace MojoHiveConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {

                String xml_results = "<Results><Row><billtocode>3DAKE</billtocode><billtodesc>l@K(&gt;ur(ZCxbt</billtodesc><billtoeffdt>2009-10-18 00:00:00</billtoeffdt><billtoexpdt>2011-07-04 00:00:00</billtoexpdt></Row><Row><billtocode>3DAKE</billtocode><billtodesc>l@K(&gt;ur(ZCxbt</billtodesc><billtoeffdt>2009-10-18 00:00:00</billtoeffdt><billtoexpdt>2011-07-04 00:00:00</billtoexpdt></Row></Results>";
                System.Xml.XmlDocument doc = new XmlDocument();
                doc.LoadXml(xml_results);
                XmlNode root = doc.FirstChild;
                if (root.HasChildNodes)
                {
                    // These are row nodes
                    //for (int i = 0; i < root.ChildNodes.Count; i++)
                    //{
                    //    Debug.WriteLine(root.ChildNodes[i].Name);
                    //    for (int ii = 0; ii < root.ChildNodes[i].ChildNodes.Count; ii++)
                    //    {
                    //        Debug.WriteLine(root.ChildNodes[i].ChildNodes[ii].Name);
                    //    }
                    //}

                    foreach (XmlNode row_node in root.ChildNodes)
                    {
                        Debug.WriteLine(row_node.Name);
                        foreach (XmlNode field_node in row_node.ChildNodes)
                        {
                            Debug.WriteLine(field_node.Name + " = " + field_node.InnerText);
                        }

                    }


                }




                String local_path = System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);


                var bridgeSetup = new BridgeSetup();
                bridgeSetup.AddAllJarsClassPath(".");
                bridgeSetup.AddAllJarsClassPath(local_path);
                Bridge.CreateJVM(bridgeSetup);
                Bridge.RegisterAssembly(typeof(MojoHiveDriver).Assembly);



                String drivername = "org.apache.hive.jdbc.HiveDriver";
                String url = "jdbc:hive2://54.218.97.70:21050/;auth=noSasl";
                String username = "";
                String password = "";
                String queuename = "";

                IMojoHiveDriver driver = new MojoHiveDriver();

                int result = driver.TestConnection(drivername, url, queuename, username, password);

                Console.WriteLine("Connection successful.");

                String sql = "SELECT * FROM billtocodes WHERE billtocode LIKE '3D%'";

                String xml = driver.QueryResultSetAsXML(drivername, url, queuename, username, password, sql);

                Console.WriteLine("Results\r\n" + xml);

            }
            catch (System.Exception ex)
            {
                Console.WriteLine("***Error in Main(): " + ex.Message);
            }

        }
    }
}
