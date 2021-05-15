package br.com.valdoneves.gravacao;

import br.com.valdoneves.configuracao.ConfiguracaoDoGravador;

public class Gravador {

    public static void main(String[] args) {
        //instanciando classe
        ConfiguracaoDoGravador gravador = new ConfiguracaoDoGravador();
        //criando Thread
        Thread iniciarGravacao = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(60000);//um minuto
                    gravador.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //inicia a Thread
        iniciarGravacao.start();
        //inicia a gravação
        gravador.start();
    }
}
