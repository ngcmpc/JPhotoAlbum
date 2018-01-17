package fi.iki.jka;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class JPhotoFrameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void switchOutputStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void slideShowTesting() throws Exception {
        JPhotoFrame photoFrame = new JPhotoFrame() {
            @Override
            protected void startSlideshow(int interval) {
                assertThat(interval, equalTo(5000));
            }

            @Override
            public void setTitle() {
            }
        };
        ActionEvent actionStartSlideshow = new ActionEvent(photoFrame, 0, JPhotoMenu.A_SLIDESHOW);

//        JPhotoCollection photos = new JPhotoCollection();
//        photos.add(0, new JPhoto());
//        photos.setTitle("collection1");
//        photoFrame.list = new JPhotoList(photos, 200);
//        photoFrame.photos = photos;

        photoFrame.actionPerformed(actionStartSlideshow);

        assertFalse(outContent.toString().contains("Not implemented: "));
    }

    //TODO: Finish this test
    @Test
    public void slideShowFastTesting() throws Exception {
        JPhotoFrame photoFrame = new JPhotoFrame() {
            @Override
            protected void startSlideshow(int interval) {
                assertThat(interval, equalTo(1000));
            }

            @Override
            public void setTitle() {
            }
        };
        ActionEvent actionStartSlideshow = new ActionEvent(photoFrame, 0, JPhotoMenu.A_SLIDESHOW_FAST);
        photoFrame.actionPerformed(actionStartSlideshow);

        assertFalse(outContent.toString().contains("Not implemented: "));
    }

    @Test
    public void photoShowIntervalStaysTheSameBetweenTheConstructorAndTheTimer() {
        final List<Integer> intervals = new ArrayList<Integer>();
        intervals.add(5000);

        JPhotoCollection photos = new JPhotoCollection();
        photos.add(0, new JPhoto());
        JPhotoList jPhotoList = new JPhotoList(photos, 200);

        JPhotoShow jPhotoShow = new JPhotoShow(photos, intervals.get(0), jPhotoList) {
            @Override
            protected void startTimer(int interval) {
                intervals.add(interval);
            }
        };

        assertThat(intervals.get(0), equalTo(intervals.get(1)));
    }

    @After
    public void resetsOutputStream() {
        System.setOut(System.out);
    }
}