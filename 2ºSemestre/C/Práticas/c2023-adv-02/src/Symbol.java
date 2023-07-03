public class Symbol
{
   protected final Type type;
   public Symbol(Type type) {
      
      assert type != null;

      this.type = type;
   }



   public Type type(){
      return type;
   }

   // para debug
   @Override
   public String toString() {
       return "Symbol type: " + type.toString();
   }
   
}

