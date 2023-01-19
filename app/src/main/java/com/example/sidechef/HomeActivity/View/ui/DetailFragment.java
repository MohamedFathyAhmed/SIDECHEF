package com.example.sidechef.HomeActivity.View.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetailFragment extends Fragment {
    Meal meal;
Repository repository;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView mealThumb;
    TextView category;
    YouTubePlayerView videoView;
    TextView country;
    TextView instructions;
    TextView ingredients;
    TextView fav;
//    VideoView videoView;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // get data from frag
        meal = DetailFragmentArgs.fromBundle(getArguments()).getDataMeal();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        repository = Repository.getInstance(requireContext());
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        mealThumb = view.findViewById(R.id.mealThumb);
        category = view.findViewById(R.id.category);
        country = view.findViewById(R.id.country);
        instructions = view.findViewById(R.id.instructions);
        ingredients = view.findViewById(R.id.ingredient);
        fav = view.findViewById(R.id.favorite);
            videoView = view.findViewById(R.id.video);

        // videoView = view.findViewById(R.id.videoView);
        toolbar = view.findViewById(R.id.toolbar);
        setMeal(meal);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void setMeal(Meal meal) {

        fav.setOnClickListener(v->{

            repository.insert(meal);

        });

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(
                getResources().getColor(com.shashank.sony.fancywalkthroughlib.R.color.grey_600));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));

        Glide.with(mealThumb.getContext()).load(meal.getStrMealThumb()).into(mealThumb);

        // setvedio(meal.getStrYoutube(),getActivity());
        collapsingToolbarLayout.setTitle(meal.getStrMeal().toString());
        category.setText(meal.getStrCategory());
        country.setText(meal.getStrArea());
        instructions.setText(meal.getStrInstructions());

        getLifecycle().addObserver((LifecycleObserver) videoView);
        String[] split = meal.getStrYoutube().split("=");

        videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = split[1];
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
        if (!meal.getStrIngredient1().isEmpty()) {
            ingredients.append("\n " + meal.getStrIngredient1() + " : " + meal.getStrMeasure1());
        }
        if (!meal.getStrIngredient2().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient2() + " : " + meal.getStrMeasure2());
        }
        if (!meal.getStrIngredient3().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient3() + " : " + meal.getStrMeasure3());
        }
        if (!meal.getStrIngredient4().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient4() + " : " + meal.getStrMeasure4());
        }
        if (!meal.getStrIngredient5().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient5() + " : " + meal.getStrMeasure5());
        }
        if (!meal.getStrIngredient6().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient6() + " : " + meal.getStrMeasure6());
        }
        if (!meal.getStrIngredient7().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient7() + " : " + meal.getStrMeasure7());
        }
        if (!meal.getStrIngredient8().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient8() + " : " + meal.getStrMeasure8());
        }
        if (!meal.getStrIngredient9().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient9() + " : " + meal.getStrMeasure9());
        }
        if (!meal.getStrIngredient10().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient10() + " : " + meal.getStrMeasure10());
        }
        if (!meal.getStrIngredient11().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient11() + " : " + meal.getStrMeasure11());
        }
        if (!meal.getStrIngredient12().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient12() + " : " + meal.getStrMeasure12());
        }
        if (!meal.getStrIngredient13().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient13() + " : " + meal.getStrMeasure13());
        }
        if (!meal.getStrIngredient14().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient14() + " : " + meal.getStrMeasure14());
        }
        if (!meal.getStrIngredient15().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient15() + " : " + meal.getStrMeasure15());
        }
        if (!meal.getStrIngredient16().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient16() + " : " + meal.getStrMeasure16());
        }
        if (!meal.getStrIngredient17().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient17() + " : " + meal.getStrMeasure17());
        }
        if (!meal.getStrIngredient18().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient18() + " : " + meal.getStrMeasure18());
        }
        if (!meal.getStrIngredient19().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient19() + " : " + meal.getStrMeasure19());
        }
        if (!meal.getStrIngredient20().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient20() + " : " + meal.getStrMeasure20());
        }

    }

//
//    videoView = view.findViewById(R.id.video);
//    getLifecycle().addObserver((LifecycleObserver) videoView);
//    String[] split = mealsItem.getStrYoutube().split("=");
//
//        videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//        @Override
//        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//            String videoId = split[1];
//            youTubePlayer.loadVideo(videoId, 0);
//        }
//    });
//
    // public void setvedio(String videoUrl, Context context) {
    // //
    // videoView.setVideoPath(meal.getStrYoutube());
    // videoView.start();
    // //
    // Uri uri = Uri.parse(videoUrl);
    // // sets the resource from the
    // // videoUrl to the videoView
    // videoView.setVideoURI(uri);
    // // creating object of
    // // media controller class
    // MediaController mediaController = new MediaController(context);
    // // sets the anchor view
    // // anchor view for the videoView
    // mediaController.setAnchorView(videoView);
    // // sets the media player to the videoView
    // mediaController.setMediaPlayer(videoView);
    // // sets the media controller to the videoView
    // videoView.setMediaController(mediaController);
    // // starts the video
    // videoView.start();
    // }
}