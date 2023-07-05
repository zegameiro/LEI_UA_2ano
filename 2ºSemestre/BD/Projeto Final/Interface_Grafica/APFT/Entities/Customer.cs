using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using System.Diagnostics;
using Windows.Storage;

namespace APFT.Entities;

public class Customer
{
    public int Nif
    {
        get; set;
    }
    public string FirstName
    {
        get; set;
    }
    public string LastName
    {
        get; set;
    }
    public string Email
    {
        get; set;
    }
    public int Phone
    {
        get; set;
    }
    public string? Address
    {
        get; set;
    }
    public string Name => FirstName + " " + LastName;

    public Customer(int nif, string firstName, string lastName, string email, int phone, string? address)
    {
        Nif = nif;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
        Address = address;
    }

    public static async Task<ObservableCollection<GroupInfoList>> GetCustomersGroupedAsync()
    {
        // Grab Contact objects from pre-existing list (list is returned from function GetContactsAsync())
        var query = from item in await GetCustomersAsync()

                    // Group the items returned from the query, sort and select the ones you want to keep
                    group item by item.FirstName[..1].ToUpper() into g
                    orderby g.Key

                    // GroupInfoList is a simple custom class that has an IEnumerable type attribute, and
                    // a key attribute. The IGrouping-typed variable g now holds the Contact objects,
                    // and these objects will be used to create a new GroupInfoList object.
                    select new GroupInfoList(g) { Key = g.Key };

        return new ObservableCollection<GroupInfoList>(query);
    }

    public static async Task<ObservableCollection<Customer>> GetCustomersAsync()
    {
        var customers = new ObservableCollection<Customer>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.CLIENTE ORDER BY nome_proprio", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            customers.Add(new Customer(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetString(2),
                reader.GetString(3),
                reader.GetInt32(4),
                await reader.IsDBNullAsync(5) ? string.Empty : reader.GetString(5))
            );
        }

        return customers;
    }

    public static async Task<Customer> GetCustomerByNifAsync(int nif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.CLIENTE WHERE nif=" + nif, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Customer(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetString(2),
                reader.GetString(3),
                reader.GetInt32(4),
                await reader.IsDBNullAsync(5) ? string.Empty : reader.GetString(5));
    }

    public static async Task<List<string>> GetCustomersByNameAsync(string name)
    {
        var suitableItems = new List<string>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getClientByName('" + name + "') ORDER BY nome_proprio", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            suitableItems.Add(reader.GetString(1) + " " + reader.GetString(2) + " (" + reader.GetInt32(0) + ")");
        }

        return suitableItems;
    }

    public static async Task<int> AddCustomer(int nif, string firstName, string lastName, string email, int phone, string? address)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC create_client " + nif + ", " +
                                 "'" + firstName + "', " +
                                 "'" + lastName + "', " +
                                 "'" + email + "', " +
                                 phone + ", " +
                                 (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'"), cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditCustomer(int nif, string firstName, string lastName, string email, int phone, string? address)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        Debug.WriteLine("EXEC update_client " + nif + ", " +
                        "'" + firstName + "', " +
                        "'" + lastName + "', " +
                        "'" + email + "', " +
                        phone + ", " +
                        (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'"));
        var cmd = new SqlCommand("EXEC update_client " + nif + ", " +
                                 "'" + firstName + "', " +
                                 "'" + lastName + "', " +
                                 "'" + email + "', " +
                                 phone + ", " +
                                 (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'"), cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteCustomer(int nif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_client " + nif, cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}