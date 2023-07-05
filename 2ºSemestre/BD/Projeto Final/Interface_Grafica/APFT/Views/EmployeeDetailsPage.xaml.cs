using APFT.Entities;
using System.ComponentModel;
using System.Data.SqlTypes;
using System.Diagnostics;
using APFT.ViewModels;

using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Media.Animation;
using Windows.Storage;
using Microsoft.UI.Xaml;
using Microsoft.Windows.ApplicationModel.Resources;
using WinUICommunity;

namespace APFT.Views;

public sealed partial class EmployeeDetailsPage : INotifyPropertyChanged
{
    public EmployeeDetailsViewModel ViewModel
    {
        get;
    }

    public event PropertyChangedEventHandler? PropertyChanged;

    private void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    private int _nif;
    public int Nif
    {
        get => _nif;
        set
        {
            _nif = value;
            OnPropertyChanged(nameof(Nif));
        }
    }

    private string _firstName;
    public string FirstName
    {
        get => _firstName;
        set
        {
            _firstName = value;
            OnPropertyChanged(nameof(FirstName));
        }
    }

    private string _lastName;
    public string LastName
    {
        get => _lastName;
        set
        {
            _lastName = value;
            OnPropertyChanged(nameof(LastName));
        }
    }

    private string? _email;
    public string? Email
    {
        get => _email;
        set
        {
            _email = value;
            OnPropertyChanged(nameof(Email));
        }
    }

    private int? _phone;
    public int? Phone
    {
        get => _phone;
        set
        {
            _phone = value;
            OnPropertyChanged(nameof(Phone));
        }
    }

    private string? _address;
    public string? Address
    {
        get => _address;
        set
        {
            _address = value;
            OnPropertyChanged(nameof(Address));
        }
    }

    private string? _gender;
    public string? Gender
    {
        get => _gender;
        set
        {
            _gender = value;
            OnPropertyChanged(nameof(Gender));
        }
    }

    private DateTime? _birthDate;
    public DateTime? BirthDate
    {
        get => _birthDate;
        set
        {
            _birthDate = value;
            OnPropertyChanged(nameof(BirthDate));
        }
    }

    private decimal? _salary;
    public decimal? Salary
    {
        get => _salary;
        set
        {
            _salary = value;
            OnPropertyChanged(nameof(Salary));
        }
    }

    private int? _departmentId;
    public int? DepartmentId
    {
        get => _departmentId;
        set
        {
            _departmentId = value;
            OnPropertyChanged(nameof(DepartmentId));
        }
    }
    
    public string BirthDateString => BirthDate != null ? BirthDate.Value.Year + "-" + BirthDate.Value.Month + "-" + BirthDate.Value.Day : string.Empty;
    public string SalaryString => Salary.ToString().Replace(',', '.');
    

    public readonly Dictionary<string, string> GenderDictionary = new();

    public EmployeeDetailsPage()
    {
        ViewModel = App.GetService<EmployeeDetailsViewModel>();
        FetchData();
        InitializeComponent();
        GenderDictionary.Add("M", _resourceLoader.GetString("Gender_Male"));
        GenderDictionary.Add("F", _resourceLoader.GetString("Gender_Female"));
        GenderDictionary.Add("N", _resourceLoader.GetString("Gender_NB"));
        GenderDictionary.Add("O", _resourceLoader.GetString("Gender_Other"));
    }

    private async void FetchData()
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        var employee = await Employee.GetEmployeeByNifAsync(int.Parse(localSettings.Values["EmployeeNif"].ToString() ?? "0"));

        Nif = employee.Nif;
        FirstName = employee.FirstName;
        LastName = employee.LastName;
        Email = employee.Email;
        Phone = employee.Phone;
        Address = employee.Address;
        Gender = employee.Gender;
        BirthDate = employee.BirthDate;
        Salary = employee.Salary;
        DepartmentId = employee.DepartmentId;
        
        GenderComboBox.SelectedItem = GenderDictionary[key: (employee.Gender == "" ? "O" : employee.Gender)];
        CalendarDatePicker.Date = employee.BirthDate;

