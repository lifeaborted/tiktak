package com.example.dedradura;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    private VideoView videoView;
    private List<Integer> videoList;
    private int currentVideoIndex = 0;
    private GestureDetector gestureDetector;

    public void vidVideo() {

    }

    public void addVideo() {

    }

    public void picVideo() {

    }

    public VideoFragment() {
        // Required empty public constructor
    }

    public void animateContent(View view, float translationX) {
        ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", translationX);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateX, scaleX, scaleY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video, container, false);
        rootView.setClickable(true);
        rootView.setFocusable(true);
        videoView = rootView.findViewById(R.id.videoView);

        // Список видео из ресурсов
        videoList = new ArrayList<>();
        videoList.add(R.raw.kit);
        videoList.add(R.raw.kok);
        videoList.add(R.raw.kek);

        playVideo(currentVideoIndex);

        // Воспроизводить заново при окончании
        videoView.setOnCompletionListener(mp -> mp.start());

        // Обработчик жестов
        gestureDetector = new GestureDetector(getContext(), new GestureListener());

        rootView.setOnTouchListener((v, event) -> {
            v.performClick();
            return gestureDetector.onTouchEvent(event);
        });

        return rootView;
    }

    private void playVideo(int index) {
        if (index >= 0 && index < videoList.size()) {
            Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + videoList.get(index));
            videoView.setVideoURI(videoUri);
            videoView.start();
        }
    }

    private void nextVideo() {
        if (currentVideoIndex < videoList.size() - 1) {
            currentVideoIndex++;
            playVideo(currentVideoIndex);
        }
    }

    private void previousVideo() {
        if (currentVideoIndex > 0) {
            currentVideoIndex--;
            playVideo(currentVideoIndex);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (videoView.isPlaying()) {
                videoView.pause();
            } else {
                videoView.start();
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY < 0) {
                    nextVideo(); // Свайп вверх
                } else {
                    previousVideo(); // Свайп вниз
                }
                return true;
            }
            return false;
        }
    }
}
