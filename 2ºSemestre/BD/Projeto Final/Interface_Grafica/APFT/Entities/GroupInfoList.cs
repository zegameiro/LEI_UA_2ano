namespace APFT.Entities;

public class GroupInfoList : List<object>
{
    public GroupInfoList(IEnumerable<object> items) : base(items)
    {
        Key = 0;
    }
    public object Key
    {
        get; set;
    }

    public override string ToString()
    {
        return "Group " + Key;
    }
}