package model;

public class Classes {
    int classesId;
    String className;
    String classDescription;

    public Classes(String className, String classDescription) {
        this.className = className;
        this.classDescription = classDescription;
    }

    public Classes() {
    }

    public Classes(int classesId, String className, String classDescription) {
        this.classesId = classesId;
        this.className = className;
        this.classDescription = classDescription;
    }

    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
}
