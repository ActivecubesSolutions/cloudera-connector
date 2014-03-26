using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Diagnostics;
using System.Threading;
using System.Windows.Interop;

using System.IO;
using System.IO.Pipes;
using System.Text;
using System.Configuration;

using log4net;
using log4net.Core;

using net.sf.jni4net;
using mojohive;


namespace QvBigDataConn
{
    static class Program
    {


        private static String _commandPipePath = null;
        private static Int32 _parentWindowHandle = -1;
        private static String _connectionString = null;
        private static String _sql = null;
        private static String _dataPipePath = null;
        private static SimpleLogger _logger = null;
        private static String _customButtonCaption = "Custom SQL";
        private static String _driverName = null;
        private static String _userName = null;
        private static String _password = null;
        private static int _messageWaitMilliseconds = 0;

        public static String ConnectionString
        {
            get
            {
                return _connectionString;
            }
        }

        public static String CommandPipe
        {
            get
            {
                return _commandPipePath;
            }
        }

        public static Int32 ParentWindowHandle
        {
            get
            {
                return _parentWindowHandle;
            }
        }

        private static string GetDefaultExeConfigPath(ConfigurationUserLevel userLevel)
        {
            try
            {
                var UserConfig = ConfigurationManager.OpenExeConfiguration(userLevel);
                return UserConfig.FilePath;
            }
            catch (ConfigurationException e)
            {
                return e.Filename;
            }
        }

        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {

#if DEBUG   
            // Debug mode only: 12-second pause to allow Visual Studio debugger to attach via "Debug | Attach to Process".
            Thread.Sleep(12000);
#endif
            // Initialize the Log4Net based logger.
            _logger = new SimpleLogger("QvBigDataConn");
            String log_path = Path.Combine(Environment.GetFolderPath(System.Environment.SpecialFolder.LocalApplicationData), "QvBigDataConn");
            if (!Directory.Exists(log_path))
            {
                try
                {
                    Directory.CreateDirectory(log_path);
                }
                catch (Exception)
                {
                    log_path = Environment.GetFolderPath(System.Environment.SpecialFolder.LocalApplicationData);
                }
            }
            _logger.EnableFileLogging(log_path, log4net.Core.Level.Debug);
            _logger.LogMsg(log4net.Core.Level.Debug, "Entering Main() function of connector.");


            try
            {
                //_logger.LogMsg(Level.Debug, String.Format("Application config file: {0}", GetDefaultExeConfigPath(ConfigurationUserLevel.None)));
                //_logger.LogMsg(Level.Debug, String.Format("Roaming user config file: {0}", GetDefaultExeConfigPath(ConfigurationUserLevel.PerUserRoaming)));
                //_logger.LogMsg(Level.Debug, String.Format("Local user config file: {0}", GetDefaultExeConfigPath(ConfigurationUserLevel.PerUserRoamingAndLocal)));
                _logger.LogMsg(Level.Debug, String.Format("Initializing local user config file: {0}", GetDefaultExeConfigPath(ConfigurationUserLevel.PerUserRoamingAndLocal)));

                // Initialize the User settings from saved config file.
                if (Properties.Settings.Default.UpgradeFromPrevious)
                {
                    // If this is the first time the user has run the app and a previous version exists,
                    // copy in the existing user settings.
                    Properties.Settings.Default.Upgrade();
                    Properties.Settings.Default.UpgradeFromPrevious = false;
                    Properties.Settings.Default.Save();
                }
                if (Properties.Settings.Default.Drivers.Count > 0)
                {
                    _driverName = Properties.Settings.Default.Drivers[0];
                    if (Properties.Settings.Default.LastDriver >= 0 && Properties.Settings.Default.LastDriver <= Properties.Settings.Default.Drivers.Count)
                    {
                        _driverName = Properties.Settings.Default.Drivers[Properties.Settings.Default.LastDriver];
                    }
                }
                _userName = Properties.Settings.Default.User;
                _password = Properties.Settings.Default.Password;

                if( Properties.Settings.Default.MessageWaitMilliseconds > 0 && Properties.Settings.Default.MessageWaitMilliseconds < 60000 )
                {
                    _messageWaitMilliseconds = Properties.Settings.Default.MessageWaitMilliseconds;
                }

            }
            catch (Exception ex)
            {
                 _logger.LogMsg(Level.Error, String.Format("Error initializing User settings: {0}", ex.Message ));
            }


            // Flag to force use of direct named pipe handler instead of "QvxLibrary.dll" objects.
            // We force this to true for now. We may want to allow an option from a configuration setting.
            Boolean _usePipeHandler = true;

            
            // Check command line arguments. 
            // If there are 2, assume we are running as a (hidden) named pipe connector. Else run as a Windows GUI application.
            if (args != null && args.Length >= 2)
            {
 
                _logger.LogMsg(log4net.Core.Level.Info, String.Format("Running as named pipe connector. Parent window handle = [{0}], QlikView command pipe = [{1}].", args[0].ToString(), args[1].ToString()));

                // Convert window handle (hexadecimal string format) to 32-bit integer.
                int handle = Int32.Parse(args[0].ToString(), System.Globalization.NumberStyles.HexNumber);
                _parentWindowHandle = handle;
                
                // Full path to command pipe from second argument. This will be formatted as "\\hostname\pipe\mypipename.pip". Hostname ="." if local.
                _commandPipePath = args[1].ToString();

                // For now this flag is always true so we always execute as a raw named pipe handler.
                if (_usePipeHandler)
                { 
                    RunNamedPipeHandler(handle, args[1].ToString());
                }
                else
                {
                    // This sets off the QlikView ".NET objects" API. 
                    new QvBigDataServer().Run(args[0], args[1]);
                }
            }
            else if (args != null && args.Length == 0)
            {
                _logger.LogMsg(log4net.Core.Level.Info, String.Format("Running as stand-alone."));
                Application.EnableVisualStyles();
                Application.SetCompatibleTextRenderingDefault(false);
                Application.Run(new Standalone());
            }

            _logger.LogMsg(Level.Debug, "Exiting program.");
        }


