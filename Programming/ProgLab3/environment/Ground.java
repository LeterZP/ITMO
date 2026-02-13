package environment;

enum Ground {
    DIRT(0.3),
    GRASS(0.5),
    ASPHALT(0.8),
    SAND(0.2);

    private final double comfort;

    Ground(double comfort) {
        this.comfort = comfort;
    }

    public double getComfort() {
        return comfort;
    }
}
