using System;
using System.Net.NetworkInformation;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using MessageBox = System.Windows.Forms.MessageBox;

namespace QvBigDataConn
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Window
    {
        private String _connectionString; 
        public String ConnectionString
        {
            get
            {
                CreateConnectionString();
                return _connectionString;
            }
            set
            {
                _connectionString = value;
                // TO DO: Parse the string and initialize the text boxes based on the segments of the input connection string.
            }
        }

        public String DriverName
        {
            get
            {
                return comboBoxDriver.SelectedItem.ToString();
            }
        }


        public Login()
        {
            _connectionString = null;
            InitializeComponent();
            
            // Subscribe to Loaded event.
            Loaded += Login_Loaded;
        }

        private void Login_Loaded(object sender, RoutedEventArgs e)
        {

            serverTextBox.Text = Properties.Settings.Default.Hostname;
            textBoxPort.Text = Properties.Settings.Default.Port.ToString();
            textBoxAuth.Text = Properties.Settings.Default.Auth;
            textBoxQueue.Text = Properties.Settings.Default.Queue;
            userTextBox.Text = Properties.Settings.Default.User;
            passwordBox.Password = Properties.Settings.Default.Password;
            textBoxPrefix.Text = Properties.Settings.Default.UrlPrefix;

            // TO DO: make sure we have at least one valid driver in the list?
            comboBoxDriver.Items.Clear();
            if (Properties.Settings.Default.Drivers.Count == 0)
            {
                // TO DO: remove this hard-coded default.
                Properties.Settings.Default.Drivers.Add("org.apache.hive.jdbc.HiveDriver");
            }
            else
            {
                comboBoxDriver.Items.Clear();
                for (int ii = 0; ii < Properties.Settings.Default.Drivers.Count; ii++)
                {
                    comboBoxDriver.Items.Add(Properties.Settings.Default.Drivers[ii]);
                }
            }
            // Must remember the last selected driver.
            comboBoxDriver.SelectedIndex = 0;
            if (Properties.Settings.Default.LastDriver <= comboBoxDriver.Items.Count)
            {
                comboBoxDriver.SelectedIndex = Properties.Settings.Default.LastDriver;
            }


        }


        private void testButton_Click(object sender, RoutedEventArgs e)
        {
            System.Windows.Input.Cursor mycursor = this.Cursor;
            try
            {
                String mydriver;
                String msg = null;

                mydriver = comboBoxDriver.SelectedItem.ToString();

                this.Cursor = System.Windows.Input.Cursors.Wait;
                Boolean success = Program.TestConnection(mydriver, ConnectionString, userTextBox.Text.Trim(), passwordBox.Password.Trim(), out msg);
                this.Cursor = mycursor;
                if (success)
                {
                    MessageBox.Show(String.Format("Connection SUCCESS.\r\n\r\ndriver:\t{0}\r\nurl:\t{1}", mydriver, ConnectionString), this.Title, System.Windows.Forms.MessageBoxButtons.OK);
                }
                else
                {
                    MessageBox.Show(String.Format("Connection FAILED.\r\n\r\ndriver:\t{0}\r\nurl:\t{1}\r\n\r\n{2}", mydriver, ConnectionString, msg), this.Title, System.Windows.Forms.MessageBoxButtons.OK);
                }


            }
            catch (Exception ex)
            {
                this.Cursor = mycursor;
                MessageBox.Show(String.Format("Unexpected error: {0}\r\n\r\nurl:\t{1}", ConnectionString, ex.Message), this.Title, System.Windows.Forms.MessageBoxButtons.OK);
            }
        }

        public string Hostname
        {
            get
            {
                return serverTextBox.Text.Trim();
            }
        }

        public string Username
        {
            get
            {
                return userTextBox.Text.Trim();
            }
        }

        public string Password
        {
            get 
            {
            return passwordBox.Password.Trim();
            }
        }



        private void CreateConnectionString()
        {
 
            // TO DO: handle various connection string types based on driver ? 
            // TO DO: User name and password. Where does this go?
            _connectionString = String.Format("jdbc:{0}://{1}:{2}/{3};{4}", textBoxPrefix.Text.Trim(), serverTextBox.Text.Trim(), textBoxPort.Text.Trim(), textBoxQueue.Text.Trim(), textBoxAuth.Text.Trim());
      
        }

        private void okButton_Click(object sender, RoutedEventArgs e)
        {
            // Save the settings
            // TO DO: validate settings first ?

            Properties.Settings.Default.Hostname = serverTextBox.Text.Trim();
            try
            {
                Properties.Settings.Default.Port = Int32.Parse(textBoxPort.Text);
            }
            catch (Exception) { };
            Properties.Settings.Default.Queue = textBoxQueue.Text.Trim();
            Properties.Settings.Default.Auth = textBoxAuth.Text.Trim();
            Properties.Settings.Default.User = userTextBox.Text.Trim();
            Properties.Settings.Default.Password = passwordBox.Password.Trim();
            Properties.Settings.Default.UrlPrefix = textBoxPrefix.Text.Trim();
            // Note: no need to save the driver values since the user cannot edit these. 
            // But we can save the last driver index so it can be selected again when first displayed.
            Properties.Settings.Default.LastDriver = comboBoxDriver.SelectedIndex;


            Properties.Settings.Default.Save();

            DialogResult = true;
            Close();
        }

        private void textBoxPort_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox textBox = sender as TextBox;
            int iValue = -1;

            if (Int32.TryParse(textBox.Text, out iValue) == false)
            {
                foreach (TextChange textChange in e.Changes)
                {
                    int iAddedLength = textChange.AddedLength;
                    int iOffset = textChange.Offset;
                    textBox.Text = textBox.Text.Remove(iOffset, iAddedLength);
                    break;
                }
         
            }
        }

        private void buttonPreview_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show(ConnectionString, this.Title, System.Windows.Forms.MessageBoxButtons.OK);
        }

    
    }
}
