using System.ComponentModel;
using System.Diagnostics;
using Windows.Storage;
using APFT.Entities;
using APFT.ViewModels;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Media.Animation;
using Microsoft.Windows.ApplicationModel.Resources;
using APFT.Helpers;

namespace APFT.Views;

public sealed partial class ConstructionDetailsPage : INotifyPropertyChanged
{
    public ConstructionDetailsViewModel ViewModel
    {
        get;
    }

    public ConstructionDetailsPage()
    {
        ViewModel = App.GetService<ConstructionDetailsViewModel>();
        InitializeComponent();
        FetchData();
    }

    public event PropertyChangedEventHandler? PropertyChanged;

    private void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    private int _id;
    public int Id
    {
        get => _id;
        set
        {
            _id = value;
            OnPropertyChanged(nameof(Id));
        }
    }

    private string _location;
    public string Location
    {
        get => _location;
        set
        {
            _location = value;
            OnPropertyChanged(nameof(Location));
        }
    }

    private DateTime _startDate;
    public DateTime StartDate
    {
        get => _startDate;
        set
        {
            _startDate = value;
            OnPropertyChanged(nameof(StartDate));
        }
    }

    private DateTime? _endDate;
    public DateTime? EndDate
    {
        get => _endDate;
        set
        {
            _endDate = value;
            OnPropertyChanged(nameof(EndDate));
        }
    }

    private int _customerNif;
    public int CustomerNif
    {
        get => _customerNif;
        set
        {
            _customerNif = value;
            OnPropertyChanged(nameof(CustomerNif));
        }
    }
    
    public string StartDateString => StartDate.Year + "-" + StartDate.Month + "-" + StartDate.Day;
    public string EndDateString => EndDate != null ? EndDate.Value.Year + "-" + EndDate.Value.Month + "-" + EndDate.Value.Day : string.Empty;

    private async void FetchData()
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        var construction = await Construction.GetConstructionByIdAsync(int.Parse(localSettings.Values["ConstructionId"].ToString() ?? "0"));

        Id = construction.Id;
        Location = construction.Location;
        StartDate = construction.StartDate;
        EndDate = construction.EndDate;
        CustomerNif = construction.CustomerNif;
        StartCalendarDatePicker.Date = construction.StartDate;
        EndCalendarDatePicker.Date = construction.EndDate;

        EmployeesGridView.ItemsSource = await Employee.GetEmployeesByConstructionIdAsync(Id);
        if (EmployeesGridView.Items.Count > 0)
        {
            ConstructionEmployeesGrid.Visibility = Visibility.Visible;
        }

        OrdersGridView.ItemsSource = await Order.GetOrdersByConstructionIdAsync(Id);
        if (OrdersGridView.Items.Count > 0)
        {
            ConstructionOrdersGrid.Visibility = Visibility.Visible;
        }

        ServicesGridView.ItemsSource = await Service.GetServicesByConstructionIdAsync(Id);
        if (ServicesGridView.Items.Count > 0)
        {
            ConstructionServicesGrid.Visibility = Visibility.Visible;
        }

        MaterialsGridView.ItemsSource = await Material.GetMaterialsByConstructionIdAsync(Id);
        if (MaterialsGridView.Items.Count > 0)
        {
            ConstructionMaterialsGrid.Visibility = Visibility.Visible;
        }
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "ConstructionDetails");

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
        await Construction.EditConstruction(Id, Location, StartDateString, EndDateString);

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

        await Construction.DeleteConstruction(Id);

        Frame.GoBack();
    }

    private void EmployeesGridView_OnItemClick(object sender, ItemClickEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        localSettings.Values["EmployeeNif"] = ((Employee)e.ClickedItem).Nif.ToString();

        Frame.Navigate(typeof(EmployeeDetailsPage));
    }

    private void StartCalendarDatePicker_OnDateChanged(CalendarDatePicker sender, CalendarDatePickerDateChangedEventArgs args)
    {
        StartDate = sender.Date.Value.DateTime;
    }

    private void EndCalendarDatePicker_OnDateChanged(CalendarDatePicker sender, CalendarDatePickerDateChangedEventArgs args)
    {
        EndDate = sender.Date?.DateTime;
    }

    private async void AddHoursExistingButton_OnClick(object sender, RoutedEventArgs e)
    {
        var sp = (sender as Button).Parent as StackPanel;

        var nif = (sp.Children[0] as TextBlock).Text;

        var date = (sp.Children[1] as CalendarDatePicker).Date.Value;
        var dateString = date.Year + "-" + date.Month + "-" + date.Day;

        var numberBox = sp.Children[2] as NumberBox;
        
        Debug.WriteLine(await Employee.AddEmployeeToConstruction(Id, int.Parse(nif), dateString, (int)numberBox.Value));
    }

    private async void AddHoursNewButton_OnClick(object sender, RoutedEventArgs e)
    {
        var date = AddHoursNewCalendarPicker.Date;
        var dateString = date?.Year + "-" + date?.Month + "-" + date?.Day;

        Debug.WriteLine(await Employee.AddEmployeeToConstruction(Id, int.Parse(AddHoursNewTextBox.Text), dateString, (int)AddHoursNumberBox.Value));
    }

    private void DatesGrid_OnSizeChanged(object sender, SizeChangedEventArgs e)
    {
        StartCalendarDatePicker.Width = DatesGridColumn.ActualWidth;
        EndCalendarDatePicker.Width = DatesGridColumn.ActualWidth;
    }

    private async void AddServiceNewButton_OnClick(object sender, RoutedEventArgs e)
    {
        Debug.WriteLine(await Service.AddServiceToConstruction(Id, (int)AddServiceNumberBox.Value));
    }

    private async void ServicesGridView_OnItemClick(object sender, ItemClickEventArgs e)
    {
        var dialog = new ContentDialog
        {
            XamlRoot = ContentArea.XamlRoot,
            Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
            Title = _resourceLoader.GetString("NotImplemented_Title"),
            PrimaryButtonText = _resourceLoader.GetString("NotImplemented_Close"),
            DefaultButton = ContentDialogButton.Primary,
            Content = _resourceLoader.GetString("NotImplemented_Content")
        };
        _ = await dialog.ShowAsync();
    }

    private async void OrdersGridView_OnItemClick(object sender, ItemClickEventArgs e)
    {
        var dialog = new ContentDialog
        {
            XamlRoot = ContentArea.XamlRoot,
            Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
            Title = _resourceLoader.GetString("NotImplemented_Title"),
            PrimaryButtonText = _resourceLoader.GetString("NotImplemented_Close"),
            DefaultButton = ContentDialogButton.Primary,
            Content = _resourceLoader.GetString("NotImplemented_Content")
        };
        _ = await dialog.ShowAsync();
    }

    private async void MaterialsGridView_OnItemClick(object sender, ItemClickEventArgs e)
    {
        var dialog = new ContentDialog
        {
            XamlRoot = ContentArea.XamlRoot,
            Style = Application.Current.Resources["DefaultContentDialogStyle"] as Style,
            Title = _resourceLoader.GetString("NotImplemented_Title"),
            PrimaryButtonText = _resourceLoader.GetString("NotImplemented_Close"),
            DefaultButton = ContentDialogButton.Primary,
            Content = _resourceLoader.GetString("NotImplemented_Content")
        };
        _ = await dialog.ShowAsync();
    }
}
