package fb_projectgame.Control;

public class MusicManager {

    private  Music soundTrack;

    private Music shoot;

    private  Music destruction;
    private Music jump;
    private Music gameOver;

    private Music win;
    private static MusicManager musicManager;

    private MusicManager() {
        soundTrack = new Music("/src/main/resources/Sounds/soundTrack.wav");
        shoot = new Music("/src/main/resources/Sounds/shoot.wav");
        destruction = new Music("/src/main/resources/Sounds/destruction.wav");
        jump = new Music("/src/main/resources/Sounds/jump.wav");
        gameOver = new Music("/src/main/resources/Sounds/gameOver.wav");
        win = new Music("/src/main/resources/Sounds/win.wav");
    }

    public static MusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    public void setSoundTrack(Music soundTrack) {
        this.soundTrack = soundTrack;
    }

    public void setShoot(Music shoot) {
        this.shoot = shoot;
    }

    public void setJump(Music jump) {
        this.jump = jump;
    }

    public void setDestruction(Music destruction) {
        this.destruction = destruction;
    }

    public void setGameOver(Music gameOver) {
        this.gameOver = gameOver;
    }

    public void setWin(Music win) {
        this.win = win;
    }


    public void start(Sounds sound) {
        switch(sound) {
            case SOUNDTRACK -> soundTrack.startLoop();
            case SHOOT -> shoot.start();
            case DESTRUCTIONS -> destruction.start();
            case JUMP -> jump.start();
            case GAMEOVER -> gameOver.start();
            case WIN -> win.start();
        }
    }


    public boolean isPlaying(Sounds sound) {
        return switch (sound) {
            case SOUNDTRACK -> soundTrack.isPlaying();
            case SHOOT -> shoot.isPlaying();
            case DESTRUCTIONS -> destruction.isPlaying();
            case JUMP -> jump.isPlaying();
            case GAMEOVER -> gameOver.isPlaying();
            case WIN -> win.isPlaying();
        };
    }

    public void stopAll() {
        soundTrack.stop();
        shoot.stop();
        destruction.stop();
        jump.stop();
        gameOver.stop();
        win.stop();
    }

}
