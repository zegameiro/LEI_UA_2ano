using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.UI.Xaml.Controls;

namespace Aula_7_1.ContentClasses;

public class Contact
{
    private string? _companyName;
    private static SqlConnection cn;

    private static SqlConnection GetSGBDConnection()
    {
        return new SqlConnection("data source= .\\SQLEXPRESS;integrated security=true;initial catalog=Northwind");
    }

    private static async Task<bool> VerifySGBDConnection()
    {
        cn ??= GetSGBDConnection();

        if (cn.State != ConnectionState.Open)
        {
            await cn.OpenAsync();
        }

        return cn.State == ConnectionState.Open;
    }

    public static async Task<ObservableCollection<Contact>> GetContactsAsync()
    {
        ObservableCollection<Contact> contacts = new ObservableCollection<Contact>();

        bool isConnected = await VerifySGBDConnection().ConfigureAwait(false);
        
        if (!isConnected)
        {
            return contacts;
        }

        SqlCommand cmd = new SqlCommand("SELECT * FROM Customers", cn);
        SqlDataReader reader = cmd.ExecuteReader();

        while (reader.Read())
        {
            Contact C = new Contact
            {
                CustomerID = reader["CustomerID"].ToString(),
                Company = reader["CompanyName"].ToString(),
                Name = reader["ContactName"].ToString(),
                Address1 = reader["Address"].ToString(),
                City = reader["City"].ToString(),
                State = reader["Region"].ToString(),
                ZIP = reader["PostalCode"].ToString(),
                Country = reader["Country"].ToString()
            };
            contacts.Add(C);
        }

        cn.Close();

        return contacts;
    }

    public string? Company
    {
        get => _companyName;
        set
        {
            if (value == null || string.IsNullOrEmpty(value))
            {
                throw new Exception("Company Name field can’t be empty");
            }
            _companyName = value;
        }
    }

    public string? CustomerID { get; set; }
    public string? Name { get; set; }
    public string? Address1 { get; set; }
    public string? Address2 { get; set; }
    public string? City { get; set; }
    public string? State { get; set; }
    public string? ZIP { get; set; }
    public string? Country { get; set; }
    public string? Tel { get; set; }
    public string? FAX { get; set; }

    public override string ToString() { return CustomerID + "   " + _companyName; }
    public Contact() : base()
    {
    }

    public Contact(string CompanyName, string LastName, string FirstName) : base()
    {
        Name = LastName + ", " + FirstName;
        Company = CompanyName;
    }

    public Contact(string CompanyName) : base()
    {
        Company = CompanyName;
    }
}

