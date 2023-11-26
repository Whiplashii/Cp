package enums.sqlqueries;

public enum UserQueries {
    findUserByLogin("Select * from user where username = ?"),
    insertUser("INSERT INTO `kp`.`user` (`username`, `useremail`, `userpassword`, `usersalt`, `wallet`, `userroleid`, `currensyid`, `isbanned`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, '0')"),
    getCurrencyRate("select currencyname, currensyrate from kp.user\n" +
            "inner join kp.currency on currencyid = user.currensyid" +
            "where currencyid = ?");


    private String query;

    UserQueries(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
