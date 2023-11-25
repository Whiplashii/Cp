package enums.sqlqueries;

public enum UserQueries {
    findUserByLogin("Select username,userpassword from user where username = ? and userpassword = ?");

    private String query;
    UserQueries(String query){
        this.query = query;
    }
    @Override
    public String toString(){
        return query;
    }
}
