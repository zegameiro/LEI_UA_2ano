using System.Diagnostics;
using APFT.ViewModels;
using Microsoft.UI;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Navigation;
using Windows.Storage;
using Microsoft.Windows.ApplicationModel.Resources;
using WinUICommunity;
using APFT.Entities;

namespace APFT.Views;

public sealed partial class ClientesPage
{
    public ClientesViewModel ViewModel
    {
        get;
    }

    public ClientesPage()
    {
        ViewModel = App.GetService<ClientesViewModel>();
        InitializeComponent();
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "Customers");

    protected async override void OnNavigatedTo(NavigationEventArgs e)
    {
        try
        {
            CustomersCVS.Source = await Customer.GetCustomersGroupedAsync();
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
        localSettings.Values["CustomerNif"] = ((Customer)e.ClickedItem).Nif.ToString();

        Frame.Navigate(typeof(CustomerDetailsPage));
    }
    

    private async void AddButton_OnClick(object sender, RoutedEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await Customer.AddCustomer(int.Parse(NifTextBox.Text), FNameTextBox.Text, LNameTextBox.Text, EmailTextBox.Text,
            int.Parse(PhoneTextBox.Text), AddressTextBox.Text);

        localSettings.Values["CustomerNif"] = NifTextBox.Text;
        Frame.Navigate(typeof(CustomerDetailsPage));
    }

    private async void AutoSuggestBox_OnTextChanged(AutoSuggestBox sender, AutoSuggestBoxTextChangedEventArgs args)
    {
        if(args.Reason == AutoSuggestionBoxTextChangeReason.UserInput)
        {
            sender.ItemsSource = sender.Text.Length >= 3 
                ? await Customer.GetCustomersByNameAsync(sender.Text.ToLower()) 
                : null;
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
        localSettings.Values["CustomerNif"] = nif;

        Frame.Navigate(typeof(CustomerDetailsPage));
    }
}