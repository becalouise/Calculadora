package Calculadora;

public class Desenvolvedor {
    public String nome;
    public double salarioMensal;

    private int diasUteis = 20;
    private double fhd = 8;

    public double horasUteisMes = diasUteis * fhd;

    public double calculaValorHora() {
        return (salarioMensal / (horasUteisMes));
    }
}
