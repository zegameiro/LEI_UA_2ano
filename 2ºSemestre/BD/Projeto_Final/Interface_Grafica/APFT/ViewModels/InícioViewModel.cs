using System.Collections.ObjectModel;
using APFT.Entities;
using CommunityToolkit.Mvvm.ComponentModel;
using LiveChartsCore.SkiaSharpView;
using LiveChartsCore;
using LiveChartsCore.SkiaSharpView.Painting;
using LiveChartsCore.SkiaSharpView.VisualElements;
using System.ComponentModel;
using System.Diagnostics;
using APFT.Views;
using LiveChartsCore.Drawing;
using Microsoft.UI;
using Microsoft.UI.Xaml;
using SkiaSharp;

namespace APFT.ViewModels;

public partial class InícioViewModel : ObservableObject, INotifyPropertyChanged
{
    public event PropertyChangedEventHandler? PropertyChanged;

    private void OnPropertyChanged(string propertyName)
    {
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
    }

    public InícioViewModel()
    {
        RowsXAxes = new Axis[] { new() { Labels = Array.Empty<string>() } }; // Disable X-axis labels
        RowsYAxes = new Axis[] { new() { MinLimit = 0 } }; // Minimum of 0 in Y-axis
        EmployeeSeries = Array.Empty<ISeries>();
        RowsSeries = Array.Empty<ISeries>();

        if (ShellPage.Current.GetInfoBadgeColor() == Microsoft.UI.Colors.Green)
        {
            FetchData();
        }
    }

    private async void FetchData()
    {
        var i = 0;

        var employeeData = await Employee.GetHoursOfAllEmployees();
        var hoursData = employeeData.Select(employee => employee.Hours.HasValue ? (double)employee.Hours.Value : 0).ToArray();
        EmployeeSeries = hoursData.AsLiveChartsPieSeries((value, series) =>
        {
            series.Name = employeeData[i++].Name;
            series.InnerRadius = 80;
        });
        
        var tableData = await Info.GetTableInfo();
        var tableDataRowNumbers = tableData.Select(table => table.Rows ?? 0).ToArray();
        var tableDataLabels = tableData.Select(table => table.TableName ?? string.Empty).ToArray();

        RowsSeries = new ISeries[]
        {
            new ColumnSeries<long>
            {
                Values = tableDataRowNumbers,
                TooltipLabelFormatter = point => $"{tableDataLabels[point.Context.Index]}: {point.Model}",
                Fill = new SolidColorPaint(SKColor.Parse(Application.Current.Resources["SystemAccentColor"].ToString()))
            }
        };
    }

    private IEnumerable<ISeries> _employeeSeries;
    public IEnumerable<ISeries> EmployeeSeries
    {
        get => _employeeSeries;
        set
        {
            if (_employeeSeries != value)
            {
                _employeeSeries = value;
                OnPropertyChanged(nameof(EmployeeSeries));
            }
        }
    }

    private ISeries[] _rowsSeries;
    public ISeries[] RowsSeries
    {
        get => _rowsSeries;
        set
        {
            if (_rowsSeries != value)
            {
                _rowsSeries = value;
                OnPropertyChanged(nameof(RowsSeries));
            }
        }
    }

    private Axis[] _rowsXAxes;
    public Axis[] RowsXAxes
    {
        get => _rowsXAxes;
        set
        {
            if (_rowsXAxes != value)
            {
                _rowsXAxes = value;
                OnPropertyChanged(nameof(RowsXAxes));
            }
        }
    }

    private Axis[] _rowsYAxes;
    public Axis[] RowsYAxes
    {
        get => _rowsYAxes;
        set
        {
            if (_rowsYAxes != value)
            {
                _rowsYAxes = value;
                OnPropertyChanged(nameof(RowsYAxes));
            }
        }
    }
}
