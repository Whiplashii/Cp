package enums.sqlqueries;

public enum UserQueries {
    findUserByLogin("Select * from user where username = ?"),
    insertUser("INSERT INTO `kp`.`user` (`username`, `useremail`, `userpassword`, `usersalt`, `wallet`, `userroleid`, `currensyid`, `isbanned`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, '0')");

    private String query;
    UserQueries(String query){
        this.query = query;
    }
    @Override
    public String toString(){
        return query;
    }
}
