public class MonsterList {

    private MonsterNode head;

    public MonsterList() {
        this.head = null;
    }

    public void insertAtFront(Monster monster) {
        if (head == null) {
            MonsterNode monsterNode = new MonsterNode(monster);
            head = monsterNode;
        } else {
            MonsterNode monsterNode = new MonsterNode(monster);
            monsterNode.setNext(head);
            head = monsterNode;
        }
    }

    public Monster removeEnd() {
        Monster monster;

        if (head == null) {
            return null;
        } else if (head.getNext() == null) {
            monster = head.getMonster();
            this.head = null;
            return monster;
        } else {
            MonsterNode p = head;
            while (true) {
                if (p.getNext().getNext() == null) {
                    monster = p.getNext().getMonster();
                    p.setNext(null);
                    return monster;
                } else {
                    p = p.getNext();
                }
            }
        }
        
    }

    public MonsterNode getHead(){
        return this.head;
    }
}
