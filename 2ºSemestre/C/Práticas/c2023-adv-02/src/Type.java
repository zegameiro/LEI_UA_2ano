public abstract class Type
{
   protected final String name;

   protected Type(String name) {
      assert name != null;
      this.name = name;
   }

   public String name() {
      return name;
   }

   public boolean subtype(Type other) {
      return name.equals(other.name());
   }
}
