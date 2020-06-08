package ch.bfh.bti7081.s2020.blue.domain;

import ch.bfh.bti7081.s2020.blue.domain.association.patientchallenge.PatientHasChallenge;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Challenge {

  @Id
  @GenericGenerator(name = "pk_sequence", strategy = PostgreSQLConstants.SEQUENCE_GENERATOR_STRATEGY, parameters = {@Parameter(name = "sequence_name", value = "challenge_id_seq"), @Parameter(name = "increment_size", value = "1")})
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
  private Long id;

  private String name;
  private String content;
  private String criteria;

  @OneToMany(mappedBy = "challenge")
  private List<PatientHasChallenge> patients;

  public Challenge() {
  }

  public Challenge(final Long id, final String name, final String content, final String criteria, final List<PatientHasChallenge> patients) {
    this.id = id;
    this.name = name;
    this.content = content;
    this.criteria = criteria;
    this.patients = patients;
  }

  public static Challenge.ChallengeBuilder builder() {
    return new Challenge.ChallengeBuilder();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(final String content) {
    this.content = content;
  }

  public String getCriteria() {
    return this.criteria;
  }

  public void setCriteria(final String criteria) {
    this.criteria = criteria;
  }

  public List<PatientHasChallenge> getPatients() {
    return this.patients;
  }

  public void setPatients(final List<PatientHasChallenge> patients) {
    this.patients = patients;
  }

  public static class ChallengeBuilder {

    private Long id;
    private String name;
    private String content;
    private String criteria;
    private List<PatientHasChallenge> patients;

    ChallengeBuilder() {
    }

    public Challenge.ChallengeBuilder id(final Long id) {
      this.id = id;
      return this;
    }

    public Challenge.ChallengeBuilder name(final String name) {
      this.name = name;
      return this;
    }

    public Challenge.ChallengeBuilder content(final String content) {
      this.content = content;
      return this;
    }

    public Challenge.ChallengeBuilder criteria(final String criteria) {
      this.criteria = criteria;
      return this;
    }

    public Challenge.ChallengeBuilder patients(final List<PatientHasChallenge> patients) {
      this.patients = patients;
      return this;
    }

    public Challenge build() {
      return new Challenge(this.id, this.name, this.content, this.criteria, this.patients);
    }

    @Override
    public java.lang.String toString() {
      return "Challenge.ChallengeBuilder(id=" + this.id + ", name=" + this.name + ", content=" + this.content + ", criteria=" + this.criteria + ", patients=" + this.patients + ")";
    }
  }
}
