package enums;

public enum UserRole {
    user(1),
    admin(2),
    creator(3);
    private int value;
    UserRole(int value){
        this.value = value;
    }
    public int getInt(){
        return value;
    }
    public static UserRole setInt(int integer){
        return switch (integer) {
            case 1 -> user;
            case 2 -> admin;
            case 3 -> creator;
            default -> null;
        };
    }
}
