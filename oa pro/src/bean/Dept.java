package bean;

import java.util.Objects;

public class Dept {
    private String id;
    private String name;
    private String local;

    public Dept() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(id, dept.id) && Objects.equals(name, dept.name) && Objects.equals(local, dept.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, local);
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", local='" + local + '\'' +
                '}';
    }
}
