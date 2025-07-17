package CLASS.Game;
import CLASS.Item.*;
import CLASS.Room.*;
import CLASS.Character.*;
import CLASS.Door.*;
import java.util.*;
public class TheGame {
       
    private static TheGame game = null;
    
    public Room Bedroom;
    public Room LivingRoom;
    public Room FrontYard;
    public Room Hall;
    public Room Reception;
    public Room Cafeteria;
    public Room CounselorOffice;
    public Room Infirmary;
    public Room ToiletM;
    public Room ToiletF;
    public Room Library;
    public Room SecretRoom;
    public Room Schoolyard;
    public Room Gym;
    public Room LockerRoom;
    public Room SportField;
    public Room FirstFloor;
    public Room TheatreClass;
    public Room HistoryClass;
    public Room ChemistryClass;
    public Room ArtClass;
    public Room MathsClass;
    public Room PhilosophyClass;
    public Room StaffRoom;
    public Room PrincipalOffice;
    public Room StorageRoom;

    public Key Key_Gym_LockerRoom;
    public Key BoringBook;
    public Key Key_FirstFloor_StorageRoom;

    public Door Bedroom_LivingRoom;
    public Door LivingRoom_FrontYard;
    public Door FrontYard_Hall;
    public Door Hall_Reception;
    public Door Hall_CounselorOffice;
    public Door Hall_Cafeteria;
    public Door Hall_Infirmary;
    public Door Hall_ToiletM;
    public Door Hall_ToiletF;
    public Door Hall_Library;
    public Door Library_SecretRoom;
    public Door Hall_Schoolyard;
    public Door Schoolyard_Gym;
    public Door Gym_LockerRoom;
    public Door Schoolyard_SportField;
    public Door Hall_FirstFloor;
    public Door FirstFloor_TheatreClass;
    public Door FirstFloor_HistoryClass;
    public Door FirstFloor_ChemistryClass;
    public Door FirstFloor_ArtClass;
    public Door FirstFloor_MathsClass;
    public Door FirstFloor_PhilosophyClass;
    public Door FirstFloor_StaffRoom;
    public Door FirstFloor_PrincipalOffice;
    public Door FirstFloor_StorageRoom;

    private TheGame(){}
    
    public static TheGame getInstance(){
        if(game == null){
            game = new TheGame(); 
        }
        return game;
    }

    public void initGame() {

        createMap();
        initCharacters();
        initItem();
        
    }

