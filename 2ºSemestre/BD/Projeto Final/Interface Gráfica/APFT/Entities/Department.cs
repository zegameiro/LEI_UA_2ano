using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using Windows.Storage;

namespace APFT.Entities;

public class Department
{
    public int Id
    {
        get; set;
    }

    public string Name
    {
        get; set;
    }

    public Department(int id, string name)
    {
        Id = id;
        Name = name;
    }

    public static async Task<ObservableCollection<Department>> GetDepartmentsAsync()
    {
        var departments = new ObservableCollection<Department>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.DEPARTAMENTO", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            departments.Add(new Department(
                reader.GetInt32(0),
                reader.GetString(1))
            );
        }

        return departments;
    }

    public static async Task<Department> GetDepartmentByIdAsync(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.DEPARTAMENTO WHERE id=" + id, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Department(
            reader.GetInt32(0),
            reader.GetString(1));
    }

    public static async Task<int> AddDepartment(int id, string name)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC create_department " + id + ", '" + name + "'", cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditDepartment(int id, string name)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC update_department " + id + ", '" + name + "'", cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteDepartment(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_department " + id, cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}