        ConstructionsGridView.ItemsSource = await Construction.GetConstructionsByEmployeeNifAsync(Nif);
        if (ConstructionsGridView.Items.Count > 0)
        {
            ConstructionsSubtitle.Visibility = Visibility.Visible;
        }
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "EmployeeDetails");

    private async void EditInfoButton_OnClick(object sender, RoutedEventArgs e)
    {
        // Create a fade-out animation for the edit icon
        var fadeOutEditAnimation = new DoubleAnimation
        {
            From = 1,
            To = 0,
            Duration = TimeSpan.FromSeconds(0.2),
            EnableDependentAnimation = true
        };
        Storyboard.SetTarget(fadeOutEditAnimation, EditInfoIcon);
        Storyboard.SetTargetProperty(fadeOutEditAnimation, "Opacity");

        // Create a fade-in animation for the progress ring
        var fadeInPrAnimation = new DoubleAnimation
        {
            From = 0,
            To = 1,
            Duration = TimeSpan.FromSeconds(0.2),
            EnableDependentAnimation = true
        };
        Storyboard.SetTarget(fadeInPrAnimation, EditInfoProgressRing);
        Storyboard.SetTargetProperty(fadeInPrAnimation, "Opacity");

        // Run animations
        var sb = new Storyboard();
        sb.Children.Add(fadeOutEditAnimation);
        sb.Children.Add(fadeInPrAnimation);
        sb.Begin();

        // Action
        Salary = decimal.Parse(SalaryTextBox.Text);
        var affectedRows = await Employee.EditEmployee(Nif, FirstName, LastName, Email ?? string.Empty, Phone ?? 0, Address, Gender, BirthDateString, SalaryString);
        
        if (affectedRows == 0)
        {
            var dialog = new ContentDialog
            {
                XamlRoot = ContentArea.XamlRoot,
                Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
                Title = _resourceLoader.GetString("AddErrorCD_Title"),
                PrimaryButtonText = _resourceLoader.GetString("ContentDialog_PrimaryButtonText"),
                DefaultButton = ContentDialogButton.Primary,
                Content = _resourceLoader.GetString("AddErrorCD_Content")
            };
            _ = await dialog.ShowAsync();
        }


        // Create a fade-out animation for the ProgressRing
        var fadeOutPrAnimation = new DoubleAnimation
        {
            From = 1,
            To = 0,
            Duration = TimeSpan.FromSeconds(0.2),
            EnableDependentAnimation = true
        };
        Storyboard.SetTarget(fadeOutPrAnimation, EditInfoProgressRing);
        Storyboard.SetTargetProperty(fadeOutPrAnimation, "Opacity");

        // Create a fade-in animation for the checkmark icon
        var fadeInCheckmarkAnimation = new DoubleAnimation
        {
            From = 0,
            To = 1,
            Duration = TimeSpan.FromSeconds(0.2),
            EnableDependentAnimation = true
        };
        Storyboard.SetTarget(fadeInCheckmarkAnimation, EditInfoIconCheckmark);
        Storyboard.SetTargetProperty(fadeInCheckmarkAnimation, "Opacity");

        // Run animations
        sb = new Storyboard();
        sb.Children.Add(fadeOutPrAnimation);
        sb.Children.Add(fadeInCheckmarkAnimation);
        sb.Begin();


        await Task.Delay(TimeSpan.FromSeconds(2));

        // Create a fade-out animation for the checkmark icon
        var fadeOutCheckmarkAnimation = new DoubleAnimation
        {
            From = 1,
            To = 0,
            Duration = TimeSpan.FromSeconds(0.2),
            EnableDependentAnimation = true
        };
        Storyboard.SetTarget(fadeOutCheckmarkAnimation, EditInfoIconCheckmark);
        Storyboard.SetTargetProperty(fadeOutCheckmarkAnimation, "Opacity");

        // Create a fade-in animation for the edit icon
        var fadeInEditAnimation = new DoubleAnimation
        {
            From = 0,
            To = 1,
            Duration = TimeSpan.FromSeconds(0.2),
            EnableDependentAnimation = true
        };
        Storyboard.SetTarget(fadeInEditAnimation, EditInfoIcon);
        Storyboard.SetTargetProperty(fadeInEditAnimation, "Opacity");

        sb = new Storyboard();
        sb.Children.Add(fadeOutCheckmarkAnimation);
        sb.Children.Add(fadeInEditAnimation);
        sb.Begin();
    }

    private async void DeleteButton_OnClick(object sender, RoutedEventArgs e)
    {
        var dialog = new ContentDialog
        {
            XamlRoot = ContentArea.XamlRoot,
            Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
            Title = _resourceLoader.GetString("DeleteCD_Title"),
            PrimaryButtonText = _resourceLoader.GetString("DeleteCD_PrimaryButton"),
            DefaultButton = ContentDialogButton.Primary,
            CloseButtonText = _resourceLoader.GetString("DeleteCD_CancelButton"),
            Content = _resourceLoader.GetString("DeleteCD_Content")
        };

        var choice = await dialog.ShowAsync();

        if (choice != ContentDialogResult.Primary) { return; }

        await Employee.DeleteEmployee(Nif);

        Frame.GoBack();
    }

    private void ConstructionsGridView_OnItemClick(object sender, ItemClickEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        localSettings.Values["ConstructionId"] = ((Construction)e.ClickedItem).Id.ToString();

        Frame.Navigate(typeof(ConstructionDetailsPage));
    }

    private void GenderComboBox_OnSelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        Gender = ((string)((ComboBox)sender).SelectedItem)[0].ToString();
    }

    private void GenderAndBirthGrid_OnSizeChanged(object sender, SizeChangedEventArgs e)
    {
        GenderComboBox.Width = BirthColumn.ActualWidth;
        CalendarDatePicker.Width = BirthColumn.ActualWidth;
    }

    private void CalendarDatePicker_OnDateChanged(CalendarDatePicker sender, CalendarDatePickerDateChangedEventArgs args)
    {
        BirthDate = sender.Date != null ? sender.Date.Value.DateTime : null;
    }

    private async void AddHoursExistingButton_OnClick(object sender, RoutedEventArgs e)
    {
        var sp = (sender as Button).Parent as StackPanel;

        var id = (sp.Children[0] as TextBlock).Text;

        var date = (sp.Children[1] as CalendarDatePicker).Date.Value;
        var dateString = date.Year + "-" + date.Month + "-" + date.Day;

        var numberBox = sp.Children[2] as NumberBox;
        
        Debug.WriteLine(await Employee.AddEmployeeToConstruction(int.Parse(id), Nif, dateString, (int)numberBox.Value));
    }

    private async void AddHoursNewButton_OnClick(object sender, RoutedEventArgs e)
    {
        var date = AddHoursNewCalendarPicker.Date;
        var dateString = date?.Year + "-" + date?.Month + "-" + date?.Day;

        Debug.WriteLine(await Employee.AddEmployeeToConstruction(int.Parse(AddHoursNewTextBox.Text), Nif, dateString, (int)AddHoursNumberBox.Value));
    }
}