        public static IMojoHiveDriver CreateCustomDriver(String drivername)
        {
            IMojoHiveDriver driver = null;
            try
            {
                String local_path = System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);
                _logger.LogMsg(log4net.Core.Level.Debug, "Connector program folder: " + local_path);

                _logger.LogMsg(log4net.Core.Level.Debug, "Creating .NET-Java bridge.");
                var bridgeSetup = new BridgeSetup();

                // The local_path is the folder where the connector EXE itself is located.
                bridgeSetup.AddAllJarsClassPath(local_path);

                // Check to see if a specific folder has been configured as the location for the current driver.
                // If so, load all JARs in that folder.
                String driver_folder = "";
                Boolean found_the_folder = false;
                try
                {
                    if (Properties.Settings.Default.Drivers.Contains(drivername))
                    {
                        int driver_index = Properties.Settings.Default.Drivers.IndexOf(drivername);

                        if (Properties.Settings.Default.DriverLocations.Count > driver_index)
                        {
                            driver_folder = Properties.Settings.Default.DriverLocations[driver_index];
                            // First assume the folder is a full path, check if it exists.
                            if (Directory.Exists(driver_folder)) found_the_folder = true;
                            else
                            {
                                // Try to append to the driver folder to the current local EXE path as a sub-folder.
                                driver_folder = Path.Combine(local_path, driver_folder);
                                if (Directory.Exists(driver_folder)) found_the_folder = true;
                            }
                            // Always try to add the folder if it exists.
                            if (found_the_folder)
                            {
                                bridgeSetup.AddAllJarsClassPath(driver_folder);
                            }

                        }
                    }
                }
                catch (Exception ex)
                {
                    _logger.LogMsg(Level.Warn, String.Format("Error adding custom driver folder [{0}] to Java CLASSPATH: {1}.", driver_folder, ex.Message));
                }
                

                _logger.LogMsg(log4net.Core.Level.Debug, "Creating Java virtual machine (JVM).");
                Bridge.CreateJVM(bridgeSetup);

                _logger.LogMsg(log4net.Core.Level.Debug, "Registering custom driver assembly with .NET-Java bridge.");
                Bridge.RegisterAssembly(typeof(MojoHiveDriver).Assembly);

                _logger.LogMsg(log4net.Core.Level.Debug, "Creating custom driver proxy for .NET.");
                driver = new MojoHiveDriver();

            }
            catch (System.Exception ex)
            {
                _logger.LogMsg(log4net.Core.Level.Error, "Error creating MojoHiveDriver proxy: " + ex.Message);

            }
            
            return driver;
        }


