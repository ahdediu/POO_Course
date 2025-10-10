package course01;

public class Lawyer extends Employee{
    private int workedHours;
    private double hourlyRate;

    public Lawyer(String name, double salary, int workedHours, double hourlyRate) {
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

    //@Override
    public String toString() {
        return "Lowyer{" +
                "workedHours=" + workedHours +
                ", hourlyRate=" + hourlyRate +
                '}';
    }

    public static void main(String[] args) {
        Lawyer l =new Lawyer("Pedro",1500,30,60);

        System.out.println(l);
        System.out.println(l.getSalary());
        System.out.println(l.getName());
    }
}
