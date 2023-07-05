using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using Windows.Storage;

namespace APFT.Entities;

public class Order
{
    public int Id
    {
        get; set;
    }

    public DateTime Date
    {
        get; set;
    }

    public int SupplierNif
    {
        get; set;
    }

    public int ConstructionId
    {
        get; set;
    }

    public string? SupplierName
    {
        get; set;
    }
    
    public string DateString => Date.Year + "-" + Date.Month + "-" + Date.Day;
    
    public Order(int id, DateTime date, int supplierNif, int constructionId)
    {
        Id = id;
        Date = date;
        SupplierNif = supplierNif;
        ConstructionId = constructionId;
    }

    public Order(int id, DateTime date, int supplierNif, int constructionId, string supplierName)
    {
        Id = id;
        Date = date;
        SupplierNif = supplierNif;
        ConstructionId = constructionId;
        SupplierName = supplierName;
    }

    public static async Task<ObservableCollection<Order>> GetOrdersAsync()
    {
        var orders = new ObservableCollection<Order>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getTotalEncomendas() ORDER BY nome_fornecedor", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            orders.Add(new Order(
                reader.GetInt32(0),
                reader.GetDateTime(1),
                reader.GetInt32(2),
                reader.GetInt32(3),
                reader.GetString(4))
            );
        }

        return orders;
    }

    public static async Task<Order> GetOrderByIdAsync(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.ENCOMENDA WHERE id=" + id, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Order(
            reader.GetInt32(0),
            reader.GetDateTime(1),
            reader.GetInt32(2),
            reader.GetInt32(3));
    }

    public static async Task<ObservableCollection<Order>> GetOrdersByConstructionIdAsync(int constructionId)
    {
        var orders = new ObservableCollection<Order>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getEncomendaByDateFornIdObraId(null, null, " + constructionId + ") ORDER BY nome_fornecedor", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            orders.Add(new Order(
                reader.GetInt32(0),
                reader.GetDateTime(1),
                reader.GetInt32(2),
                reader.GetInt32(3),
                reader.GetString(4))
            );
        }
        
        return orders;
    }

    public static async Task<ObservableCollection<Order>> GetOrdersBySupplierNifAsync(int supplierNif)
    {
        var orders = new ObservableCollection<Order>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getEncomendaByDateFornIdObraId(null, " + supplierNif + ", null) ORDER BY nome_fornecedor", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            orders.Add(new Order(
                reader.GetInt32(0),
                reader.GetDateTime(1),
                reader.GetInt32(2),
                reader.GetInt32(4),
                reader.GetString(3))
            );
        }
        
        return orders;
    }

    public static async Task<ObservableCollection<Order>> GetOrdersFilteredAsync(string dateString, string supplierNif, string constructionId)
    {
        var list = new ObservableCollection<Order>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getEncomendaByDateFornIdObraId(" +
                                 (string.IsNullOrEmpty(dateString) ? "null" : "'" + dateString + "'") + ", " + 
                                 (string.IsNullOrEmpty(supplierNif) ? "null" : supplierNif) + ", " +
                                 (string.IsNullOrEmpty(constructionId) ? "null" : constructionId) + ") ORDER BY nome_fornecedor", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            list.Add(new Order(
                reader.GetInt32(0),
                reader.GetDateTime(1),
                reader.GetInt32(2),
                reader.GetInt32(4),
                reader.GetString(3))
            );
        }

        return list;
    }


    public static async Task<int> AddOrder(int id, string dateString, int supplierNif, int constructionId)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC add_encomenda " + id + ", '" + dateString + "', " + supplierNif + ", " + constructionId, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteOrder(int id)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_order " + id, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    // Complete with remaining UDFs
}