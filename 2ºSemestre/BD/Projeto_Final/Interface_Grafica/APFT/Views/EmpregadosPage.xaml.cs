using System.Diagnostics;
using APFT.ViewModels;
using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Navigation;
using Windows.Storage;
using Microsoft.Data.SqlClient;
using Microsoft.UI;
using Microsoft.UI.Xaml;
using Microsoft.Windows.ApplicationModel.Resources;
using WinUICommunity;
using APFT.Entities;

namespace APFT.Views;

public sealed partial class EmpregadosPage : Page
{
    public EmpregadosViewModel ViewModel
    {
        get;
    }

    public List<string> Genders { get; set; }

    public EmpregadosPage()
    {
        ViewModel = App.GetService<EmpregadosViewModel>();
        InitializeComponent();
        Genders = new List<string>
        {
            _resourceLoader.GetString("Gender_All"),
            _resourceLoader.GetString("Gender_Male"),
            _resourceLoader.GetString("Gender_Female"),
            _resourceLoader.GetString("Gender_NB"),
            _resourceLoader.GetString("Gender_Other")
        };
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "Employees");

    protected async override void OnNavigatedTo(NavigationEventArgs e)
    {
        try
        {
            EmployeesCVS.Source = await Employee.GetEmployeesGroupedAsync();
            FetchingDataGrid.Visibility = Visibility.Collapsed;
        }
        catch (Exception ex)
        {
            Debug.WriteLine(ex.Message);
            ShellPage.Current.SetInfoBadgeColor(Colors.Red);
            FetchingDataGrid.Visibility = Visibility.Collapsed;
            FetchingDataGridError.Visibility = Visibility.Visible;

            var dialog = new ContentDialog
            {
                XamlRoot = ContentArea.XamlRoot,
                Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
                Title = _resourceLoader.GetString("ContentDialog_Title"),
                PrimaryButtonText = _resourceLoader.GetString("ContentDialog_PrimaryButtonText"),
                DefaultButton = ContentDialogButton.Primary,
                Content = string.Format(_resourceLoader.GetString("ContentDialog_Content"), ex.Message)
            };
            _ = await dialog.ShowAsyncQueue();
        }
    }

    private void ListViewBase_OnItemClick(object sender, ItemClickEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        localSettings.Values["EmployeeNif"] = ((Employee)e.ClickedItem).Nif.ToString();

        Frame.Navigate(typeof(EmployeeDetailsPage));
    }
    

    private async void AddButton_OnClick(object sender, RoutedEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        try
        {
            await Employee.AddEmployee(
                int.Parse(NifTextBox.Text),
                FNameTextBox.Text,
                LNameTextBox.Text,
                EmailTextBox.Text,
                int.Parse(PhoneTextBox.Text),
                AddressTextBox.Text,
                string.Empty,
                string.Empty,
                "750",
                int.Parse(DepartmentIdTextBox.Text));

            localSettings.Values["EmployeeNif"] = NifTextBox.Text;
            Frame.Navigate(typeof(EmployeeDetailsPage));
        }
        catch (Exception ex)
        {
            var dialog = new ContentDialog
            {
                XamlRoot = ContentArea.XamlRoot,
                Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
                Title = _resourceLoader.GetString("AddErrorCD_Title"),
                PrimaryButtonText = _resourceLoader.GetString("ContentDialog_PrimaryButtonText"),
                DefaultButton = ContentDialogButton.Primary,
                Content = string.Format(_resourceLoader.GetString("AddErrorCD_Content"), ex.Message)
            };
            _ = await dialog.ShowAsyncQueue();
        }
    }

    private async void AutoSuggestBox_OnTextChanged(AutoSuggestBox sender, AutoSuggestBoxTextChangedEventArgs args)
    {
        // Since selecting an item will also change the text,
        // only listen to changes caused by user entering text.
        if(args.Reason == AutoSuggestionBoxTextChangeReason.UserInput)
        {
            if (sender.Text.Length >= 3)
            {
                var suitableItems = new List<string>();
                var localSettings = ApplicationData.Current.LocalSettings;

                await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
                await cn.OpenAsync();
                var cmd = new SqlCommand("SELECT * FROM getEmpregadoByName('" + sender.Text.ToLower() + "')", cn);

                var reader = await cmd.ExecuteReaderAsync();

                while (await reader.ReadAsync())
                {
                    suitableItems.Add(reader.GetString(1) + " " + reader.GetString(2) + " (" + reader.GetInt32(0) + ")");
                }

                if(suitableItems.Count == 0)
                {
                    suitableItems.Add(_resourceLoader.GetString("AutoSuggestBox_NotFound"));
                }

                sender.ItemsSource = suitableItems;
            }
            else
            {
                sender.ItemsSource = null;
            }
        }
    }

    private void AutoSuggestBox_OnSuggestionChosen(AutoSuggestBox sender, AutoSuggestBoxSuggestionChosenEventArgs args)
    {
        var person = args.SelectedItem.ToString() ?? "(0)";
        if (person == _resourceLoader.GetString("AutoSuggestBox_NotFound"))
        {
            SearchBox.Text = "";
            return;
        }

        var nif = person.Split('(', ')')[1].Trim();

        var localSettings = ApplicationData.Current.LocalSettings;
        localSettings.Values["EmployeeNif"] = nif;

        Frame.Navigate(typeof(EmployeeDetailsPage));
    }

    //
    // Filter
    //

    private string _gender;
    public string Gender
    {
        get => _gender != _resourceLoader.GetString("Gender_All") ? _gender[0].ToString() : string.Empty;
        set => _gender = value;
    }

    private decimal _salary;
    public string Salary
    {
        get => _salary.ToString().Replace(',', '.');
        set => _salary = value == "" ? 0 : decimal.Parse(value);
    }

    private DateTime? BirthDate
    {
        get;
        set;
    }
    public string BirthDateString => BirthDate != null ? BirthDate.Value.Year + "-" + BirthDate.Value.Month + "-" + BirthDate.Value.Day : string.Empty;

    private async void GenderComboBox_OnSelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        Gender = (string)((ComboBox)sender).SelectedItem;
        EmployeesCVS.Source = await Employee.GetEmployeesFilteredGroupedAsync(Gender, BirthDateString, Salary);
    }

    private async void SalaryTextBox_OnTextChanged(object sender, TextChangedEventArgs e)
    {
        Salary = ((TextBox)sender).Text;
        EmployeesCVS.Source = await Employee.GetEmployeesFilteredGroupedAsync(Gender, BirthDateString, Salary);
    }

    private async void CalendarDatePicker_OnDateChanged(CalendarDatePicker sender, CalendarDatePickerDateChangedEventArgs args)
    {
        BirthDate = sender.Date != null ? sender.Date.Value.DateTime : null;
        EmployeesCVS.Source = await Employee.GetEmployeesFilteredGroupedAsync(Gender, BirthDateString, Salary);
    }

    private void ClearFilterButton_OnClick(object sender, RoutedEventArgs e)
    {
        GenderComboBox.SelectedItem = "All";
        CalendarDatePicker.Date = null;
        SalaryTextBox.Text = null;
    }
}
