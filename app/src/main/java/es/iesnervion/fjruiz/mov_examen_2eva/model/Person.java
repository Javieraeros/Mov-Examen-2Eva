package es.iesnervion.fjruiz.mov_examen_2eva.model;

public class Person {
    //region Atributes
    private int id;
    private String name;
    private int age;
    private String telephone;
    private char sex;
    //endregion

    //region Constructors
    public Person() {
    }

    public Person(int id, String name, int age, String telephone, char sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.telephone = telephone;
        this.sex = sex;
    }
    //endregion

    //region Properties
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
    //endregion
}
