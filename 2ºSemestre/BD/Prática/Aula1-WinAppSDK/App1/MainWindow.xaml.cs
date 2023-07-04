// Copyright (c) Microsoft Corporation and Contributors.
// Licensed under the MIT License.

using Microsoft.UI.Composition.SystemBackdrops;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using System;
using System.Data;
using System.Data.SqlClient;
using System.Runtime.InteropServices; // For DllImport
using System.Threading.Tasks;
using WinRT; // required to support Window.As<ICompositionSupportsSystemBackdrop>()

// To learn more about WinUI, the WinUI project structure,
// and more about our project templates, see: http://aka.ms/winui-project-info.

namespace App1
{
    /// <summary>
    /// An empty window that can be used on its own or navigated to within a Frame.
    /// </summary>

    public sealed partial class MainWindow : Window
    {
        WindowsSystemDispatcherQueueHelper m_wsdqHelper; // See below for implementation.
        MicaController m_backdropController;
        SystemBackdropConfiguration m_configurationSource;

        public MainWindow()
        {
            this.InitializeComponent();
            Window window = this;
            window.ExtendsContentIntoTitleBar = true;  // enable custom titlebar
            window.SetTitleBar(AppTitleBar);      // set user ui element as titlebar
            
            TrySetSystemBackdrop();
        }

        //
        // Mica-related functions
        //

        bool TrySetSystemBackdrop()
        {
            if (Microsoft.UI.Composition.SystemBackdrops.MicaController.IsSupported())
            {
                m_wsdqHelper = new WindowsSystemDispatcherQueueHelper();
                m_wsdqHelper.EnsureWindowsSystemDispatcherQueueController();

                // Create the policy object.
                m_configurationSource = new SystemBackdropConfiguration();
                this.Activated += Window_Activated;
                this.Closed += Window_Closed;
                ((FrameworkElement)this.Content).ActualThemeChanged += Window_ThemeChanged;

                // Initial configuration state.
                m_configurationSource.IsInputActive = true;
                SetConfigurationSourceTheme();

                m_backdropController = new Microsoft.UI.Composition.SystemBackdrops.MicaController();
    
            // Enable the system backdrop.
            // Note: Be sure to have "using WinRT;" to support the Window.As<...>() call.
            m_backdropController.AddSystemBackdropTarget(this.As<Microsoft.UI.Composition.ICompositionSupportsSystemBackdrop>());
                m_backdropController.SetSystemBackdropConfiguration(m_configurationSource);
                return true; // succeeded
            }

            return false; // Mica is not supported on this system
        }

        private void Window_Activated(object sender, WindowActivatedEventArgs args)
        {
            m_configurationSource.IsInputActive = args.WindowActivationState != WindowActivationState.Deactivated;
        }

        private void Window_Closed(object sender, WindowEventArgs args)
        {
            // Make sure any Mica/Acrylic controller is disposed
            // so it doesn't try to use this closed window.
            if (m_backdropController != null)
            {
                m_backdropController.Dispose();
                m_backdropController = null;
            }
            this.Activated -= Window_Activated;
            m_configurationSource = null;
        }

        private void Window_ThemeChanged(FrameworkElement sender, object args)
        {
            if (m_configurationSource != null)
            {
                SetConfigurationSourceTheme();
            }
        }

        private void SetConfigurationSourceTheme()
        {
            switch (((FrameworkElement)this.Content).ActualTheme)
            {
                case ElementTheme.Dark: m_configurationSource.Theme = Microsoft.UI.Composition.SystemBackdrops.SystemBackdropTheme.Dark; break;
                case ElementTheme.Light: m_configurationSource.Theme = Microsoft.UI.Composition.SystemBackdrops.SystemBackdropTheme.Light; break;
                case ElementTheme.Default: m_configurationSource.Theme = Microsoft.UI.Composition.SystemBackdrops.SystemBackdropTheme.Default; break;
            }
        }

        //
        // Button click functions
        //

        private async void TestButton_Click(object sender, RoutedEventArgs e)
        {
            // Enable a ProgressRing on the button (instead of an icon)
            testButton_icon.Visibility = Visibility.Collapsed;
            testButton_ring.Visibility = Visibility.Visible;

            string sqlmessage = await TestDBConnection(addressTextBox.Text, userTextBox.Text, userTextBox.Text, passwordBox.Password);

            // Restore icon, since data is already fetched
            testButton_icon.Visibility = Visibility.Visible;
            testButton_ring.Visibility = Visibility.Collapsed;

            // Create a ContentDialog with the output
            ContentDialog dialog = new ContentDialog();
            dialog.XamlRoot = buttonsGrid.XamlRoot;
            dialog.Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style;
            dialog.Title = "Connection test";
            dialog.PrimaryButtonText = "OK";
            dialog.DefaultButton = ContentDialogButton.Primary;
            dialog.Content = sqlmessage;

            var result = await dialog.ShowAsync();
        }

