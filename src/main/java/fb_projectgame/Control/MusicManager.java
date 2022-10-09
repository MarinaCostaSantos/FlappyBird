package fb_projectgame.Control;

public class MusicManager {

    private final Music soundTrack;

    private final Music welldone;
    private final Music jump;
    private final Music gameOver;
    private static MusicManager musicManager;

    private MusicManager() {
        soundTrack = new Music("/src/main/resources/Sounds/soundTrack.wav");
        welldone = new Music("/src/main/resources/Sounds/rocket.wav");
        jump = new Music("/src/main/resources/Sounds/destruction.wav");
        gameOver = new Music("/src/main/resources/Sounds/gameOver.wav");
    }

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }


    public void start(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.startLoop();
            case WELLDONE -> welldone.start();
            case JUMP -> jump.start();
            case GAMEOVER -> gameOver.startLoop();
        }
    }

    public void stop(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.stop();
            case GAMEOVER -> gameOver.stop();
        }
    }

    public boolean isPlaying(Sounds sound) {
        return switch (sound) {
            case SOUNDTRACK -> soundTrack.isPlaying();
            case WELLDONE -> welldone.isPlaying();
            case JUMP -> jump.isPlaying();
            case GAMEOVER -> gameOver.isPlaying();
        };
    }

    public void stopAll() {
        soundTrack.stop();
        welldone.stop();
        jump.stop();
        gameOver.stop();
    }

}
