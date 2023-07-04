using APFT.Entities;
using System.Diagnostics;
using APFT.ViewModels;

using Microsoft.UI.Xaml.Controls;
using Microsoft.UI.Xaml.Navigation;
using Windows.Storage;
using Microsoft.UI;
using Microsoft.UI.Xaml;
using Microsoft.Windows.ApplicationModel.Resources;
using WinUICommunity;

namespace APFT.Views;

public sealed partial class FornecedoresPage : Page
{
    public FornecedoresViewModel ViewModel
    {
        get;
    }

    public FornecedoresPage()
    {
        ViewModel = App.GetService<FornecedoresViewModel>();
        InitializeComponent();
    }

    private readonly ResourceLoader _resourceLoader = new("resources.pri", "Suppliers");

    protected async override void OnNavigatedTo(NavigationEventArgs e)
    {
        try
        {
            SuppliersCVS.Source = await Supplier.GetSuppliersGroupedAsync();
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
        localSettings.Values["SupplierNif"] = ((Supplier)e.ClickedItem).Nif.ToString();

        Frame.Navigate(typeof(SupplierDetailsPage));
    }
    

    private async void AddButton_OnClick(object sender, RoutedEventArgs e)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        // TODO: Adapt to supplier
        await Supplier.AddSupplier(int.Parse(NifTextBox.Text), NameTextBox.Text, EmailTextBox.Text,
            int.Parse(PhoneTextBox.Text), AddressTextBox.Text);

        localSettings.Values["SupplierNif"] = NifTextBox.Text;
        Frame.Navigate(typeof(SupplierDetailsPage));
    }

    private async void AutoSuggestBox_OnTextChanged(AutoSuggestBox sender, AutoSuggestBoxTextChangedEventArgs args)
    {
        if(args.Reason == AutoSuggestionBoxTextChangeReason.UserInput)
        {
            sender.ItemsSource = sender.Text.Length >= 3 
                ? await Supplier.GetSuppliersByNameAsync(sender.Text.ToLower()) 
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
        localSettings.Values["SupplierNif"] = nif;

        Frame.Navigate(typeof(SupplierDetailsPage));
    }
}
