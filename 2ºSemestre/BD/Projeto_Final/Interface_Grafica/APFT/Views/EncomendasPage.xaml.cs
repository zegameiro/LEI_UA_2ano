using System.Diagnostics;
using Windows.Storage;
using APFT.Entities;
using APFT.ViewModels;
using Microsoft.UI;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Navigation;
using Microsoft.Windows.ApplicationModel.Resources;
using WinUICommunity;

namespace APFT.Views;

public sealed partial class EncomendasPage : Page
{
    public EncomendasViewModel ViewModel
    {
        get;
    }

    public EncomendasPage()
    {
        ViewModel = App.GetService<EncomendasViewModel>();
        InitializeComponent();
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "Orders");

    protected async override void OnNavigatedTo(NavigationEventArgs e)
    {
        try
        {
            OrdersListView.ItemsSource = await Order.GetOrdersAsync();
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
        localSettings.Values["OrderId"] = ((Order)e.ClickedItem).Id.ToString();

        Frame.Navigate(typeof(OrderDetailsPage));
    }

    private async void AddButton_OnClick(object sender, RoutedEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await Order.AddOrder(
            int.Parse(IdTextBox.Text),
            (AddDatePicker.Date?.Year ?? 1970) + "-" + (AddDatePicker.Date?.Month ?? 1) + "-" + (AddDatePicker.Date?.Day ?? 1), 
            int.Parse(SupplierNifTextBox.Text), 
            int.Parse(ConstructionIdTextBox.Text));

        localSettings.Values["OrderId"] = IdTextBox.Text;
        Frame.Navigate(typeof(OrderDetailsPage));
    }

    // Filter

    private DateTime? StartDate { get; set; }
    public string StartDateString => StartDate != null ? StartDate.Value.Year + "-" + StartDate.Value.Month + "-" + StartDate.Value.Day : string.Empty;
    
    private void ClearFilterButton_OnClick(object sender, RoutedEventArgs e)
    {
        SupplierNifFilterTextBox.Text = string.Empty;
        ConstructionIdFilterTextBox.Text = string.Empty;
        StartCalendarDatePicker.Date = null;
    }

    private async void StartCalendarDatePicker_OnDateChanged(CalendarDatePicker sender, CalendarDatePickerDateChangedEventArgs args)
    {
        StartDate = sender.Date?.DateTime;
        OrdersListView.ItemsSource = await Order.GetOrdersFilteredAsync(
            StartDateString, 
            SupplierNifFilterTextBox.Text, 
            ConstructionIdFilterTextBox.Text
            );
    }

    private async void SupplierNifFilterTextBox_OnTextChanged(object sender, TextChangedEventArgs e)
    {
        OrdersListView.ItemsSource = await Order.GetOrdersFilteredAsync(
            StartDateString, 
            SupplierNifFilterTextBox.Text, 
            ConstructionIdFilterTextBox.Text
        );
    }
}