        private static String ReadNextCommandMessage(NamedPipeClientStream pipe)
        {
           String xml = null;
           int data_len = 0;

           _logger.LogMsg(Level.Debug, "ReadNextCommandMessage(): Attempting to retrieve message from command pipe. ");

            try
            {
                // TO DO: make this more efficient. We also need to handle larger messages / dynamic buffer size.
                byte[] mybuf = new byte[2048];
                byte[] len_buf = new byte[4];
                for( int ii = 0; ii < mybuf.Length; ii++ )
                {
                    mybuf[ii] = 0xFF;
                }

                _logger.LogMsg(Level.Debug, "Checking for pipe stream status.");
                if (pipe.IsConnected)
                {                    
              
                    _logger.LogMsg(Level.Debug, "Pipe is connected. Attempting binary read.");

                    int itmp = 0;
                    int pos = 0;
                    while (itmp >= 0)
                    {
                        itmp = pipe.ReadByte();
                        if (itmp == -1)
                        {
                            _logger.LogMsg(Level.Debug, "End of read stream, -1 returned.");
                            break;
                        }
                        mybuf[pos] = (Byte)itmp;
                        if (pos > 3)
                        {
                            if (mybuf[pos] == 0x00)
                            {
                                break;
                            }
                            else
                            {
                                xml += (char)mybuf[pos];
                            }

                        }
                        else
                        {
                            len_buf[3 - pos] = (byte)itmp;
                        }

                        pos++;
                    }
                    data_len = BitConverter.ToInt32(len_buf, 0);
                    _logger.LogMsg(Level.Debug, String.Format("Bytes read (+1) = {0}, QlkView data length = {1}.", (pos + 1).ToString(), data_len.ToString()));
                  
                    

                }
                else 
                {
                    _logger.LogMsg(Level.Warn, "Pipe is not connected.");
                }
              

            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in ReadNextCommandMessage(): " + ex.Message);
                throw ex;
            }

            return xml;
        }


        private static void SendCommandMessage(NamedPipeClientStream pipe, String xml)
        {
            try
            {
                // Write data length (4 byte integer in big-endian byte order), xml message (string) and final null byte.
                _logger.LogMsg(Level.Debug, String.Format("Sending Command Response message:\r\n{0}", xml));

                int data_len = xml.Length + 1;
                byte[] ar_bytes = BitConverter.GetBytes(data_len);
                //Array.Reverse(ar_bytes);

                // Write the length out in reverse (big-endian byte order).
                for (int ii = 0; ii < 4; ii++)
                {
                    pipe.WriteByte(ar_bytes[3-ii]);
                }
                // Write the XML string 1 byte at a time - no nulls.
                Debug.WriteLine(String.Format("Writing XML to stream: total {0} chars", xml.Length.ToString()));
                Byte[] xml_buf = GetBytes(xml);
                for (int ii = 0; ii < xml_buf.Length; ii++)
                {
                    if (xml_buf[ii] != 0x00)
                    {
                        pipe.WriteByte(xml_buf[ii]);
                    }
                }
                // Write the final null byte that QlikView is expecting.
                pipe.WriteByte(0x00);

                // TO DO: is this Flush() necessary?
                pipe.Flush();

                // Wait for QlikView to read from the pipe. 
               // _logger.LogMsg(Level.Debug, "Waiting for pipe drain on command pipe.");
               // pipe.WaitForPipeDrain();
               // _logger.LogMsg(Level.Debug, "Pipe drain complete.");

                
            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in ReadNextCommandMessage(): " + ex.Message);
                throw ex;
            }
        }

        private static byte[] GetBytes(string str)
        {
            byte[] bytes = new byte[str.Length * sizeof(char)];
            System.Buffer.BlockCopy(str.ToCharArray(), 0, bytes, 0, bytes.Length);
            return bytes;
        }

        public static NamedPipeClientStream CreatePipeStream(String pipe_name, String host_name, int pipe_connect_timeout, PipeDirection direction = PipeDirection.InOut)
        {
            NamedPipeClientStream pipeClientStream = null;
            try
            {
                _logger.LogMsg(Level.Debug, String.Format("Creating .NET NamedPipeClientStream object for {0}", pipe_name));
                pipeClientStream =
                    new NamedPipeClientStream(
                        host_name,
                        pipe_name,
                        direction,
                        PipeOptions.Asynchronous, // TO DO: is Asynchronous the correct option?
                        System.Security.Principal.TokenImpersonationLevel.Impersonation,
                        HandleInheritability.Inheritable);
                

                    // Connect to the pipe
                    _logger.LogMsg(Level.Debug, String.Format("Attempting to connect to pipe [{0}] on host [{1}].", pipe_name, host_name));
                    pipeClientStream.Connect(pipe_connect_timeout);
                    _logger.LogMsg(Level.Info, String.Format("Pipe connection successful: [{0}].", pipe_name));
                    _logger.LogMsg(Level.Debug, String.Format("There are currently {0} pipe server instances open.", pipeClientStream.NumberOfServerInstances));
                
                
            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in CreatePipeStream(): " + ex.Message);
                throw ex;
            }

            return pipeClientStream;
        }

