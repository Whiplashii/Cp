package enums;

public enum UserRole {
    user(1),
    admin(2),
    creator(3);
    private int value;
    UserRole(int value){
        this.value = value;
    }
}
