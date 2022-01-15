





package de.gyko.miau;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MuhPlayer {

    private Clip clip;

    public MuhPlayer() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(MuhPlayer.class.getResourceAsStream("6f94a1e3deb3b2e685c0ed304df8573a.wav")));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.clip.stop();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var2) {
            var2.printStackTrace();
        }
    }

    public void play() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < 10; ++i) {
            s.append("UUU");
            System.out.println("M" + s + "H!");
        }

        this.clip.setMicrosecondPosition(0L);

        this.clip.start();
        try {
            System.out.println((this.clip.getMicrosecondLength() / 1000L) / 1000d);
            Thread.sleep(this.clip.getMicrosecondLength() / 1000L);
        } catch (InterruptedException var7) {
            var7.printStackTrace();
        }

        this.clip.stop();

    }
}