        private async void GetContentButton_Click(object sender, RoutedEventArgs e)
        {
            // Enable a ProgressRing on the button (instead of an icon)
            getContentButton_icon.Visibility = Visibility.Collapsed;
            getContentButton_ring.Visibility = Visibility.Visible;

            string sqlmessage = await GetTableContent(addressTextBox.Text, userTextBox.Text, userTextBox.Text, passwordBox.Password);

            // Restore icon, since data is already fetched
            getContentButton_icon.Visibility = Visibility.Visible;
            getContentButton_ring.Visibility = Visibility.Collapsed;

            // Create a ContentDialog with the output
            ContentDialog dialog = new ContentDialog();
            dialog.XamlRoot = buttonsGrid.XamlRoot;
            dialog.Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style;
            dialog.Title = "Table content";
            dialog.PrimaryButtonText = "OK";
            dialog.DefaultButton = ContentDialogButton.Primary;
            dialog.Content = sqlmessage;

            var result = await dialog.ShowAsync();
        }

        //
        // SQL internal functions
        //

        private async Task<string> TestDBConnection(string dbServer, string dbName, string userName, string userPass)
        {
            string str = "";
            SqlConnection CN = new SqlConnection("Data Source = " + dbServer + " ;" + "Initial Catalog = " + dbName +
                                                       "; uid = " + userName + ";" + "password = " + userPass);

            try
            {
                await CN.OpenAsync();
                if (CN.State == ConnectionState.Open)
                {
                    str = "Successful connection to database " + CN.Database + " on the " + CN.DataSource + " server";
                }
            }
            catch (Exception ex)
            {
                str = "Failed to open connection to database due to the error \r\n" + ex.Message;
            }

            if (CN.State == ConnectionState.Open)
                CN.Close();

            return str;
        }

        private async Task<string> GetTableContent(string dbServer, string dbName, string userName, string userPass)
        {
            string str = "";
            SqlConnection CN = new SqlConnection("Data Source = " + dbServer + " ;" + "Initial Catalog = " + dbName +
                                                       "; uid = " + userName + ";" + "password = " + userPass);

            try
            {
                await CN.OpenAsync();
                if (CN.State == ConnectionState.Open)
                {
                    int cnt = 1;
                    SqlCommand sqlcmd = new SqlCommand("SELECT * FROM Hello", CN);
                    SqlDataReader reader;
                    reader = sqlcmd.ExecuteReader();

                    while (reader.Read())
                    {
                        str += cnt.ToString() + " - " + reader.GetInt32(reader.GetOrdinal("MsgID")) + ", ";
                        str += reader.GetString(reader.GetOrdinal("MsgSubject"));
                        str += "\n";
                        cnt += 1;
                    }
                }
            }
            catch (Exception ex)
            {
                str = "Failed to open connection to database due to the error \r\n" + ex.Message;
            }

            if (CN.State == ConnectionState.Open)
                CN.Close();

            return str;
        }
    }

    // Mica-related class
    class WindowsSystemDispatcherQueueHelper
    {
        [StructLayout(LayoutKind.Sequential)]
        struct DispatcherQueueOptions
        {
            internal int dwSize;
            internal int threadType;
            internal int apartmentType;
        }

        [DllImport("CoreMessaging.dll")]
        private static extern int CreateDispatcherQueueController([In] DispatcherQueueOptions options, [In, Out, MarshalAs(UnmanagedType.IUnknown)] ref object dispatcherQueueController);

        object m_dispatcherQueueController = null;
        public void EnsureWindowsSystemDispatcherQueueController()
        {
            if (Windows.System.DispatcherQueue.GetForCurrentThread() != null)
            {
                // one already exists, so we'll just use it.
                return;
            }

            if (m_dispatcherQueueController == null)
            {
                DispatcherQueueOptions options;
                options.dwSize = Marshal.SizeOf(typeof(DispatcherQueueOptions));
                options.threadType = 2;    // DQTYPE_THREAD_CURRENT
                options.apartmentType = 2; // DQTAT_COM_STA

                CreateDispatcherQueueController(options, ref m_dispatcherQueueController);
            }
        }
    }
}
