package hello.hellospring.domain;

public class Member {

    private Long id;//시스템에서 정하는id (시퀀스)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
