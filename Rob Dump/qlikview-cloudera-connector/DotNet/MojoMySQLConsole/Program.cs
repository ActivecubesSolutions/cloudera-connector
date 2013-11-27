using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;

using mojomysql;
using net.sf.jni4net;

namespace MojoMySQLConsole
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                var bridgeSetup = new BridgeSetup();
                bridgeSetup.AddAllJarsClassPath(".");
                Bridge.CreateJVM(bridgeSetup);
                Bridge.RegisterAssembly(typeof(SimpleMySQLDriver).Assembly);

                ISimpleMySQLDriver driver = new SimpleMySQLDriver();

                String query = "";
                String driver_name = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:5510/sakila";
                String username = "root";
                String password = "irvine09";

                query = "SELECT * FROM sakila.actor WHERE last_name LIKE 'B%'";
                String results = driver.QueryResultSetAsString(driver_name, url, username, password, query);

                //Console.Write(results);
                Debug.WriteLine(results);

                //String last_name = String.Format("Smith{0}", DateTime.Now.Ticks.ToString());
                //query = String.Format("INSERT INTO sakila.actor (first_name, last_name) VALUES ('John', '{0}')", last_name);
                //driver.RunStaticQuery(query);
            }
            catch (System.Exception ex)
            {
                Console.WriteLine("***Error in Main(): " + ex.Message);
            }
        }
    }
}
