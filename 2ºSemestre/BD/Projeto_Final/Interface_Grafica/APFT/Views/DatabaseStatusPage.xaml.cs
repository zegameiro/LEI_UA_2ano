using System.ComponentModel;
using APFT.ViewModels;
using Microsoft.Data.SqlClient;
using Microsoft.UI;
using Microsoft.UI.Xaml.Media;
using Microsoft.Windows.ApplicationModel.Resources;
using Windows.Storage;
using Windows.System;
using Microsoft.UI.Xaml.Input;

namespace APFT.Views;

public sealed partial class DatabaseStatusPage : INotifyPropertyChanged
{
    public DatabaseStatusViewModel ViewModel
    {
        get;
    }

    public event PropertyChangedEventHandler? PropertyChanged;

    private void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    public static readonly string ServerAddress = "tcp:mednat.ieeta.pt\\SQLSERVER,8101";

    private SolidColorBrush _iconBadgeColor;
    public SolidColorBrush IconBadgeColor
    {
        get => _iconBadgeColor;
        set
        {
            _iconBadgeColor = value;
            OnPropertyChanged(nameof(IconBadgeColor));
        }
    }

    private string _connectionStatus;
    public string ConnectionStatus
    {
        get => _connectionStatus;
        set
        {
            _connectionStatus = value;
            OnPropertyChanged(nameof(ConnectionStatus));
        }
    }

    private string _database;
    public string Database
    {
        get => _database;
        set
        {
            _database = value;
            OnPropertyChanged(nameof(Database));
        }
    }

    private string _username;
    public string Username
    {
        get => _username;
        set
        {
            _username = value;
            OnPropertyChanged(nameof(Username));
        }
    }

    private string _password;
    public string Password
    {
        get => _password;
        set
        {
            _password = value;
            OnPropertyChanged(nameof(Password));
        }
    }

    public DatabaseStatusPage()
    {
        LoadStoredValues();
        ViewModel = App.GetService<DatabaseStatusViewModel>();
        InitializeComponent();
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "DatabaseStatus");

    private void LoadStoredValues()
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        var colorString = "#FFFF0000";
        if (localSettings.Values.TryGetValue("IconBadgeColor", out var value))
        {
            colorString = value.ToString();
        }
        IconBadgeColor = new SolidColorBrush(colorString == "#FF008000" ? Colors.Green : Colors.Red);

        ConnectionStatus = _resourceLoader.GetString("New");
        if (localSettings.Values.TryGetValue("ConnectionStatus", out value))
        {
            ConnectionStatus = value?.ToString() ?? _resourceLoader.GetString("New");
        }

        Database = "p5g3";
        if (localSettings.Values.TryGetValue("Database", out value))
        {
            Database = value?.ToString() ?? "p5g3";
        }

        Username = "p5g3";
        if (localSettings.Values.TryGetValue("Username", out value))
        {
            Username = value?.ToString() ?? "p5g3";
        }

        Password = "";
        if (localSettings.Values.TryGetValue("Password", out value))
        {
            Password = value?.ToString() ?? "";
        }
    }

    private void SavePropertyValues()
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        localSettings.Values["IconBadgeColor"] = IconBadgeColor.Color.ToString();
        localSettings.Values["ConnectionStatus"] = ConnectionStatus;
        localSettings.Values["Database"] = Database;
        localSettings.Values["Username"] = Username;
        localSettings.Values["Password"] = Password;
        localSettings.Values["SQLConnectionString"] = "Data Source = " + ServerAddress + "; " +
                                                      "Initial Catalog = " + Database + "; uid = " + Username + "; " +
                                                      "password = " + Password + "; TrustServerCertificate = True";
    }

    private async void TestDbConnection(string dbServer, string dbName, string userName, string userPass)
    {
        await using var cn = new SqlConnection("Data Source = " + dbServer + "; " +
                                         "Initial Catalog = " + dbName + "; uid = " + userName + "; " +
                                         "password = " + userPass + "; TrustServerCertificate = True");
        try
        {
            await cn.OpenAsync();
            ShellPage.Current.SetInfoBadgeColor(Colors.Green);
            IconBadgeColor = new SolidColorBrush(Colors.Green);
            ConnectionStatus = string.Format(_resourceLoader.GetString("Success"), cn.Database, cn.DataSource);
        }
        catch (Exception ex)
        {
            ShellPage.Current.SetInfoBadgeColor(Colors.Red);
            IconBadgeColor = new SolidColorBrush(Colors.Red);
            ConnectionStatus = string.Format(_resourceLoader.GetString("Error"), cn.Database, ex.Message);
        }

        SavePropertyValues();
    }

    private void TestConnectionButton_Click(object sender, Microsoft.UI.Xaml.RoutedEventArgs e) => TestDbConnection(ServerAddress, DatabaseTextBox.Text, UserTextBox.Text, PasswordBox.Password);

    private void TextBoxes_OnKeyDown(object sender, KeyRoutedEventArgs e)
    {
        if (e.Key == VirtualKey.Enter)
        {
            TestDbConnection(ServerAddress, DatabaseTextBox.Text, UserTextBox.Text, PasswordBox.Password);
        }
    }
}
