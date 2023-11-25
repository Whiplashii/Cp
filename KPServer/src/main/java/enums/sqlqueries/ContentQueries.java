package enums.sqlqueries;

public enum ContentQueries {
    insert("INSERT INTO content (`contentname`, `contentdescription`, `contentprice`, `contenttypeid`, `userid`, `currensyid`,`dateadd`) VALUES (?,?,?,?,?,?,?)"),
    remove("Delete from content where contentid = ?"),
    updateName("update content set contentname = ? where contentid = ?"),
    updateDescription("update content set contentdescription = ? where contentid = ?"),
    updatePrice("update content set contentPrice = ? where contentid = ?"),
    getContent("select * from content");
    private String query;
    ContentQueries(String query){
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}