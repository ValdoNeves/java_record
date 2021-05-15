package br.com.valdoneves.configuracao;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ConfiguracaoDoGravador {

    //Diretorio onde será guardado o arquivo, e o nome do arquivo.wav
    File wavFile = new File("./nomeDoArquivo.wav");

    //Formato do arquivo definido pela biblioteca
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    //Canal pelo qual será capturado o áudio
    TargetDataLine line;

    //Definindo o formato de audio wav
    AudioFormat getAudioFormat(){
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;

        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        return format;
    }

    //iniciando a gravação
    public void start(){
        try {
            //pega o formato de audio definido a cima
            AudioFormat format = getAudioFormat();

            //informações sobre a linha de gravação
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            //verificar se o sistema de captação esta funcionando corretamente, se nao estiver funcionando
            //o sistema irá parar
            if(!AudioSystem.isLineSupported(info)){
                System.exit(0);
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            //pega a linha de gravação em uma nova stream
            AudioInputStream ais = new AudioInputStream(line);

            AudioSystem.write(ais,fileType,wavFile);

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void finish(){
        line.stop();
        line.close();
    }

}