        public static int RunNamedPipeHandler(int parentWindowHandle, String commandPipeName)
        {
            int result = -1;
            int pipe_connect_timeout = 10000; // Millisecond timeout

            try
            {
                _logger.LogMsg(Level.Debug, "Entering RunNamedPipeHandler().");

                // Get the (short) pipe name and host name from the full path. 
                // The .NET functions do not want the full path, only the name. Path format: "\\hostname\pipe\mypipename.pip"
                String host_name = null;
                String pipe_name = null;
                if (!ParsePipePath(commandPipeName, out host_name, out pipe_name))
                {
                    _logger.LogMsg(Level.Fatal, String.Format("Invalid command pipe path {0}. Connector cannot continue.", commandPipeName));
                    return result;
                }

                // Create the .NET pipe wrapper object.
                NamedPipeClientStream pipeClientStream = CreatePipeStream(pipe_name, host_name, pipe_connect_timeout);
                

                // Read from pipe
                _logger.LogMsg(Level.Debug, String.Format("Opening .NET StreamReader."));

                Boolean keep_on_processing = true;
                String msg = null;

                while (keep_on_processing)
                {
                    // Qlikview seems to need some time to respond so we always sleep a few seconds.
                    if (_messageWaitMilliseconds > 0)
                    {
                        //_logger.LogMsg(Level.Debug, String.Format("Sleeping in message handler loop for {0} milliseconds.", _messageWaitMilliseconds.ToString()));
                        Thread.Sleep(_messageWaitMilliseconds);
                    }

                    // *** Get the next full XML message from the QlikView command pipe.
                    msg = ReadNextCommandMessage(pipeClientStream);

                    if (String.IsNullOrEmpty(msg))
                    {
                        _logger.LogMsg(Level.Info, "No message received on command pipe.");
                        keep_on_processing = false;
                        //no_response++;
                        //if (no_response > 10)
                        //{
                        //    keep_on_processing = false;
                        //}
                    }
                    else
                    {
                        _logger.LogMsg(Level.Debug, String.Format("Command message received (length {0}). XML follows: \r\n{1}", msg.Length.ToString(), msg));
                        
                        // *** Handle command message
                        QlikViewMessage command_msg = new QlikViewMessage();
                        if (command_msg.Load(msg))
                        {

                            switch (command_msg.MessageType)
                            {
                                case QlikViewCommandTypes.Connect:
                                    // Get connection string, send "OK" response.
                                    _connectionString = command_msg.GetConnectionString();
                                    _logger.LogMsg(Level.Info, String.Format("Handling QVX_CONNECT. Connection String: {0}", _connectionString));

                                    // TO DO: Test the connection with the Java proxy ? This would be a round trip to 
                                    // the remote server so it could be a performance issue.
                                    String connect_error_msg;
                                    String connect_response_xml = QlikViewMessage.MakeGenericReplyMessage(true);
                                    if (!Properties.Settings.Default.SimulateConnection)
                                    {
                                        Boolean success = TestConnection(_driverName, _connectionString, _userName, _password, out connect_error_msg);
                                        if (!success)
                                        {
                                            connect_response_xml = QlikViewMessage.MakeGenericReplyMessage(false, null, connect_error_msg);
                                        }

                                    }
                                    SendCommandMessage(pipeClientStream, connect_response_xml);

                                    keep_on_processing = true;
                                    break;

                                case QlikViewCommandTypes.Execute:
                                    // Get the SQL and data pipe name from the QVX_EXECUTE message.
                                    command_msg.GetSQLStatementAndDataPipeName(out _sql, out _dataPipePath);
                                    _logger.LogMsg(Level.Debug, String.Format( "Handling QVX_EXECUTE. Data pipe path: [{0}], SQL: [{1}].", _dataPipePath, _sql ));                                        

                                    // Send QVX_OK before writing to data pipe.
                                    String execute_response_xml = QlikViewMessage.MakeGenericReplyMessage(true);
                                    SendCommandMessage(pipeClientStream, execute_response_xml);

                                    // TO DO: need to get driver, connectionstring, username, password
                                    //String drivername = Properties.Settings.Default.dr

                                    // Write data to the data pipe.
                                    if (!String.IsNullOrEmpty(_sql) && !String.IsNullOrEmpty(_dataPipePath))
                                    {
                                        RunBigDataQueryJava(_dataPipePath, _connectionString, _sql);
                                    }
                                    // Keep on procressing - there mat be multiple QVX_EXECUTE - one for each SQL statement in the script. 
                                    keep_on_processing = true;


                                    break;

                                case QlikViewCommandTypes.GenericCommand:
                                    String generic_val;
                                    String generic_response_xml;
                                    command_msg.ParseGenericCommand(out generic_val);
                                    _logger.LogMsg(Level.Debug, String.Format("Handling QVX_GENERIC_COMMAND: {0}", generic_val));
                                    switch( generic_val ) {
                                                                            
                                        case QlikViewGenericCommandValues.GetCustomCaption:
                                            // Send the caption?
                                            if (!Properties.Settings.Default.DisableCustomSQLButton)
                                            {
                                                // This causes QlikView to display the custom SQL button.
                                                generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true, new String[] { _customButtonCaption });
                                            }
                                            else
                                            {
                                                // This reply prevents QlikView from displaying the custom SQL button.
                                                generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(false);                                                
                                            }
                                            SendCommandMessage(pipeClientStream, generic_response_xml);
                                            keep_on_processing = true;
                                            break;

                                        case QlikViewGenericCommandValues.DisableQlikViewSelectButton:
                                            // NOTE: we disable the "Select" button by default since it appears to an ODBC-only thing.
                                            if (Properties.Settings.Default.DisableQlikViewSelectButton)
                                            {
                                                generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true, new String[] { "true" });
                                            }
                                            else
                                            {
                                                generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(false);
                                            }
                                            SendCommandMessage(pipeClientStream, generic_response_xml);
                                            keep_on_processing = true;
                                            break;

                                        case QlikViewGenericCommandValues.HaveStarField:
                                            generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true, new String[] { "true" });
                                            SendCommandMessage(pipeClientStream, generic_response_xml);
                                            keep_on_processing = true;
                                            break;

                                        case QlikViewGenericCommandValues.IsConnected:
                                            generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true, new String[] { "true" });
                                            SendCommandMessage(pipeClientStream, generic_response_xml);
                                            keep_on_processing = true;
                                            break;
                                        
                                        default:
                                            _logger.LogMsg(Level.Warn, String.Format("Unknown QVX_GENERIC_COMMAND type received: [{0}].", generic_val));
                                            
                                            // TO DO: Stop listening because we don't know what to do ? Or just ignore and keep going?
                                            keep_on_processing = false;
                                            break;
                                    }
                                    break;


                                case QlikViewCommandTypes.EditConnect:                                
                                    _logger.LogMsg(Level.Debug, String.Format("Handling QVX_EDIT_CONNECT."));
                                    // TO DO / PROBLEM: the connection string parameter in the message is ALWAYS BLANK. There does not appear
                                    // to be any way to get the connection string that is currently in the QlikView script. This means we cannot
                                    // parse it to get the individual pieces before displaying the dialog. 
                                    String new_connection_string = command_msg.GetConnectionString();
                                    
                                    // Call the dialog and return the modified connection string if OK (skip if Cancel).
                                    
                                    ShowCustomConnectDialog(ref new_connection_string);
                                    if (!String.IsNullOrEmpty(new_connection_string))
                                    {
                                        generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true, new String[] { new_connection_string });
                                    }
                                    else
                                    {
                                        generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(false);
                                    }
                                    SendCommandMessage(pipeClientStream, generic_response_xml);

                                    // Continue listening on the command pipe.                               
                                    keep_on_processing = true;

                                    break;

                                case QlikViewCommandTypes.EditSelect:
                                    _logger.LogMsg(Level.Debug, String.Format("Handling QVX_EDIT_SELECT."));
                                    // Call the dialog and return the modified SQL string.
                                    String new_sql = null;
                                    ShowCustomSQLDialog(ref new_sql);
                                    if( !String.IsNullOrEmpty(new_sql ) )
                                    {
                                        generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true, new String[] { new_sql });
                                    }
                                    else 
                                    {
                                        generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(false);
                                    }
                                    SendCommandMessage(pipeClientStream, generic_response_xml);

