import java.util.Objects;

public class Enrollee {
    private String userId;
    private String firstName;
    private String lastName;
    private Integer version;
    private String insuranceCompany;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollee enrollee = (Enrollee) o;
        return Objects.equals(userId, enrollee.userId) && Objects.equals(firstName, enrollee.firstName) && Objects.equals(lastName, enrollee.lastName) && Objects.equals(version, enrollee.version) && Objects.equals(insuranceCompany, enrollee.insuranceCompany);
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", version=" + version +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, version, insuranceCompany);
    }
}
