public class MonsterNode {
    
    private Monster monster;
    private MonsterNode next;

    public MonsterNode(Monster monster) {
        this.monster = monster;
        this.next = null;
    }

    public Monster getMonster() {
        return this.monster;
    }

    public MonsterNode getNext() {
        return this.next;
    }

    public void setNext(MonsterNode next) {
        this.next = next;
    }

}
