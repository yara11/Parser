package parser;

public enum Literals {
    // To be used as EPSILON throughout the project.
    EPS{
    @Override
        public String toString() {
            return "\\L";
        }
    },
}