    public void createMap(){
        //creating rooms
        Bedroom = new Room("Bedroom", "This is my bedroom, everything is as I've left it.");
        LivingRoom = new Room("LivingRoom", "This is the living room, I come here a lot.");
        FrontYard = new Room("FrontYard", "This is the front of the school, also called the smoke spot.");
        Hall = new Room("Hall", "This is the main hall, there are many lockers but few interesting people.");
        Reception = new Room("Reception", "This is the reception, there isn't much to do here.");
        Cafeteria = new Room("Cafeteria", "This is the cafetaria, the food is usually awful. Some even said they saw rats in here.");
        CounselorOffice = new Room("CounselorOffice", "This is the counselor's office, i don't like being here.");
        Infirmary = new Room("Infirmary", "This is the infirmary, I've been here a few times.");
        ToiletM = new Room("ToiletM", "This is the men's toilets. It smells.");
        ToiletF = new Room("ToiletF", "This is the Women's toilets. It smells.");
        Library = new Room("Library", "This is the library, I've never been here willingly.");
        SecretRoom = new Room("SecretRoom", "This is awsome! I now understand the purpose of the library.");
        Schoolyard = new Room("Schoolyard", "This is the schoolyard, my favorite place in the school.");
        Gym = new Room("Gym", "This is the Gym, pretty boring if you ask me.");
        LockerRoom = new Room("LockerRoom", "This is the locker room, it stinks in here.");
        SportField = new Room("SportField", "This is the sport field, I love sitting here.");
        FirstFloor = new Room("FirstFloor", "This is the first floor, it leads to all the classrooms.");
        TheatreClass = new Room("TheatreClass", "This is the Theatre class, there are many costumes in here.");
        HistoryClass = new Room("HistoryClass", "This is the History class, the best place to sleep.");
        ChemistryClass = new Room("ChemistryClass", "This is the Chemistry class, where the magic happens.");
        ArtClass = new Room("ArtClass", "This is the Art class, you can draw and paint here.");
        MathsClass = new Room("MathsClass", "This is the Maths class, it's nerds stuff.");
        PhilosophyClass = new Room("PhilosophyClass", "This is the Philosophy class, the second best place to sleep.");
        StaffRoom = new Room("StaffRoom", "This is the staff room, i'm usually not allowed here.");
        PrincipalOffice = new Room("PrincipalOffice", "This is the Principal's office, scary stuff.");
        StorageRoom = new Room("StorageRoom", "This is the storage room, where people store stuff.");

        //creating the necessary keys
        Key_Gym_LockerRoom = new Key("Key_Gym_LockerRoom", "This is the key to the LockerRoom.");
        BoringBook = new Key("BoringBook", "This books looks boooooring, it's long overdue in the Library.");
        Key_FirstFloor_StorageRoom = new Key("Key_FirstFloor_StorageRoom", "This is the key to the StorageRoom.");
        

        //creating and linking doors
        Bedroom_LivingRoom = new Door(Bedroom, LivingRoom);
        Bedroom_LivingRoom.setRoomMap();

        LivingRoom_FrontYard = new DoorOneWay(LivingRoom, FrontYard);
        LivingRoom_FrontYard.setRoomMap();

        FrontYard_Hall = new Door(FrontYard, Hall);
        FrontYard_Hall.setRoomMap();

        Hall_Reception = new Door(Hall, Reception);
        Hall_Reception.setRoomMap();

        Hall_CounselorOffice = new Door(Hall, CounselorOffice);
        Hall_CounselorOffice.setRoomMap();

        Hall_Cafeteria = new Door(Hall, Cafeteria);
        Hall_Cafeteria.setRoomMap();

        Hall_Infirmary = new Door(Hall, Infirmary);
        Hall_Infirmary.setRoomMap();

        Hall_ToiletM = new Door(Hall, ToiletM);
        Hall_ToiletM.setRoomMap();

        Hall_ToiletF = new Door(Hall, ToiletF);
        Hall_ToiletF.setRoomMap();

        Hall_Library = new Door(Hall, Library);
        Hall_Library.setRoomMap();

        Library_SecretRoom = new DoorWithKey(Library, SecretRoom, BoringBook);
        Library_SecretRoom.setRoomMap();

        Hall_Schoolyard = new Door(Hall, Schoolyard);
        Hall_Schoolyard.setRoomMap();

        Schoolyard_Gym = new Door(Schoolyard, Gym);
        Schoolyard_Gym.setRoomMap();

        Gym_LockerRoom = new DoorWithKey(Gym, LockerRoom, Key_Gym_LockerRoom);
        Gym_LockerRoom.setRoomMap();

        Schoolyard_SportField = new Door(Schoolyard, SportField);
        Schoolyard_SportField.setRoomMap();

        Hall_FirstFloor = new Door(Hall, FirstFloor);
        Hall_FirstFloor.setRoomMap();
        
        FirstFloor_TheatreClass = new Door(FirstFloor, TheatreClass);
        FirstFloor_TheatreClass.setRoomMap();

        FirstFloor_HistoryClass = new Door(FirstFloor, HistoryClass);
        FirstFloor_HistoryClass.setRoomMap();

        FirstFloor_ChemistryClass = new Door(FirstFloor, ChemistryClass);
        FirstFloor_ChemistryClass.setRoomMap();

        FirstFloor_ArtClass = new Door(FirstFloor, ArtClass);
        FirstFloor_ArtClass.setRoomMap();

        FirstFloor_MathsClass = new Door(FirstFloor, MathsClass);
        FirstFloor_MathsClass.setRoomMap();

        FirstFloor_PhilosophyClass = new Door(FirstFloor, PhilosophyClass);
        FirstFloor_PhilosophyClass.setRoomMap();

        FirstFloor_StaffRoom = new DoorAdult(FirstFloor, StaffRoom);
        FirstFloor_StaffRoom.setRoomMap();

        FirstFloor_PrincipalOffice = new DoorWithCode(FirstFloor, PrincipalOffice, "1923");
        FirstFloor_PrincipalOffice.setRoomMap();

        FirstFloor_StorageRoom = new DoorWithKey(FirstFloor, StorageRoom, Key_FirstFloor_StorageRoom);
        FirstFloor_StorageRoom.setRoomMap();
    }

    
    public Human ArtTeacher;
    public Human ChemistryTeacher;
    public Human HistoryTeacher;
    public Human Janitor;
    public Human Principal;
    public Human Mathsteacher;
    public Human Mom;
    public Human PhilosophyTeacher;
    public Human Theaterteacher;

