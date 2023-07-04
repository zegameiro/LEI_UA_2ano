using System.ComponentModel;
using System.Diagnostics;
using APFT.Entities;
using APFT.ViewModels;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Media.Animation;
using Microsoft.Windows.ApplicationModel.Resources;
using Windows.Storage;

namespace APFT.Views;

public sealed partial class OrderDetailsPage : INotifyPropertyChanged
{
    public OrderDetailsViewModel ViewModel
    {
        get;
    }

    public OrderDetailsPage()
    {
        ViewModel = App.GetService<OrderDetailsViewModel>();
        FetchData();
        InitializeComponent();
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

    private DateTime _date;
    public DateTime Date
    {
        get => _date;
        set
        {
            _date = value;
            OnPropertyChanged(nameof(Date));
        }
    }

    private int _supplierNif;
    public int SupplierNif
    {
        get => _supplierNif;
        set
        {
            _supplierNif = value;
            OnPropertyChanged(nameof(SupplierNif));
        }
    }

    private string _supplierName;
    public string SupplierName
    {
        get => _supplierName;
        set
        {
            _supplierName = value;
            OnPropertyChanged(nameof(SupplierName));
        }
    }

    private int _constructionId;
    public int ConstructionId
    {
        get => _constructionId;
        set
        {
            _constructionId = value;
            OnPropertyChanged(nameof(ConstructionId));
        }
    }

    public string DateString => Date != null ? Date.Year + "-" + Date.Month + "-" + Date.Day : string.Empty;

    private async void FetchData()
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        var order = await Order.GetOrderByIdAsync(int.Parse(localSettings.Values["OrderId"].ToString() ?? "0"));

        Id = order.Id;
        Date = order.Date;
        SupplierNif = order.SupplierNif;
        SupplierName = order.SupplierName ?? string.Empty;
        ConstructionId = order.ConstructionId;
        DatePicker.Date = order.Date;

        MaterialsGridView.ItemsSource = await Material.GetMaterialsByOrderIdAsync(Id);
        if (MaterialsGridView.Items.Count > 0)
        {
            MaterialsSubtitle.Visibility = Visibility.Visible;
        }
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "OrderDetails");

    private void FrameworkElement_OnSizeChanged(object sender, SizeChangedEventArgs e)
    {
        DatePicker.Width = DatePickerColumn.ActualWidth;
    }

    private async void AddButtonBase_OnClick(object sender, RoutedEventArgs e)
    {
        Debug.WriteLine(await Material.AddMaterialToOrder(Id, int.Parse(AddMaterialIdTextBox.Text), double.Parse(AddMaterialCostTextBox.Text)));
        FetchData();
    }
}
