using Aula_7_1.ViewModels;

using Microsoft.UI.Xaml.Controls;

namespace Aula_7_1.Views;

public sealed partial class MainPage : Page
{
    public MainViewModel ViewModel
    {
        get;
    }

    public MainPage()
    {
        ViewModel = App.GetService<MainViewModel>();
        InitializeComponent();
    }
}
