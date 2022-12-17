package Calculadora;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ConexaoMySql db1 = new ConexaoMySql();
        String sql = "Create table orcamento" +
                " (id_orcamento int primary key auto_increment," +
                " valor float)";

        Scanner sc = new Scanner(System.in);
        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.nome = "Grupo A3";
        desenvolvedor.salarioMensal = 2000.00;

        System.out.println("Nome: " + desenvolvedor.nome);
        System.out.println("Salário Mensal Pretendido: " + desenvolvedor.salarioMensal);
        System.out.println("Horas Mês: " + desenvolvedor.horasUteisMes);
        double valorHora = desenvolvedor.calculaValorHora();
        System.out.println("Valor/Hora: " + valorHora);

        ItemSistema[] itens = new ItemSistema[4];

        ItemSistema telaDeLogin = new ItemSistema();
        telaDeLogin.tipo = "Login";
        telaDeLogin.complexidade = 3;
        itens[0] = telaDeLogin;

        ItemSistema telaNormal = new ItemSistema();
        telaNormal.tipo = "Tela Normal";
        telaNormal.complexidade = 1;
        itens[1] = telaNormal;

        ItemSistema telaDeSaida = new ItemSistema();
        telaDeSaida.tipo = "Tela Saída";
        telaDeSaida.complexidade = 2;
        itens[2] = telaDeSaida;

        int decisao;
        int totalHoras = 0;
        double valorBase;
        double encargos;
        double valorTotal;

        do {

            System.out.println(
                    "\n Selecione um tipo de tela \n 1 - Tela de Login \n 2 - Tela Normal \n 3 - Tela de Saída");
            int tipoTela = sc.nextInt();
            if ((tipoTela < 1) || (tipoTela > 3)) {
                System.out.println("Seleção inválida, reinicie o programa");
                sc.close();
                return;

            }

            System.out.println("Qual a quantidade da tela?");
            int quantidade = sc.nextInt();

            ItemSistema telaSelecionada = itens[tipoTela - 1];
            totalHoras += quantidade * telaSelecionada.calcularQuantidadeHoras();

            System.out.println("\n Deseja selecionar mais telas? \n 1 - Sim \n 2 - Não");
            decisao = sc.nextInt();

        } while (decisao == 1);

        sc.close();

        valorBase = totalHoras * valorHora;
        encargos = (valorBase / 100) * 30;
        valorTotal = valorBase + encargos;

        Orcamento orcamento = new Orcamento();
        orcamento.desenvolvedor = desenvolvedor;
        orcamento.totalHoras = totalHoras;
        orcamento.valor = valorTotal;


        System.out.println("Valor Total do Orçamento: R$ " + orcamento.valor);

        System.out.println("Valor dos encargos, risco e gestão: R$ " + encargos);

        db1.openDataBase();
        db1.executaQuery(sql);

        sql = "insert orcamento " + "(valor) values ('" + orcamento.valor + "')";
        db1.executaQuery(sql);
        db1.closeDatabase();

    }
}
