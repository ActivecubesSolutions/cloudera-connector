   M S B A M L   `   `   `  ����   QvBigDataConn    QvBigDataConn.Login   R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml       � Title$  Connect to...��$	��341��$	��580�� � ShowInTaskbar .   � 
ResizeMode$ NoResize=� � WindowStartupLocation$ CenterOwner=� � DataContext �� .��� $	��280�� � ColumnDefinitions �� $
��268* ��� $
��290* �.+��� -   $��okButton��$��Save Changes��$��23��$��
0,0,103,15q�$��Bottom=� �� 	IsDefault . $��Right=�$	��107��$��1���� -   $��button2��$��Cancel��$��23��$��	0,0,12,15q�$��Bottom=� �� IsCancel .  .  $��Right=�$��75��$��1���� -   $��button3��$��Test Connection��$��23��$��
0,242,11,0q�$	��Top=�$��Right=�$	��114���� -   $��serverTextBox��$��23��$
��Left=�$��72,5,0,0q�$	��Top=�$	��159��$��54.218.97.70���� -   $��label3��$��Server:��$��28��$
��Left=�$��12,0,0,0q�$	��Top=�$��50���� -   $��userTextBox��$��23��$
��Left=�$��
72,157,0,0q�$	��Top=�$	��159��$�� ���� -   $��label1��$��User:��$��28��$
��Left=�$��
13,152,0,0q�$	��Top=�$��70���� -   $��label2��$
��Pwd:��$
��Left=�$��12,184,0,90q�$��42��G� -	   $��passwordBox��$��23��$
��Left=�$��
72,189,0,0q�$	��Top=�$	��159�� G� Password$  ���� -
   $��textBoxPort��$��23��$
��Left=�$��	72,34,0,0q�$	��Top=�$��93��$��21050���� -   $��label4��$��Port:��$��28��$��	12,29,0,0q�$	��Top=�$
��Left=�$��48���� -   $��textBoxQueue��$��23��$
��Left=�$��	72,63,0,0q�$	��Top=�$	��159��$��Default���� -   $��label5��$��Queue:��$��28��$
��Left=�$��	12,58,0,0q�$	��Top=��� -   $��textBoxAuth��$��23��$
��Left=�$��	72,92,0,0q�$	��Top=�$	��159��$��auth=noSasl���� -   $��label6��$��Auth:��$��28��$
��Left=�$��	13,88,0,0q�$	��Top=��� -   $��textBoxPrefix��$��23��$
��Left=�$��
72,123,0,0q�$	��Top=�$��93��$��hive2���� -   $��label8��$��Prefix:��$��28��$
��Left=�$��
12,121,0,0q�$	��Top=��� -   $��comboBoxDriver��$��23��$��1��$��
16,29,12,0q�$	��Top=����� .@��� $%��org.apache.hive.jdbc.HiveDriver���� -   $��label7��$��Driver:��$��1��$��28��$
��Left=�$	��Top=��� -   $��label9��$
��Pwd:��$��28��$
��Left=�$��
13,184,0,0q�$	��Top=��� -   $��buttonPreview��$��Preview��$��23��$
��Left=�$��
24,242,0,0q�$	��Top=�$	��105��