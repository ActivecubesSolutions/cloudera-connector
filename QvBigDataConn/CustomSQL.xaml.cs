using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace QvBigDataConn
{
    /// <summary>
    /// Interaction logic for CustomSQL.xaml
    /// </summary>
    public partial class CustomSQL : Window
    {
        public String SQL
        {
            get
            {
                return textBoxSQL.Text;
            }
            set
            {
                textBoxSQL.Text = value;
            }
        }

        public CustomSQL()
        {
            InitializeComponent();
        }

        private void buttonOk_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = true;
            Close();
        }

        private void buttonCancel_Click(object sender, RoutedEventArgs e)
        {
            DialogResult = false;
            Close();
        }
    }
}
