namespace QvBigDataConn
{
    partial class QvCustomDialog
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.buttonOK = new System.Windows.Forms.Button();
            this.buttonCancel = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label4 = new System.Windows.Forms.Label();
            this.textBoxQueue = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.textBoxAuth = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.numericUpDownPort = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxHost = new System.Windows.Forms.TextBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.listBoxDriver = new System.Windows.Forms.ListBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.label6 = new System.Windows.Forms.Label();
            this.textBoxPassword = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.textBoxUser = new System.Windows.Forms.TextBox();
            this.buttonURL = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownPort)).BeginInit();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // buttonOK
            // 
            this.buttonOK.Location = new System.Drawing.Point(346, 211);
            this.buttonOK.Name = "buttonOK";
            this.buttonOK.Size = new System.Drawing.Size(75, 23);
            this.buttonOK.TabIndex = 0;
            this.buttonOK.Text = "&Ok";
            this.buttonOK.UseVisualStyleBackColor = true;
            this.buttonOK.Click += new System.EventHandler(this.buttonOK_Click);
            // 
            // buttonCancel
            // 
            this.buttonCancel.Location = new System.Drawing.Point(427, 211);
            this.buttonCancel.Name = "buttonCancel";
            this.buttonCancel.Size = new System.Drawing.Size(75, 23);
            this.buttonCancel.TabIndex = 1;
            this.buttonCancel.Text = "&Cancel";
            this.buttonCancel.UseVisualStyleBackColor = true;
            this.buttonCancel.Click += new System.EventHandler(this.buttonCancel_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.textBoxQueue);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.textBoxAuth);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.numericUpDownPort);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.textBoxHost);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(283, 134);
            this.groupBox1.TabIndex = 2;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "URL";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(5, 99);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(42, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Queue:";
            // 
            // textBoxQueue
            // 
            this.textBoxQueue.Location = new System.Drawing.Point(53, 96);
            this.textBoxQueue.Name = "textBoxQueue";
            this.textBoxQueue.Size = new System.Drawing.Size(210, 20);
            this.textBoxQueue.TabIndex = 6;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(15, 73);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(32, 13);
            this.label3.TabIndex = 5;
            this.label3.Text = "Auth:";
            // 
            // textBoxAuth
            // 
            this.textBoxAuth.Location = new System.Drawing.Point(53, 70);
            this.textBoxAuth.Name = "textBoxAuth";
            this.textBoxAuth.Size = new System.Drawing.Size(210, 20);
            this.textBoxAuth.TabIndex = 4;
            this.textBoxAuth.Text = "auth=noSasl";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(15, 45);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(29, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Port:";
            // 
            // numericUpDownPort
            // 
            this.numericUpDownPort.Location = new System.Drawing.Point(53, 43);
            this.numericUpDownPort.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.numericUpDownPort.Name = "numericUpDownPort";
            this.numericUpDownPort.Size = new System.Drawing.Size(72, 20);
            this.numericUpDownPort.TabIndex = 2;
            this.numericUpDownPort.Value = new decimal(new int[] {
            21050,
            0,
            0,
            0});
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(15, 16);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(32, 13);
            this.label1.TabIndex = 1;
            this.label1.Text = "Host:";
            // 
            // textBoxHost
            // 
            this.textBoxHost.Location = new System.Drawing.Point(53, 16);
            this.textBoxHost.Name = "textBoxHost";
            this.textBoxHost.Size = new System.Drawing.Size(210, 20);
            this.textBoxHost.TabIndex = 0;
            this.textBoxHost.Text = "54.218.97.70";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.listBoxDriver);
            this.groupBox2.Location = new System.Drawing.Point(302, 13);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(200, 134);
            this.groupBox2.TabIndex = 3;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Driver";
            // 
            // listBoxDriver
            // 
            this.listBoxDriver.FormattingEnabled = true;
            this.listBoxDriver.Items.AddRange(new object[] {
            "org.apache.hive.jdbc.HiveDriver"});
            this.listBoxDriver.Location = new System.Drawing.Point(7, 20);
            this.listBoxDriver.Name = "listBoxDriver";
            this.listBoxDriver.Size = new System.Drawing.Size(187, 69);
            this.listBoxDriver.TabIndex = 0;
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.label6);
            this.groupBox3.Controls.Add(this.textBoxPassword);
            this.groupBox3.Controls.Add(this.label5);
            this.groupBox3.Controls.Add(this.textBoxUser);
            this.groupBox3.Location = new System.Drawing.Point(12, 153);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(283, 81);
            this.groupBox3.TabIndex = 4;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Security";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(7, 53);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(31, 13);
            this.label6.TabIndex = 3;
            this.label6.Text = "Pwd:";
            this.label6.Click += new System.EventHandler(this.label6_Click);
            // 
            // textBoxPassword
            // 
            this.textBoxPassword.Location = new System.Drawing.Point(53, 47);
            this.textBoxPassword.Name = "textBoxPassword";
            this.textBoxPassword.PasswordChar = '*';
            this.textBoxPassword.Size = new System.Drawing.Size(210, 20);
            this.textBoxPassword.TabIndex = 2;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(7, 20);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(32, 13);
            this.label5.TabIndex = 1;
            this.label5.Text = "User:";
            // 
            // textBoxUser
            // 
            this.textBoxUser.Location = new System.Drawing.Point(53, 20);
            this.textBoxUser.Name = "textBoxUser";
            this.textBoxUser.Size = new System.Drawing.Size(210, 20);
            this.textBoxUser.TabIndex = 0;
            // 
            // buttonURL
            // 
            this.buttonURL.Location = new System.Drawing.Point(427, 153);
            this.buttonURL.Name = "buttonURL";
            this.buttonURL.Size = new System.Drawing.Size(75, 23);
            this.buttonURL.TabIndex = 5;
            this.buttonURL.Text = "Test URL";
            this.buttonURL.UseVisualStyleBackColor = true;
            this.buttonURL.Click += new System.EventHandler(this.buttonURL_Click);
            // 
            // QvCustomDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(508, 246);
            this.Controls.Add(this.buttonURL);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.buttonCancel);
            this.Controls.Add(this.buttonOK);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "QvCustomDialog";
            this.Text = "QvCustomDialog";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownPort)).EndInit();
            this.groupBox2.ResumeLayout(false);
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button buttonOK;
        private System.Windows.Forms.Button buttonCancel;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textBoxQueue;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox textBoxAuth;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.NumericUpDown numericUpDownPort;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBoxHost;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.ListBox listBoxDriver;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox textBoxPassword;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox textBoxUser;
        private System.Windows.Forms.Button buttonURL;
    }
}