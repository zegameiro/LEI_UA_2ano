using System.Diagnostics;
using APFT.Entities;
using APFT.ViewModels;
using Microsoft.UI;
using Microsoft.UI.Xaml;
using Microsoft.UI.Xaml.Controls;

namespace APFT.Views;

public sealed partial class InícioPage : Page
{
    public InícioViewModel ViewModel
    {
        get;
    }

    public InícioPage()
    {
        ViewModel = App.GetService<InícioViewModel>();
        InitializeComponent();
        if (ShellPage.Current.GetInfoBadgeColor() == Microsoft.UI.Colors.Green)
        {
            LoginGrid.Visibility = Visibility.Collapsed;
            GraphsGrid.Visibility = Visibility.Visible;
        }
    }

    private void FrameworkElement_OnSizeChanged(object sender, SizeChangedEventArgs e)
    {
        if (ShellPage.Current.GetNavigationViewDisplayMode() == NavigationViewDisplayMode.Minimal)
        {
            PieChartEmployees.SetValue(Grid.ColumnSpanProperty, 2);
            CartesianChartRows.SetValue(Grid.ColumnSpanProperty, 2);
            CartesianChartRows.SetValue(Grid.RowProperty, 1);
            CartesianChartRows.SetValue(Grid.ColumnProperty, 0);
        }
        else
        {
            PieChartEmployees.SetValue(Grid.ColumnSpanProperty, 1);
            CartesianChartRows.SetValue(Grid.ColumnSpanProperty, 1);
            CartesianChartRows.SetValue(Grid.RowProperty, 0);
            CartesianChartRows.SetValue(Grid.ColumnProperty, 1);
        }
    }
}
