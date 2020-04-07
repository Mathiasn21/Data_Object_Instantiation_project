# Getting started:
In order to allow an object to be used as a Data Object, one needs to annotate that class with @DataObject. Like this:

    @DataObject
    public class DTO {
        public String field1;
        public int field2;
        public float field4;
        public double field3;
    
        public DTO(String field1, int field2, double field3, float field4) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
        }
    }

It is possible to be more concise by telling which constructor and which fields to utilize whenever mapping data to that class. Which is done like this:
    
    @DataObject
    public class ComplexDTO implements Comparable<String>{
        @DataField
        public String word;
    
        @DataConstructor
        public ComplexDTO(String word) { this.word = word; }
    
        @Override
        public int compareTo(@NotNull String o) {
            return this.word.compareTo(o);
        }
    }

**Note:** The implementation of Comparable is just there to tell how to compare this object. 
As all instantiated objects are stored in a tree structure internally. This is not a necessity but
does supplement the framework with additional information on how to compare each instance of that class.
