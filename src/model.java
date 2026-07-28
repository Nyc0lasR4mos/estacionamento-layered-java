import java.time.LocalDateTime;
import java.time.Duration;


public class Configuracao {
   
    private double valorHora;
    private double horaAdicional;
    private int vagas;
    
    
    public Configuracao() {
    this.vagas = 50;
}   
    
    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getHoraAdicional() {
        return horaAdicional;
    }

    public void setHoraAdicional(double horaAdicional) {
        this.horaAdicional = horaAdicional;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
}

public class Veiculo {

    private String placa;
    private String modelo;
    private String cor;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double valorPago;

    public Veiculo(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public void registrarEntrada() {
        this.horaEntrada = LocalDateTime.now();
    }

    public void registrarSaida(Configuracao config) {
        this.horaSaida = LocalDateTime.now();
        this.valorPago = calcularValor(config);
    }

    private double calcularValor(Configuracao config) {

        if (horaEntrada == null || horaSaida == null) {
            return 0.0;
        }

        long minutos = Duration.between(horaEntrada, horaSaida).toMinutes();

        if (minutos <= 0) {
            return 0.0;
        }

        double horas = Math.ceil(minutos / 60.0);

        if (horas <= 1) {
            return config.getValorHora();
        }

        return config.getValorHora()
                + ((horas - 1) * config.getHoraAdicional());
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    // Setter adicionado
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}

public class Main {

    public static void main(String[] args) {

        Configuracao config = new Configuracao();
        config.setValorHora(12.00);
        config.setHoraAdicional(6.00);

        Veiculo carro = new Veiculo("ABC-1234", "Civic", "Preto");

        System.out.println("Veículo chegando...");
        carro.registrarEntrada();
        System.out.println("Entrada registrada em: " + carro.getHoraEntrada());

        // Simula permanência de 2h15
        carro.setHoraEntrada(LocalDateTime.now().minusMinutes(135));

        System.out.println("\nVeículo saindo...");
        carro.registrarSaida(config);

        System.out.println("Saída registrada em: " + carro.getHoraSaida());

        System.out.printf("Valor total a pagar: R$ %.2f%n",
                carro.getValorPago());
    }
}
