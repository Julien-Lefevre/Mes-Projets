package CLASS.Character;

import CLASS.Room.*;

public abstract class Student extends Human {

    public Student(String name, String description, int HPmax, Room room){
        super(name, description, HPmax, room);
    }
}