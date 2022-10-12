package fb_projectgame.Control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MusicManagerTest extends Assertions {

    MusicManager musicManagerSpy;
    Music soundTrackMock = Mockito.mock(Music.class);
    Music shootMock = Mockito.mock(Music.class);

    Music jumpMock = Mockito.mock(Music.class);
    Music destructionMock = Mockito.mock(Music.class);
    Music gameOverMock = Mockito.mock(Music.class);

    Music winMock = Mockito.mock(Music.class);

    @BeforeEach
    void init() {
        MusicManager musicManager = MusicManager.getInstance();
        musicManager.setSoundTrack(soundTrackMock);
        musicManager.setShoot(shootMock);
        musicManager.setJump(jumpMock);
        musicManager.setDestruction(destructionMock);
        musicManager.setGameOver(gameOverMock);
        musicManager.setWin(winMock);
        musicManagerSpy = Mockito.spy(musicManager);
    }

    @Test
    void startSoundTrack() {
        musicManagerSpy.start(Sounds.SOUNDTRACK);
        Mockito.verify(soundTrackMock, Mockito.times(1)).startLoop();
    }

    @Test
    void startShoot() {
        musicManagerSpy.start(Sounds.SHOOT);
        Mockito.verify(shootMock, Mockito.times(1)).start();
    }

    @Test
    void startDestruction() {
        musicManagerSpy.start(Sounds.DESTRUCTIONS);
        Mockito.verify(destructionMock, Mockito.times(1)).start();
    }

    @Test
    void startGameOver() {
        musicManagerSpy.start(Sounds.GAMEOVER);
        Mockito.verify(gameOverMock, Mockito.times(1)).start();
    }

    @Test
    void startJump() {
        musicManagerSpy.start(Sounds.JUMP);
        Mockito.verify(jumpMock, Mockito.times(1)).start();
    }

    @Test
    void startWin() {
        musicManagerSpy.start(Sounds.WIN);
        Mockito.verify(winMock, Mockito.times(1)).start();
    }

    @Test
    void isPlayingSoundTrackTrue() {
        Mockito.when(soundTrackMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SOUNDTRACK);
        assertTrue(playing);
    }

    @Test
    void isPlayingSoundTrackFalse() {
        Mockito.when(soundTrackMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SOUNDTRACK);
        assertFalse(playing);
    }

    @Test
    void isPlayingShootTrue() {
        Mockito.when(shootMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SHOOT);
        assertTrue(playing);
    }

    @Test
    void isPlayingShootFalse() {
        Mockito.when(shootMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.SHOOT);
        assertFalse(playing);
    }

    @Test
    void isPlayingJumpTrue() {
        Mockito.when(jumpMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.JUMP);
        assertTrue(playing);
    }

    @Test
    void isPlayingJumpFalse() {
        Mockito.when(jumpMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.JUMP);
        assertFalse(playing);
    }

    @Test
    void isPlayingDestructionTrue() {
        Mockito.when(destructionMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.DESTRUCTIONS);
        assertTrue(playing);
    }

    @Test
    void isPlayingDestructionFalse() {
        Mockito.when(destructionMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.DESTRUCTIONS);
        assertFalse(playing);
    }

    @Test
    void isPlayingGameOverTrue() {
        Mockito.when(gameOverMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.GAMEOVER);
        assertTrue(playing);
    }

    @Test
    void isPlayingGameOverFalse() {
        Mockito.when(gameOverMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.GAMEOVER);
        assertFalse(playing);
    }

    @Test
    void isPlayingWinTrue() {
        Mockito.when(winMock.isPlaying()).thenReturn(true);
        boolean playing = musicManagerSpy.isPlaying(Sounds.WIN);
        assertTrue(playing);
    }

    @Test
    void isPlayingWinFalse() {
        Mockito.when(winMock.isPlaying()).thenReturn(false);
        boolean playing = musicManagerSpy.isPlaying(Sounds.WIN);
        assertFalse(playing);
    }

    @Test
    void stopAll() {
        musicManagerSpy.stopAll();
        Mockito.verify(soundTrackMock, Mockito.times(1)).stop();
        Mockito.verify(shootMock, Mockito.times(1)).stop();
        Mockito.verify(jumpMock, Mockito.times(1)).stop();
        Mockito.verify(destructionMock, Mockito.times(1)).stop();
        Mockito.verify(gameOverMock, Mockito.times(1)).stop();
        Mockito.verify(winMock, Mockito.times(1)).stop();
    }


}
