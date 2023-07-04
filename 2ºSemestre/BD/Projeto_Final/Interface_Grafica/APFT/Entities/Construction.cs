using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Net;
using Windows.Networking;
using Windows.Storage;

namespace APFT.Entities;

public class Construction
{
    public int Id
    {
        get; set;
    }

    public string Location
    {
        get; set;
    }

    public DateTime StartDate
    {
        get; set;
    }

    public DateTime? EndDate
    {
        get; set;
    }

    public int CustomerNif
    {
        get; set;
    }

    public string StartDateString => StartDate != null ? StartDate.Year + "-" + StartDate.Month + "-" + StartDate.Day : string.Empty;
    public string EndDateString => EndDate != null ? EndDate.Value.Year + "-" + EndDate.Value.Month + "-" + EndDate.Value.Day : string.Empty;
    public string DateString => StartDateString + " - " + EndDateString;

    public Construction(int id, string location, DateTime startDate, DateTime? endDate, int customerNif)
    {
        Id = id;
        Location = location;
        StartDate = startDate;
        EndDate = endDate;
        CustomerNif = customerNif;
    }

    public static async Task<ObservableCollection<Construction>> GetConstructionsAsync()
    {
        var constructions = new ObservableCollection<Construction>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.OBRA", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            constructions.Add(new Construction(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetDateTime(2),
                await reader.IsDBNullAsync(3) ? null : reader.GetDateTime(3),
                reader.GetInt32(4)
            ));
        }

        return constructions;
    }

    public static async Task<ObservableCollection<Construction>> GetConstructionsByCustomerNifAsync(int customerNif)
    {
        var constructions = new ObservableCollection<Construction>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getObraByClientNif(" + customerNif + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            constructions.Add(new Construction(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetDateTime(2),
                await reader.IsDBNullAsync(3) ? null : reader.GetDateTime(3),
                reader.GetInt32(4)
            ));
        }

        return constructions;
    }

    public static async Task<ObservableCollection<Construction>> GetConstructionsByEmployeeNifAsync(int employeeNif)
    {
        var constructions = new ObservableCollection<Construction>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getObraEmpregadoByEmpregado(" + employeeNif + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            constructions.Add(new Construction(
                reader.GetInt32(1),
                reader.GetString(2),
                reader.GetDateTime(3),
                await reader.IsDBNullAsync(4) ? null : reader.GetDateTime(3),
                0)
            );
        }

        return constructions;
    }

    public static async Task<ObservableCollection<Construction>> GetConstructionsByDateAsync(string startDateString, string endDateString)
    {
        var constructions = new ObservableCollection<Construction>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();

        Debug.WriteLine("SELECT * FROM getObraByDate(" +
                        (string.IsNullOrEmpty(startDateString) ? "null" : "'" + startDateString + "'") + ", " +
                        (string.IsNullOrEmpty(endDateString) ? "null" : "'" + endDateString + "'") + ")");

        var cmd = new SqlCommand("SELECT * FROM getObraByDate(" +
                                 (string.IsNullOrEmpty(startDateString) ? "null" : "'" + startDateString + "'") + ", " +
                                 (string.IsNullOrEmpty(endDateString) ? "null" : "'" + endDateString + "'") + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            constructions.Add(new Construction(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetDateTime(2),
                await reader.IsDBNullAsync(3) ? null : reader.GetDateTime(3),
                reader.GetInt32(4)
            ));
        }

        return constructions;
    }

    public static async Task<Construction> GetConstructionByIdAsync(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.OBRA WHERE id=" + id, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();

        return new Construction(
            reader.GetInt32(0),
            reader.GetString(1),
            reader.GetDateTime(2),
            await reader.IsDBNullAsync(3) ? new DateTime() : reader.GetDateTime(3),
            reader.GetInt32(4));
    }

    public static async Task<int> AddConstruction(int id, string location, string startDateString, string endDateString, int customerNif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC create_obra " + id + ", " + 
                                 "'" + location + "', " +
                                 "'" + startDateString + "', " +
                                 (string.IsNullOrEmpty(endDateString) ? "null" : "'" + endDateString + "'") + 
                                 customerNif, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditConstruction(int id, string location, string startDateString, string endDateString)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC update_obra " + id + ", " + 
                                 "'" + location + "', " +
                                 "'" + startDateString + "', " +
                                 (string.IsNullOrEmpty(endDateString) ? "null" : "'" + endDateString + "'"), cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteConstruction(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_obra " + id, cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}