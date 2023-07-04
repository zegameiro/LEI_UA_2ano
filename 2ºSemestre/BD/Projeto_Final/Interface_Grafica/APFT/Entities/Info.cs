using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using System.Diagnostics;
using Windows.Storage;

namespace APFT.Entities;

public class Info
{
    public string? TableName
    {
        get; set;
    }

    public double? Fragmentation
    {
        get; set;
    }

    public long? Rows
    {
        get; set;
    }

    public Info(string? tableName, double? fragmentation, long? rows)
    {
        TableName = tableName;
        Fragmentation = fragmentation;
        Rows = rows;
    }

    public static async Task<ObservableCollection<Info>> GetTableInfo()
    {
        var list = new ObservableCollection<Info>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getTablesInfo()", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            list.Add(new Info(
                reader.GetString(0),
                reader.GetDouble(1),
                reader.GetInt64(2))
            );
        }

        return list;
    }
}