    public Hero theHero;

    public Human ArtKid;
    public Human Bootlicker;
    public Human ExchangeStudent;
    public Human Jock;
    public Human Nerd;
    public Human Queenbee;
    public Human Rebel;
    public Human TheatreKid;


    public void initCharacters(){
        Principal = new A_Principal(FrontYard);
        Principal.sethumanInRoom();

        ArtTeacher = new A_ArtTeacher(StaffRoom);
        ArtTeacher.sethumanInRoom();

        ChemistryTeacher = new A_ChemistryTeacher(ChemistryClass);
        ChemistryTeacher.sethumanInRoom();

        HistoryTeacher = new A_HistoryTeacher(HistoryClass);
        HistoryTeacher.sethumanInRoom();

        Theaterteacher = new A_TheaterTeacher(StaffRoom);
        Theaterteacher.sethumanInRoom();

        PhilosophyTeacher = new A_PhilosophyTeacher(StaffRoom);
        PhilosophyTeacher.sethumanInRoom();

        Janitor = new A_Janitor(CounselorOffice);
        Janitor.sethumanInRoom();
        
        Mathsteacher = new A_MathsTeacher(MathsClass);
        Mathsteacher.sethumanInRoom();

        Mom = new A_Mom(LivingRoom);
        Mom.sethumanInRoom();
        



        Rebel = new S_Rebel(FrontYard);
        Rebel.sethumanInRoom();

        ExchangeStudent = new S_ExchangeStudent(Schoolyard);
        ExchangeStudent.sethumanInRoom();

        Jock = new S_Jock(SportField);
        Jock.sethumanInRoom();
        
        ArtKid = new S_ArtKid(ArtClass);
        ArtKid.sethumanInRoom();

        TheatreKid = new S_TheatreKid(TheatreClass);
        TheatreKid.sethumanInRoom();

        Queenbee = new S_QueenBee(Hall);
        Queenbee.sethumanInRoom();

        Nerd = new S_Nerd(PhilosophyClass);
        Nerd.sethumanInRoom();

        Bootlicker = new S_BootLicker(Infirmary);
        Bootlicker.sethumanInRoom();

        theHero = new Hero("Michael", "I am myself", 30, Bedroom);
    }

    private Item myDiary;
    private Item TheMap;
    private Item cigarettePack;
    private Item money;
    private Item TraductionBook;
    private Item FireCracker;
    private Item ExamSubject;
    private Item Glue;
    private Item Hair;
    private Item Bread;
    private Item Ingredients;
    private Item LoveLetter;
    private Item RareCard;
    private Item ChalkPowder;
    private Item Glasses;
    private Weapon BaseballBat;
    

