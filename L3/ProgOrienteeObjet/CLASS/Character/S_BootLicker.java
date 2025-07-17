package CLASS.Character;

import CLASS.Game.Dialogue;
import CLASS.Room.*;

public class S_BootLicker extends Student {
    private Dialogue myDialogue = new Dialogue();
    private Boolean isQuestStarted = false;
    private Boolean isQuestCompleted = false;
    private Boolean isAfterQuest = false;

        
        public S_BootLicker(Room room){
            super("Noah", "He is in a bad shape, looks like he got hit in the face", 10, room);
            myDialogue.setFirstMeet("Oh hey! I'm Noah, don't mind the ice I got into some trouble.");
            myDialogue.setQuestStart("It's the Captain of the baseball team that did that, i wouldn't mind getting some revenge on him but no one is strong enough to beat him by hands.");
            myDialogue.setQuestComplete("Wait you did what? No way! Thank you I feel so much better, revenge is the way to go!");
            myDialogue.setAfterQuest("Hey, that ain't much but the ExamPapers are in the PrincipalOffice upstairs, but be careful, she's in there too!");
            myDialogue.setBeardTalk("Are you my doctor?");
        }

        @Override
        public void talk(Hero hero){
            if (hero.isPersonBeaten("Brian")){
                this.isQuestCompleted = true;
            }
            if (hero.getHasFakeBeard()){
                System.out.println(myDialogue.getBeardTalk());
            }
            else if (isAfterQuest){
                System.out.println(myDialogue.getAfterQuest());
            }
            else if (isQuestCompleted){
                System.out.println(myDialogue.getQuestComplete());
                this.isAfterQuest = true;
            }
            else if (isQuestStarted){
                System.out.println(myDialogue.getQuestStart());
            }
            else{
                System.out.println(myDialogue.getFirstMeet());
                this.isQuestStarted = true;
            }
        }
}
