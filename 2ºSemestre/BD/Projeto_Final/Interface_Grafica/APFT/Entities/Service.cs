using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using System.Diagnostics;
using Windows.Storage;

namespace APFT.Entities;

public class Service
{
    public int Id
    {
        get; set;
    }

    public string Category
    {
        get; set;
    }

    public int DepartmentId
    {
        get; set;
    }
    
    public Service(int id, string category, int departmentId)
    {
        Id = id;
        Category = category;
        DepartmentId = departmentId;
    }

    public static async Task<ObservableCollection<Service>> GetServicesAsync()
    {
        var services = new ObservableCollection<Service>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.SERVICO", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            services.Add(new Service(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetInt32(2))
            );
        }

        return services;
    }

    public static async Task<Service> GetServiceByIdAsync(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.SERVICO WHERE id_S=" + id, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Service(
            reader.GetInt32(0),
            reader.GetString(1),
            reader.GetInt32(2));
    }

    public static async Task<ObservableCollection<Service>> GetServicesByConstructionIdAsync(int constructionId)
    {
        var services = new ObservableCollection<Service>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getObraServicoByObra(" + constructionId + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            services.Add(new Service(
                reader.GetInt32(1),
                reader.GetString(2),
                reader.GetInt32(3))
            );
        }

        return services;
    }

    public static async Task<int> AddService(int id, string name)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC create_service " + id + ", '" + name + "'", cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditService(int id, string name)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC update_service " + id + ", '" + name + "'", cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteService(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_service " + id, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> AddServiceToConstruction(int constructionId, int serviceId)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        Debug.WriteLine("EXEC add_obra_servico " + constructionId + ", " + serviceId);
        var cmd = new SqlCommand("EXEC add_obra_servico " + constructionId + ", " + serviceId, cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}