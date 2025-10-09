package course01;

public class Lowyer extends Employee{
    private int workedHours;
    private double hourlyRate;

    public Lowyer(String name, double salary, int workedHours, double hourlyRate) {
        super(name, salary);
        this.workedHours = workedHours;
        this.hourlyRate = hourlyRate;
    }

    public void setWorked(int hours, double rate) {
        workedHours = hours;
        hourlyRate = rate;
    }
    @Override
    public double getSalary() {
        return super.getSalary() + workedHours*hourlyRate;
    }

    @Override
    public String toString() {
        return "Lowyer{" +
                "workedHours=" + workedHours +
                ", hourlyRate=" + hourlyRate +
                '}';
    }

    public static void main(String[] args) {
        Lowyer l =new Lowyer("Pedro",1500,30,60);

        System.out.println(l);
        System.out.println(l.getSalary());
        System.out.println(l.getName());
    }
}
