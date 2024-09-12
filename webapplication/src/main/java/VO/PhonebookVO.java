package VO;
public class PhonebookVO {
    private int id;             
    private String name;       
    private String hp;          
    private String memo;       

    public PhonebookVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public PhonebookVO(int id, String name, String hp, String memo) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.memo = memo;
    }

	@Override
	public String toString() {
		return "PhonebookVO [id=" + id + ", name=" + name + ", hp=" + hp + ", memo=" + memo + "]";
	}

   
}
