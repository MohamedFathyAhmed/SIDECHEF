package com.example.sidechef.HomeActivity.View.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.model.models.Meal;
import com.google.android.material.appbar.CollapsingToolbarLayout;



public class DetailFragment extends Fragment {
Meal meal;

    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView mealThumb;
    TextView category;
    TextView country;
    TextView instructions;
    TextView ingredients;
    TextView fav;
    VideoView videoView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         meal=new Meal( "52842", "Broccoli & Stilton soup",
                null, "Starter", "British", "Heat the rapeseed oil in a large saucepan and then add the onions. Cook on a medium heat until soft. Add a splash of water if the onions start to catch. Add the celery, leek, potato and a knob of butter. Stir until melted, then cover with a lid. Allow to sweat for 5 minutes. Remove the lid. Pour in the stock and add any chunky bits of broccoli stalk. Cook for 10 – 15 minutes until all the vegetables are soft. Add the rest of the broccoli and cook for a further 5 minutes. Carefully transfer to a blender and blitz until smooth. Stir in the stilton, allowing a few lumps to remain. Season with black pepper and serve.", "https://www.themealdb.com/images/media/meals/tvvxpv1511191952.jpg", "null", "https://www.youtube.com/watch?v=_HgVLpmNxTY", "Rapeseed Oil", "Onion", "Celery", "Leek", "Potatoes", "Butter", "Vegetable Stock", "Broccoli",
                "Stilton Cheese", "", "", "", "", "", "", "", "", "", "", "", "2 tblsp ", "1 finely chopped ", "1", "1 sliced", "1 medium", "1 knob", "1 litre hot", "1 Head chopped", "140g", "", "", "",
                "", "", "", "", "", "", "", "",
                "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"
                , "null", "null","null");
   }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get data from frag
        meal = DetailFragmentArgs.fromBundle(getArguments()).getDataMeal();

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        collapsingToolbarLayout=view.findViewById(R.id.collapsing_toolbar);
        mealThumb=view.findViewById(R.id.mealThumb);
        category=view.findViewById(R.id.category);
        country=view.findViewById(R.id.country);
        instructions=view.findViewById(R.id.instructions);
        ingredients=view.findViewById(R.id.ingredient);
        fav=view.findViewById(R.id.favorite);
         videoView = view.findViewById(R.id.videoView);

      setMeal(meal);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    public void setMeal(Meal meal) {
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorPrimary));

        Glide.with(mealThumb.getContext()).load(meal.getStrMealThumb()).into(mealThumb);


       // setvedio(meal.getStrYoutube(),getActivity());
       collapsingToolbarLayout.setTitle(meal.getStrMeal().toString());
        category.setText(meal.getStrCategory());
        country.setText(meal.getStrArea());
        instructions.setText(meal.getStrInstructions());

        if (!meal.getStrIngredient1().isEmpty()) {
            ingredients.append("\n " + meal.getStrIngredient1()+" : "+ meal.getStrMeasure1());
        }
        if (!meal.getStrIngredient2().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient2()+" : "+ meal.getStrMeasure2());
        }
        if (!meal.getStrIngredient3().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient3()+" : "+ meal.getStrMeasure3());
        }
        if (!meal.getStrIngredient4().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient4()+" : "+ meal.getStrMeasure4());
        }
        if (!meal.getStrIngredient5().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient5()+" : "+ meal.getStrMeasure5());
        }
        if (!meal.getStrIngredient6().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient6()+" : "+ meal.getStrMeasure6());
        }
        if (!meal.getStrIngredient7().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient7()+" : "+ meal.getStrMeasure7());
        }
        if (!meal.getStrIngredient8().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient8()+" : "+ meal.getStrMeasure8());
        }
        if (!meal.getStrIngredient9().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient9()+" : "+ meal.getStrMeasure9());
        }
        if (!meal.getStrIngredient10().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient10()+" : "+ meal.getStrMeasure10());
        }
        if (!meal.getStrIngredient11().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient11()+" : "+ meal.getStrMeasure11());
        }
        if (!meal.getStrIngredient12().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient12()+" : "+ meal.getStrMeasure12());
        }
        if (!meal.getStrIngredient13().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient13()+" : "+ meal.getStrMeasure13());
        }
        if (!meal.getStrIngredient14().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient14()+" : "+ meal.getStrMeasure14());
        }
        if (!meal.getStrIngredient15().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient15()+" : "+ meal.getStrMeasure15());
        }
        if (!meal.getStrIngredient16().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient16()+" : "+ meal.getStrMeasure16());
        }
        if (!meal.getStrIngredient17().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient17()+" : "+ meal.getStrMeasure17());
        }
        if (!meal.getStrIngredient18().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient18()+" : "+ meal.getStrMeasure18());
        }
        if (!meal.getStrIngredient19().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient19()+" : "+ meal.getStrMeasure19());
        }
        if (!meal.getStrIngredient20().isEmpty()) {
            ingredients.append("\n \u2022 " + meal.getStrIngredient20()+" : "+ meal.getStrMeasure20());
        }

    }


//    public void setvedio(String videoUrl, Context context) {
//        //
//        videoView.setVideoPath(meal.getStrYoutube());
//        videoView.start();
//        //
//       Uri uri = Uri.parse(videoUrl);
//        // sets the resource from the
//        // videoUrl to the videoView
//        videoView.setVideoURI(uri);
//        // creating object of
//        // media controller class
//        MediaController mediaController = new MediaController(context);
//        // sets the anchor view
//        // anchor view for the videoView
//        mediaController.setAnchorView(videoView);
//        // sets the media player to the videoView
//        mediaController.setMediaPlayer(videoView);
//        // sets the media controller to the videoView
//        videoView.setMediaController(mediaController);
//        // starts the video
//        videoView.start();
//    }
}