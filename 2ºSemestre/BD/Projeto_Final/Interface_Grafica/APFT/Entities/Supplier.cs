using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using Windows.Storage;

namespace APFT.Entities;

public class Supplier
{
    public int Nif
    {
        get; set;
    }

    public string Name
    {
        get; set;
    }

    public int Phone
    {
        get; set;
    }

    public string Email
    {
        get; set;
    }

    public string? Address
    {
        get; set;
    }
    
    public Supplier(int nif, string name, int phone, string email, string? address)
    {
        Nif = nif;
        Name = name;
        Phone = phone;
        Email = email;
        Address = address;
    }

    public static async Task<ObservableCollection<GroupInfoList>> GetSuppliersGroupedAsync()
    {
        // Grab Contact objects from pre-existing list (list is returned from function GetContactsAsync())
        var query = from item in await GetSuppliersAsync()

            // Group the items returned from the query, sort and select the ones you want to keep
            group item by item.Name[..1].ToUpper() into g
            orderby g.Key

            // GroupInfoList is a simple custom class that has an IEnumerable type attribute, and
            // a key attribute. The IGrouping-typed variable g now holds the Contact objects,
            // and these objects will be used to create a new GroupInfoList object.
            select new GroupInfoList(g) { Key = g.Key };

        return new ObservableCollection<GroupInfoList>(query);
    }

    public static async Task<ObservableCollection<Supplier>> GetSuppliersAsync()
    {
        var suppliers = new ObservableCollection<Supplier>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.FORNECEDOR", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            suppliers.Add(new Supplier(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetInt32(2),
                reader.GetString(3),
                reader.GetString(4))
            );
        }

        return suppliers;
    }

    public static async Task<List<string>> GetSuppliersByNameAsync(string name)
    {
        var suitableItems = new List<string>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getFornecedorByName('" + name + "')", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            suitableItems.Add(reader.GetString(1) + " (" + reader.GetInt32(0) + ")");
        }

        return suitableItems;
    }

    public static async Task<Supplier> GetSupplierByNifAsync(int nif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.FORNECEDOR WHERE nif=" + nif, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Supplier(
            reader.GetInt32(0),
            reader.GetString(1),
            reader.GetInt32(2),
            reader.GetString(3),
            reader.GetString(4));
    }

    public static async Task<int> AddSupplier(int nif, string name, string email, int phone, string? address)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC create_fornecedor " + nif + ", '" + name + "', " + phone + ", '" + email + "', " + 
                                 (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'"), cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditSupplier(int nif, string name, int phone, string email, string? address)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC update_fornecedor " + nif + ", '" + name + "', " + phone + ", '" + email + "', " + 
                                 (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'"), cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteSupplier(int nif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_fornecedor " + nif, cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}