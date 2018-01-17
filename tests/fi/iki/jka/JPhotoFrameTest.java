package fi.iki.jka;

import org.junit.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JPhotoFrameTest {
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
    }
}