package Model;

public class NewBox {
    private int box_Id;
    private String name_box;
    private int quantity_box;
    private int chance;

    public NewBox(int box_Id, String name_box, int quantity_box, int chance) {
        this.box_Id = box_Id;
        this.name_box = name_box;
        this.quantity_box = quantity_box;
        this.chance = chance;
    }

    public int getBox_Id() {
        return box_Id;
    }

    public void setBox_Id(int box_Id) {
        this.box_Id = box_Id;
    }

    public String getName_box() {
        return name_box;
    }

    public void setName_box(String name_box) {
        this.name_box = name_box;
    }

    public int getQuantity_box() {
        return quantity_box;
    }

    public void setQuantity_box(int quantity_box) {
        this.quantity_box = quantity_box;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    @Override
    public String toString() {
        return "NewBox{" +
                "box_Id=" + box_Id +
                ", name_box='" + name_box + '\'' +
                ", quantity_box=" + quantity_box +
                ", chance=" + chance +
                '}';
    }
}
