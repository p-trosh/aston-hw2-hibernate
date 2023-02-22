package ru.trosh.astontrainee.domain;

public class Speciality {
    private Long id;
    private String name;

    public Speciality() {
    }

    public Speciality(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Speciality.SpecialityBuilder builder() {
        return new Speciality.SpecialityBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class SpecialityBuilder {
        private Long id;
        private String name;

        SpecialityBuilder() {
        }

        public Speciality.SpecialityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Speciality.SpecialityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Speciality build() {
            return new Speciality(this.id, this.name);
        }
    }
}
