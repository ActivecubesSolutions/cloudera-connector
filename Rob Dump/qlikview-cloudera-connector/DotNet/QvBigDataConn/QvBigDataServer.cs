using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using QlikView.Qvx.QvxLibrary;
using System.Windows.Interop;

namespace QvBigDataConn
{
    internal class QvBigDataServer : QvxServer
    {
        public override QvxConnection CreateConnection()
        {
            return new QvBigDataConnection();
        }

        public override string CreateConnectionString()
        {
            QvxLog.Log(QvxLogFacility.Application, QvxLogSeverity.Notice, "CreateConnectionString()");

            var login = CreateLoginWindowHelper();
            login.ShowDialog();

            string connectionString = null;
            if (login.DialogResult.Equals(true))
            {
                connectionString = String.Format("Server={0};UserId={1};Password={2}",
                    login.Hostname, login.Username, login.Password);
            }

            return connectionString;
        }

        private Login CreateLoginWindowHelper()
        {
            // Since the owner of the loginWindow is a Win32 process we need to
            // use WindowInteropHelper to make it modal to its owner.
            var login = new Login();
            var wih = new WindowInteropHelper(login);
            wih.Owner = MParentWindow;

            return login;
        }
    }
}
