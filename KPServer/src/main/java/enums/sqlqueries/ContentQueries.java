package enums.sqlqueries;

public enum ContentQueries {
    insert("INSERT INTO content (`contentname`, `contentdescription`, `contentprice`, `contenttypeid`, `userid`, `currensyid`,`dateadd`) VALUES (?,?,?,?,?,?,?)"),
    remove("Delete from content where contentid = ?"),
    updateContent("update content set contentname = ?, contentdescription = ?, contentPrice = ? where contentid = ?"),
    updateDescription("update content set contentdescription = ? where contentid = ?"),
    updatePrice("update content set contentPrice = ? where contentid = ?"),
    getContent("select * from content"),
    getCreatorContent("select * from content where userid = ?"),
    getUserLibrary("Select contentid,contentname,contentdescription,contentprice,contenttypeid,content.userid,dateadd,currensyid from usercontent "+
            "inner join kp.content on usercontentid = contentid "+
            "where kp.usercontent.userid = ?"),
    getContentPrice("select contentprice, currencyrate from content " +
            "inner join currency on currencyid = currensyid " +
            "where contentid = ?");
    private String query;
    ContentQueries(String query){
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
