package framework.stubb;

import framework.annotations.DataObject;

@DataObject
public class DataObjectStubb {
    public String field1;
    public int field2;
    public float field3;
    public long field4;

    public DataObjectStubb(String field1, int field2, float field3, long field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    @Override
    public String toString() {
        return "DataObjectStubb{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3=" + field3 +
                ", field4=" + field4 +
                '}';
    }
}