                                    // Continue listening on the command pipe.                               
                                    keep_on_processing = true;

                                    break;


                                case QlikViewCommandTypes.Disconnect:
                                    _logger.LogMsg(Level.Debug, String.Format("Handling QVX_DISCONNECT."));
                                    // There is nothing to parse out since this has no defined parameters

                                    // TO DO: Actually do something to disconnect from the data source ?
                                    generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true);
                                    SendCommandMessage(pipeClientStream, generic_response_xml);

                                    // 
                                    keep_on_processing = true;

                                    break;

                                case QlikViewCommandTypes.Terminate:
                                    _logger.LogMsg(Level.Debug, String.Format("Handling QVX_TERMINATE."));
                                    // There is nothing to parse out since this has no defined parameters

                                    // TO DO: Actually do something to shut down the Java query if running (async - not yet implemented).
                                    generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(true);
                                    SendCommandMessage(pipeClientStream, generic_response_xml);

                                    // Exit becaue we are shutting down.
                                    keep_on_processing = false;

                                    break;

                                default:
                                    _logger.LogMsg(Level.Warn, String.Format("Connector does not yet support this command message type: {0}.", command_msg.MessageType)); 
                                    // If we don't know the message type, just exit.
                                    // TO DO: Handle all the other QlikView message types.
                                    generic_response_xml = QlikViewMessage.MakeGenericReplyMessage(QlikViewResponseTypes.QvxUnknownCommand);
                                    SendCommandMessage(pipeClientStream, generic_response_xml);
                                    keep_on_processing = true;
                                    break;
                            }
                        }
                        else
                        {
                            _logger.LogMsg(Level.Error, "Invalid command message format.");
                            keep_on_processing = false;
                        }

                        if (!keep_on_processing)
                        {
                            _logger.LogMsg(Level.Debug, "Exiting message handler loop. Connector program terminating. ");
                        }
                    }

                } // end while (keep_on_processing)

                result = 0;
            }
            catch (Exception ex)
            {
                _logger.LogMsg(Level.Fatal, "Unexpected error in RunNamedPipeHandler(): " + ex.Message);
            }


            return result;
        }

        private static void SendDataMessage(NamedPipeClientStream pipe, String xml)
        {
            try
            {
                // Write data length, string and null
                _logger.LogMsg(Level.Debug, String.Format("Getting byte array from XML string for sending (data pipe)."));
                {
                    // Write data length to stream. This is a 32-bit integer in big endian byte order.
                    int data_len = xml.Length + 1;
                    byte[] ar_bytes = BitConverter.GetBytes(data_len);
                    for (int ii = 0; ii < 4; ii++)
                    {
                        pipe.WriteByte(ar_bytes[3 - ii]);
                    }

                    Debug.WriteLine(String.Format("Writing XML to stream: {0} chars", xml.Length.ToString()));
                    Byte[] xml_buf = GetBytes(xml);
                    for (int ii = 0; ii < xml_buf.Length; ii++)
                    {
                        if (xml_buf[ii] != 0x00)
                        {
                            pipe.WriteByte(xml_buf[ii]);
                        }
                    }
                    Debug.WriteLine("Writing 0x00 to stream.");
                    pipe.WriteByte(0x00);

                    pipe.Flush();

                }
            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in SendDateMessage(): " + ex.Message);
                throw ex;
            }
        }

        public static Boolean TestConnection(String driver, String connectionString, String username, String password, out String errormessage)
        {
            Boolean success = false;
            errormessage = null;
            try
            {
                IMojoHiveDriver hivedriver = CreateCustomDriver(driver);
                String queue = ""; // The queue is actually part of the connection string (url) so we pass a blank.
                int result = hivedriver.TestConnection(driver, connectionString, queue, username, password);

                if (result == 0)
                {
                    success = true;
                }
                else
                {
                    errormessage = hivedriver.GetLastExceptionMessage();
                }
            }
            catch (System.Exception ex)
            {
                Debug.WriteLine("Error in TestConnection(): " + ex.Message);
                
                //throw ex;
            }
            return success;
        }

        public static int RunBigDataQueryJava(String dataPipePath, String connectionString, String sql)
        {
            int result = 0;
            _logger.LogMsg(Level.Debug, "Enter function RunBigDataQueryJava().");

            try
            {
                // The queue name is not necessary as a separate parameter. it is part of the URL. 
                // TO DO: change Java interface so we don't need to pass this parameter at all.
                String unused_queue_name  = "";

                // Create Java proxy
                IMojoHiveDriver driver = CreateCustomDriver(_driverName);

                if (!Properties.Settings.Default.SimulateQuery)
                {
                    // Run the real query via Java / JDBC.
                    _logger.LogMsg(Level.Debug, "Calling Java method QueryResultSetToPipe().");

                    result = driver.QueryResultSetToPipe(_driverName, _connectionString, unused_queue_name, _userName, _password, _sql, _dataPipePath);
                    
                    _logger.LogMsg(Level.Debug, "Return from Java method QueryResultSetToPipe(). Result = " + result.ToString());
                    // DEBUGGING: just write a fixed data file to the pipe instead of running an actual JDBC query.
                }
                else
                {
                    // Load the data from a local data file instead.
                    String data_file = "";
                    if (File.Exists(Properties.Settings.Default.TestDataFilename))
                    {
                        data_file = Properties.Settings.Default.TestDataFilename;
                    }
                    else
                    {
                        String local_path = System.IO.Path.GetDirectoryName(System.Reflection.Assembly.GetExecutingAssembly().Location);
                        data_file = Path.Combine(local_path, Properties.Settings.Default.TestDataFilename);
                    }
                    if (File.Exists(data_file))
                    {
                        _logger.LogMsg(Level.Debug, String.Format("Calling Java method TestNamedPipeWrite() with input data file {0}.", data_file));
                        result = driver.TestNamedPipeWrite(dataPipePath, data_file);
                        _logger.LogMsg(Level.Debug, "Return from Java method TestNamedPipeWrite(). Result = " + result.ToString());
                    }
                    else
                    {
                        _logger.LogMsg(Level.Error, String.Format("Test data file not found: {0}", Properties.Settings.Default.TestDataFilename));
                    }

                    // TO DO: If we can't run the test, do we still have to close the data pipe?

                }

                if (driver.GetLastErrorCode() != 0)
                {
                    _logger.LogMsg(Level.Error, String.Format( "Exception from MojoHiveDriver: {0}.", driver.GetLastExceptionMessage()));
                }


            }
            catch (Exception ex)
            {
                _logger.LogMsg(Level.Fatal, "Unexpected error in RunBigDataQueryJava(): " + ex.Message);
            }


            return result;
        }



        private static Boolean ParsePipePath(String pipePath, out String hostName, out String pipeName)
        {
            Boolean result = false;
            hostName = null;
            pipeName = null;
            try
            {
                // Get the (short) pipe name and host name from the full path. 
                // The .NET functions do not want the full path, only the name. Path format: "\\hostname\pipe\mypipename.pip"
                String[] segments = pipePath.Split('\\');
                if (segments.Length == 5)
                {
                    hostName = segments[segments.Length - 3];
                    pipeName = segments[segments.Length - 1];
                    result = true;
                }
            }
            catch (Exception ex)
            {
                _logger.LogMsg(Level.Error, "Unexpected error in ParsePipePath(): " + ex.Message);
            }
            return result;
        }

        private static void ShowCustomConnectDialog(ref String connectionString)
        {
            connectionString = null;
            var login = new Login();
            var wih = new WindowInteropHelper(login);
            wih.Owner = (IntPtr) _parentWindowHandle ;


            login.ShowDialog();
            if (login.DialogResult.Equals(true))
            {
               // Get the various parameters.
                connectionString = login.ConnectionString;

                // Class variables are also updated
                // TO DO: updating class-level variables here is a bit ugly. Need to refactor.
                _driverName = login.DriverName;
                _password = login.Password;
                _userName = login.Username;

            }
         
            

        }

        private static void ShowCustomSQLDialog(ref String sql)
        {
            sql = null;
            var sqldlg = new CustomSQL();
            var wih = new WindowInteropHelper(sqldlg);
            wih.Owner = (IntPtr)_parentWindowHandle;


            sqldlg.ShowDialog();
            if (sqldlg.DialogResult.Equals(true))
            {
                // Get the various parameters.
                sql = sqldlg.SQL;
            }



        }

    }
}
