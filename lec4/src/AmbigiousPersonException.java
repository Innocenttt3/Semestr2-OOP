class AmbiguousPersonException extends Exception {
    private String personName;

    public AmbiguousPersonException(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    @Override
    public String getMessage() {
        return "AmbiguousPersonException: Multiple persons found with the name " + personName;
    }
}
