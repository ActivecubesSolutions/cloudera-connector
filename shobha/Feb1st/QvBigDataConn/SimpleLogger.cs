using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;
using log4net;
using log4net.Appender;
using log4net.Config;
using log4net.Core;

namespace QvBigDataConn
{


    #region log4net

    internal static class LogConfiguration
    {
        public static readonly string DefaultLayoutPattern = "%date{yyyy-MM-dd HH:mm:ss} [%thread] %-5level - %message%newline";

        // Configure the file logger programmatically. 
        public static void ConfigureFileAppender(ILog log, string logDirPath, Level level)
        {
            bool isConfigured = log.Logger.Repository.GetAppenders().OfType<RollingFileAppender>().Any();
            if (!isConfigured)
            {
                // Setup RollingFileAppender
                RollingFileAppender appender = new RollingFileAppender();
                appender.Layout = new log4net.Layout.PatternLayout(DefaultLayoutPattern);
                appender.MaximumFileSize = "10MB";
                appender.MaxSizeRollBackups = 5;
                appender.RollingStyle = RollingFileAppender.RollingMode.Composite;
                appender.AppendToFile = true;
                // The log path must be specified with a backslash for log4net to detect this as the log directory and not the
                // log file name.
                appender.File = logDirPath[logDirPath.Length - 1] == '\\' ? logDirPath : logDirPath + '\\';
                appender.Name = "RollingFileAppender";
                appender.Threshold = level;
                appender.StaticLogFileName = false;
                appender.DatePattern = String.Format("'{0}'yyyyMMdd'.log'", log.Logger.Name);
                appender.ActivateOptions(); // IMPORTANT, creates the file
                BasicConfigurator.Configure(appender);
                log.Info("Name: " + log.Logger.Name);
#if DEBUG
                // Setup TraceAppender
                TraceAppender ta = new TraceAppender();
                ta.Layout = new log4net.Layout.PatternLayout(DefaultLayoutPattern);
                BasicConfigurator.Configure(ta);
#endif
            }
        }

        // Configure the event log logger programmatically. 
        public static void ConfigureEventLogAppender(ILog log, string sourceName, Level level)
        {
            bool isConfigured = log.Logger.Repository.GetAppenders().OfType<EventLogAppender>().Any();
            if (!isConfigured)
            {
                // Setup RollingFileAppender
                EventLogAppender appender = new EventLogAppender();
                appender.Layout = new log4net.Layout.PatternLayout(@"[%thread] - %message%newline");
                appender.ApplicationName = sourceName;
                appender.Name = "EventLogAppender";
                appender.Threshold = level;
                appender.ActivateOptions();
                BasicConfigurator.Configure(appender);
            }
        }


    }

    #endregion log4net

    public class SimpleLogger
    {
        private ILog _log;

        public SimpleLogger(string sourceName)
        {
            _log = LogManager.GetLogger(sourceName);
        }

        public void EnableFileLogging(string logDirPath, Level levelThreshold)
        {
            if (!System.IO.Directory.Exists(logDirPath)) throw new ArgumentException("Invalid path specified.", logDirPath);

            LogConfiguration.ConfigureFileAppender(_log, logDirPath, levelThreshold);
        }

        public void EnableEventLogLogging(string sourceName, Level levelThreshold)
        {
            LogConfiguration.ConfigureEventLogAppender(_log, sourceName, levelThreshold);
        }

        public void LogMsg(Level level, string message)
        {
            //Debug.WriteLine( String.Format( "{0} {1}", level.ToString(), message ) );

            if (level == Level.Info)
            {
                _log.Info(message);
            }
            else if (level == Level.Debug)
            {
                _log.Debug(message);
            }
            else if (level == Level.Error)
            {
                _log.Error(message);
            }
            else if (level == Level.Warn)
            {
                _log.Warn(message);
            }
            else
            {
                _log.Info(message);
            }

        }
    }
}
