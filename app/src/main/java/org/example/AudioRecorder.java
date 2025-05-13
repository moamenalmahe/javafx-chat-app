package org.example;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioRecorder {

    private static final long RECORD_TIME = 5000; // 5 ثوانٍ
    private static final String FILE_PATH = "recorded.wav";

    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private TargetDataLine line;

    public void startRecording() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                throw new RuntimeException("خطأ: جهاز الصوت غير مدعوم.");
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            File wavFile = new File(FILE_PATH);

            // بدء التسجيل في Thread منفصل
            Thread recordingThread = new Thread(() -> {
                try (AudioInputStream ais = new AudioInputStream(line)) {
                    AudioSystem.write(ais, fileType, wavFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            recordingThread.start();

            // الانتظار لمدة 5 ثوانٍ
            Thread.sleep(RECORD_TIME);

            finish();

        } catch (LineUnavailableException | InterruptedException ex) {
            throw new RuntimeException("فشل في التسجيل: " + ex.getMessage(), ex);
        }
    }

    private void finish() {
        line.stop();
        line.close();
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public static String getRecordedFilePath() {
        return FILE_PATH;
    }
}
