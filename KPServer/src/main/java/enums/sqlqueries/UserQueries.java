package enums.sqlqueries;

public enum UserQueries {
    findUserByLogin("Select * from user where username = ?"),
    insertUser("INSERT INTO `kp`.`user` (`username`, `useremail`, `userpassword`, `usersalt`, `wallet`, `userroleid`, `currensyid`, `isbanned`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, '0')"),
    getCurrencyRate("select currencyname, currensyrate from kp.user\n" +
            "inner join kp.currency on currencyid = user.currensyid" +
            "where currencyid = ?"),
    becomeCreator("UPDATE user SET userroleid = ? where userid = ?"),
    buyContent("INSERT INTO usercontent (`userid`, `usercontentid`) VALUES (?, ?)"),
    changeMoney("UPDATE user SET wallet = ? WHERE (`userid` = ?)"),
    updatedUser("Update user set userroleid = ?, isbanned = ? where userid = ?"),
    getUsers("select * from user where userid != ?");
    private String query;

    UserQueries(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
