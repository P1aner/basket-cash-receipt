package by.receipt.model;

public abstract class DomainEntity {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public DomainEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