    public void initItem(){
        myDiary = new Diary("Diary", "You can write everything you want in here");
        Bedroom.addItem(myDiary);

        TheMap = new SchoolMap("Map", "This is a map of the school");
        Principal.addInBag(TheMap);

        cigarettePack = new Item ("CigarettePack", "This is a cigarette pack, may be useful... to someone else");
        Rebel.addInBag(cigarettePack);

        money = new Item("30$", "This is money, 30 dollars to be precise");
        ExchangeStudent.addInBag(money);

        TraductionBook = new Item ("GermanDictionnary", "This allows me to translate german");
        Library.addItem(TraductionBook);

        FireCracker = new Item ("FireCracker", "This would be the perfect distraction to make the principal leave her office");
        ChemistryTeacher.addInBag(FireCracker);

        ExamSubject = new Item ("ExamSubject", "This is what I've been looking for! With it I'm sure i'll escape military school! :D");
        PrincipalOffice.addItem(ExamSubject);

        Glue = new Item("Glue", "A glue stick");
        Reception.addItem(Glue);

        Hair = new Item("Hair", "a yarn-ish ball of hair. Ew...");
        LockerRoom.addItem(Hair);
        
        Bread = new Item("Bread", "How do you tell how good bread is without tasting it? Not the smell, not the look, but the sound of the crust. Listen. Symphony of crackle. Only great bread sound this way.");
        Cafeteria.addItem(Bread);
        
        Ingredients = new Item("Ingredients", "We have lettuce, hard-boiled eggs, tomatos and cheese. Yummy");
        Cafeteria.addItem(Ingredients);

        LoveLetter = new Item("LoveLetter", "A love letter from Martoni to Venus");
        TheatreKid.addInBag(LoveLetter);

        RareCard = new Item("RareCard", "There is a guy in a black robe and a staff on it");
        HistoryTeacher.addInBag(RareCard);

        ChalkPowder = new Item("ChalkPowder", "This looks like white powder");
        StorageRoom.addItem(ChalkPowder);

        Glasses = new Item("Glasses", "Those are just glasses, I wonder who they belong to");
        ToiletM.addItem(Glasses);

        BaseballBat = new Weapon("BaseballBat", "This is a very violent object, not suitted for kids", 50);
        Gym.addItem(BaseballBat);

        Janitor.addInBag(Key_FirstFloor_StorageRoom);

        Jock.addInBag(Key_Gym_LockerRoom);
        
        Nerd.addInBag(BoringBook);
    }



        public void doAction(Scanner scan){
            String[] comand;
        
            comand = MyScanner.scanning(scan);
            switch (comand[0]){
                case "ATTACK" : ((Hero)theHero).attack(comand[1]);
                break ;

                case "GO" : ((Hero)theHero).go(comand[1], scan);
                break;

                case "INVENTORY" : ((Hero)theHero).inventory();
                break;

                case "TAKE" : ((Hero)theHero).take(comand[1]);
                break;

                case "HELP" : ((Hero)theHero).help();    //Définir help en défaut dans l'interface. 
                break;

                case "LOOK" : 
                if (comand.length == 1) {((Hero)theHero).look();}
                else ((Hero)theHero).look(comand[1]);
                break;

                case "USE" :
                if (comand.length == 2){
                    if (comand[1].equals("Diary"))  { ((Hero)theHero).use(comand[1], scan); }
                    else                            { ((Hero)theHero).use(comand[1]); }
                }
                else ((Hero)theHero).use(comand[1], comand[2]);
                break;

                case "QUIT" : ((Hero)theHero).quit();   //Par défaut dans l'interface ou un truc qui met simplement fin au jeu. 
                break;

                case "TALK" : ((Hero)theHero).talk(comand[1]);
                break;

            }
            if ((theHero.searchInBag("ExamSubject"))  && this.Principal.getRoom().equals(PrincipalOffice)){
                System.out.println("You took the subjects while the principal was here. She saw you and expelled you. GAME OVER");
                theHero.quit();
            }

        }
    
        
}
