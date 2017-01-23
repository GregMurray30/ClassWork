public class DictionaryTest {

        public static void main(String[] args) {
            Dictionary A = new Dictionary();

          //TEST: isEmpty(), size() and insert()
          //System.out.println(A.isEmpty()); //output: true
          //System.out.println(A.size()); //output: 0
            A.insert("1","a");
          //System.out.println(A.isEmpty()); //output: false
          //System.out.println(A.size()); //output: 1

          //TEST:lookup() and delete()
           A.insert("2","b");
         //System.out.println(A.lookup("1")); //output: a
         // System.out.println(A.lookup("2")); //output: b
         //System.out.println(A.toString());
          // A.delete("1");
          //System.out.println(A.lookup("2")); //output: null

          //TEST: lookup() and findKey()
         //  A.insert("2","b");
           A.insert("3", "c");
         //System.out.println(A.lookup("3")); //output: c
           A.delete("2");
         //System.out.println(A.lookup("2")); //output: null
         //System.out.println(A.lookup("3")); //output: c
           A.insert("2", "b");
         //System.out.println(A.lookup("2")); //output: b

         //TEST: toString() and makeEmpty()
           System.out.println(A.toString()); //output:
           //1 a
           //2 b
           //3 c
           A.makeEmpty();
         //System.out.println(A.isEmpty()); //output: true
         //System.out.println(A.size()); //output: 0
         //System.out.println(A.toString());
        }
}
