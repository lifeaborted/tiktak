package com.example.dedradura;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class PhotoFragment extends Fragment {

    private ImageView imageView;
    private List<Integer> photoList;
    private int currentPhotoIndex = 0;
    private GestureDetector gestureDetector;

    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo, container, false);
        rootView.setClickable(true);
        rootView.setFocusable(true);
        imageView = rootView.findViewById(R.id.imageView);

        // Список фотографий из ресурсов
        photoList = new ArrayList<>();
        photoList.add(R.raw.pic1);
        photoList.add(R.raw.pic2);
        photoList.add(R.raw.pic3);
        photoList.add(R.raw.pic4);

        displayPhoto(currentPhotoIndex);

        // Обработчик жестов
        gestureDetector = new GestureDetector(getContext(), new GestureListener());

        rootView.setOnTouchListener((v, event) -> {
            v.performClick();
            return gestureDetector.onTouchEvent(event);
        });

        return rootView;
    }

    private void displayPhoto(int index) {
        if (index >= 0 && index < photoList.size()) {
            imageView.setImageResource(photoList.get(index));
        }
    }

    private void nextPhoto() {
        if (currentPhotoIndex < photoList.size() - 1) {
            currentPhotoIndex++;
            displayPhoto(currentPhotoIndex);
        }
    }

    private void previousPhoto() {
        if (currentPhotoIndex > 0) {
            currentPhotoIndex--;
            displayPhoto(currentPhotoIndex);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY < 0) {
                    nextPhoto(); // Свайп вверх
                } else {
                    previousPhoto(); // Свайп вниз
                }
                return true;
            }
            return false;
        }
    }
}
