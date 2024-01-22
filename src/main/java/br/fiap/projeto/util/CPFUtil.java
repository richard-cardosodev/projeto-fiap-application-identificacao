package br.fiap.projeto.util;

import java.util.Random;

public class CPFUtil {
    public static String gerarCPF() {
        Random random = new Random();

        // Gera os nove primeiros dígitos do CPF
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        int num4 = random.nextInt(10);
        int num5 = random.nextInt(10);
        int num6 = random.nextInt(10);
        int num7 = random.nextInt(10);
        int num8 = random.nextInt(10);
        int num9 = random.nextInt(10);

        // Calcula o primeiro dígito verificador
        int digito1 = calculaDigitoVerificador(num1, num2, num3, num4, num5, num6, num7, num8, num9);

        // Calcula o segundo dígito verificador
        int digito2 = calculaDigitoVerificador(num1, num2, num3, num4, num5, num6, num7, num8, num9, digito1);

        // Retorna o CPF formatado
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d", num1, num2, num3, num4, num5, num6, num7, num8, num9, digito1, digito2);
    }

    public static String gerarCPFSoNumeros() {
        return gerarCPF().replace(".","").replace("-", "");
    }

    private static int calculaDigitoVerificador(int... numeros) {
        int soma = 0;
        int multiplicador = 2;

        for (int i = numeros.length - 1; i >= 0; i--) {
            soma += numeros[i] * multiplicador;
            multiplicador++;
        }

        int resto = soma % 11;
        int digito = 11 - resto;

        return (digito >= 10) ? 0 : digito;
    }
}
