using Aula_7_1.ViewModels;

using Microsoft.UI.Xaml.Controls;

namespace Aula_7_1.Views;

public sealed partial class TestPage : Page
{
    public TestViewModel ViewModel
    {
        get;
    }

    public TestPage()
    {
        ViewModel = App.GetService<TestViewModel>();
        InitializeComponent();
        LoadContacts();
        
    }

    public async void LoadContacts()
    {
        ContactList.ItemsSource = await ContentClasses.Contact.GetContactsAsync();
    }
}
