using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;
using System.Diagnostics;
using Windows.Storage;

namespace APFT.Entities;

public class Employee
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

    public string? Email
    {
        get; set;
    }

    public int? Phone
    {
        get; set;
    }

    public string? Address
    {
        get; set;
    }

    public string? Gender
    {
        get; set;
    }

    public DateTime? BirthDate
    {
        get; set;
    }

    public decimal? Salary
    {
        get; set;
    }

    public int? DepartmentId
    {
        get; set;
    }

    public int? Hours
    {
        get; set;
    }

    public string Name => FirstName + " " + LastName;
    public string BirthDateString => BirthDate != null ? BirthDate.Value.Year + "-" + BirthDate.Value.Month + "-" + BirthDate.Value.Day : string.Empty;
    public string SalaryString => Salary.ToString().Replace(',', '.');

    public Employee(int nif, string firstName, string lastName, string email, int phone, string address, string gender, DateTime birthDate, decimal salary, int departmentId)
    {
        Nif = nif;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Phone = phone;
        Address = address;
        Gender = gender;
        BirthDate = birthDate;
        Salary = salary;
        DepartmentId = departmentId;
    }

    public Employee(int nif, string firstName, string lastName, int hours)
    {
        Nif = nif;
        FirstName = firstName;
        LastName = lastName;
        Hours = hours;
    }

    public static async Task<ObservableCollection<GroupInfoList>> GetEmployeesGroupedAsync()
    {
        // Grab Contact objects from pre-existing list (list is returned from function GetContactsAsync())
        var query = from item in await GetEmployeesAsync()

                    // Group the items returned from the query, sort and select the ones you want to keep
                    group item by item.FirstName[..1].ToUpper() into g
                    orderby g.Key

                    // GroupInfoList is a simple custom class that has an IEnumerable type attribute, and
                    // a key attribute. The IGrouping-typed variable g now holds the Contact objects,
                    // and these objects will be used to create a new GroupInfoList object.
                    select new GroupInfoList(g) { Key = g.Key };

        return new ObservableCollection<GroupInfoList>(query);
    }

    public static async Task<ObservableCollection<Employee>> GetEmployeesAsync()
    {
        var employees = new ObservableCollection<Employee>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.EMPREGADO ORDER BY nome_proprio", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            employees.Add(new Employee(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetString(2),
                reader.GetString(3),
                reader.GetInt32(4),
                await reader.IsDBNullAsync(5) ? string.Empty : reader.GetString(5),
                await reader.IsDBNullAsync(6) ? string.Empty : reader.GetString(6),
                await reader.IsDBNullAsync(7) ? new DateTime(1970, 1, 1) : reader.GetDateTime(7),
                reader.GetDecimal(8),
                reader.GetInt32(9))
            );
        }

        return employees;
    }

    public static async Task<ObservableCollection<GroupInfoList>> GetEmployeesFilteredGroupedAsync(string? gender, string birthDateString, string salary)
    {
        // Grab Contact objects from pre-existing list (list is returned from function GetContactsAsync())
        var query = from item in await GetEmployeesFilteredAsync(gender, birthDateString, salary)

            // Group the items returned from the query, sort and select the ones you want to keep
            group item by item.FirstName[..1].ToUpper() into g
            orderby g.Key

            // GroupInfoList is a simple custom class that has an IEnumerable type attribute, and
            // a key attribute. The IGrouping-typed variable g now holds the Contact objects,
            // and these objects will be used to create a new GroupInfoList object.
            select new GroupInfoList(g) { Key = g.Key };

        return new ObservableCollection<GroupInfoList>(query);
    }

    public static async Task<ObservableCollection<Employee>> GetEmployeesFilteredAsync(string? gender, string birthDateString, string salary)
    {
        var employees = new ObservableCollection<Employee>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getEmpregadoBySexBirthSalary(" + 
                                 (string.IsNullOrEmpty(gender) ? "null" : "'" + gender + "'") + ", " +
                                 (string.IsNullOrEmpty(birthDateString) ? "null" : "'" + birthDateString + "'") + ", " + 
                                 salary + ") ORDER BY nome_proprio", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            employees.Add(new Employee(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetString(2),
                reader.GetString(3),
                reader.GetInt32(4),
                await reader.IsDBNullAsync(5) ? string.Empty : reader.GetString(5),
                await reader.IsDBNullAsync(6) ? string.Empty : reader.GetString(6),
                await reader.IsDBNullAsync(7) ? new DateTime(1970, 1, 1) : reader.GetDateTime(7),
                reader.GetDecimal(8),
                reader.GetInt32(9))
            );
        }

        return employees;
    }

    public static async Task<ObservableCollection<Employee>> GetEmployeesByConstructionIdAsync(int constructionId)
    {
        var employees = new ObservableCollection<Employee>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getObraEmpregadoByObra(" + constructionId + ")", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            employees.Add(new Employee(
                reader.GetInt32(1),
                reader.GetString(4),
                reader.GetString(5),
                reader.GetString(6),
                0,
                string.Empty,
                string.Empty,
                new DateTime(),
                0,
                0)
            );
        }

        return employees;
    }

    public static async Task<List<string>> GetEmployeesByNameAsync(string name)
    {
        var suitableItems = new List<string>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getEmpregadoByName('" + name + "') ORDER BY nome_proprio", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            suitableItems.Add(reader.GetString(1) + " " + reader.GetString(2) + " (" + reader.GetInt32(0) + ")");
        }

        return suitableItems;
    }

    public static async Task<Employee> GetEmployeeByNifAsync(int nif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());
        
        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM EMPRESA_CONSTRUCAO.EMPREGADO WHERE nif=" + nif, cn);

        var reader = await cmd.ExecuteReaderAsync();
        await reader.ReadAsync();
        
        return new Employee(
            reader.GetInt32(0),
            reader.GetString(1),
            reader.GetString(2),
            reader.GetString(3),
            reader.GetInt32(4),
            await reader.IsDBNullAsync(5) ? string.Empty : reader.GetString(5),
            await reader.IsDBNullAsync(6) ? string.Empty : reader.GetString(6),
            await reader.IsDBNullAsync(7) ? new DateTime(1970, 1, 1) : reader.GetDateTime(7),
            reader.GetDecimal(8),
            reader.GetInt32(9));
    }

    public static async Task<ObservableCollection<Employee>> GetHoursOfAllEmployees()
    {
        var orders = new ObservableCollection<Employee>();
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("SELECT * FROM getTotalHoursAllEmployees()", cn);

        var reader = await cmd.ExecuteReaderAsync();

        while (await reader.ReadAsync())
        {
            orders.Add(new Employee(
                reader.GetInt32(0),
                reader.GetString(1),
                reader.GetString(2),
                reader.GetInt32(3))
            );
        }

        return orders;
    }

    public static async Task<int> AddEmployee(int nif, string firstName, string lastName, string email, int phone, string address, string gender, string birthDateString, string salaryString, int departmentId)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        Debug.WriteLine("EXEC add_employee " + nif + ", " +
                        "'" + firstName + "', " +
                        "'" + lastName + "', " +
                        "'" + email + "', " +
                        phone + ", " +
                        (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'") + ", " +
                        (string.IsNullOrEmpty(gender) ? "null" : "'" + gender + "'") + ", " +
                        (string.IsNullOrEmpty(birthDateString) ? "null" : "'" + birthDateString + "'") + ", " + 
                        "" + salaryString + ", " + 
                        departmentId);
        var cmd = new SqlCommand("EXEC add_employee " + nif + ", " +
                                 "'" + firstName + "', " +
                                 "'" + lastName + "', " +
                                 "'" + email + "', " +
                                 phone + ", " +
                                 (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'") + ", " +
                                 (string.IsNullOrEmpty(gender) ? "null" : "'" + gender + "'") + ", " +
                                 (string.IsNullOrEmpty(birthDateString) ? "null" : "'" + birthDateString + "'") + ", " + 
                                 "" + salaryString + ", " + 
                                 departmentId, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> EditEmployee(int nif, string firstName, string lastName, string email, int phone, string? address, string? gender, string birthDateString, string salaryString)
    {
        var localSettings = ApplicationData.Current.LocalSettings;
        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();

        Debug.WriteLine("EXEC update_employee " + nif + ", " +
                        "'" + firstName + "', " +
                        "'" + lastName + "', " +
                        "'" + email + "', " +
                        phone + ", " +
                        (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'") + ", " +
                        (string.IsNullOrEmpty(gender) ? "null" : "'" + gender + "'") + ", " +
                        (string.IsNullOrEmpty(birthDateString) ? "null" : "'" + birthDateString + "'") + ", " + 
                        salaryString);

        var cmd = new SqlCommand("EXEC update_employee " + nif + ", " +
                                 "'" + firstName + "', " +
                                 "'" + lastName + "', " +
                                 "'" + email + "', " +
                                 phone + ", " +
                                 (string.IsNullOrEmpty(address) ? "null" : "'" + address + "'") + ", " +
                                 (string.IsNullOrEmpty(gender) ? "null" : "'" + gender + "'") + ", " +
                                 (string.IsNullOrEmpty(birthDateString) ? "null" : "'" + birthDateString + "'") + ", " + 
                                 salaryString, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> DeleteEmployee(int nif)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        var cmd = new SqlCommand("EXEC delete_employee " + nif, cn);

        return await cmd.ExecuteNonQueryAsync();
    }

    public static async Task<int> AddEmployeeToConstruction(int id, int nif, string date, int hours)
    {
        var localSettings = ApplicationData.Current.LocalSettings;

        await using var cn = new SqlConnection(localSettings.Values["SQLConnectionString"].ToString());

        await cn.OpenAsync();
        Debug.WriteLine("EXEC add_empregado_obra " + id + ", " + nif + ", '" + date + "', '" + hours + ":00:00'");
        var cmd = new SqlCommand("EXEC add_empregado_obra " + id + ", " + nif + ", '" + date + "', '" + hours + ":00:00'", cn);

        return await cmd.ExecuteNonQueryAsync();
    }
}