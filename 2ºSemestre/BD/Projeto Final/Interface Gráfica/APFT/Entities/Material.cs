using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using System.Diagnostics;
using Windows.Storage;

namespace APFT.Entities;

public class Material
{
    public int Id
    {
        get; set;
    }

    public string Category
    {
        get; set;
    }

    public string Name
    {
        get; set;
    }

    public int WarehouseUnits
    {
        get; set;
    }
    
    public Material(int id, string category, string name, int warehouseUnits)
    {
        Id = id;
        Category = category;
        Name = name;
        WarehouseUnits = warehouseUnits;
    }

    public static async Task<ObservableCollection<Material>> GetMaterialsAsync()
    {
        var materials = new ObservableCollection<Material>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            materials.Add(new Material(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetString(2),
                reader.GetInt32(3))
            );
        }

        return materials;
    }

    public static async Task<Material> GetMaterialByIdAsync(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO WHERE id=" + id, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Material(
            reader.GetInt32(0),
            reader.GetString(1),
            reader.GetString(2),
            reader.GetInt32(3));
    }

    public static async Task<ObservableCollection<Material>> GetMaterialsByConstructionIdAsync(int constructionId)
    {
        var list = new ObservableCollection<Material>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getMateriaisByObra(" + constructionId + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            list.Add(new Material(
                reader.GetInt32(1),
                reader.GetString(3),
                reader.GetString(2),
                0)
            );
        }

        return list;
    }

    public static async Task<ObservableCollection<Material>> GetMaterialsByOrderIdAsync(int orderId)
    {
        var list = new ObservableCollection<Material>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getMateriaisByEncomenda(" + orderId + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            list.Add(new Material(
                reader.GetInt32(1),
                reader.GetString(3),
                reader.GetString(2),
                0)
            );
        }

        return list;
    }

    public static async Task<int> AddMaterial(int id, string category, string name, int wharehouseUnits)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC create_material " + id + ", '" + category + "', '" + name + "', " + wharehouseUnits, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditMaterial(int id, string category, string name, int wharehouseUnits)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC update_material " + id + ", '" + category + "', '" + name + "', " + wharehouseUnits, cn);
        
        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteMaterial(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_material " + id, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> AddMaterialToOrder(int orderId, int materialId, double price)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC add_material_enc " + orderId + ", " + materialId + ", " + price.ToString().Replace(',', '.'), cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}