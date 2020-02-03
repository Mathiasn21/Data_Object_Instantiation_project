package framework.dataCollection;

public interface ICollector {
    void getAllPrimaryColumns();
    void getAllFilledColumns();
    void getCategoryBy(String name);
}