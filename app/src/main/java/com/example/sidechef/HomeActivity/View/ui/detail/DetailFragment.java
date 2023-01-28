package com.example.sidechef.HomeActivity.View.ui.detail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.bumptech.glide.Glide;
import com.example.sidechef.R;
import com.example.sidechef.Utils.Utils;
import com.example.sidechef.Utils.YourPreference;
import com.example.sidechef.model.Repository;
import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.Time;
import com.example.sidechef.model.models.Week;
import com.example.sidechef.model.models.WeekMeals;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailFragment extends Fragment {
    Meal meal;
Repository repository;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView mealThumb;
    TextView category;
    YouTubePlayerView videoView;
    TextView country;
    TextView instructions;
    RecyclerView ingredientsRV;
    TextView fav;
    TextView nameMeal;
    TextView BtnAddToPlan;
    LinearLayout btnlinearLayout;
    BottomNavigationView navBar;
    TextView BtnAddToCal;
    List<String>ingredients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredients=new ArrayList<>();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        navBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //hideen bar
         navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);
        // get data from frag
        meal = DetailFragmentArgs.fromBundle(getArguments()).getDataMeal();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        repository = Repository.getInstance(requireContext());
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        mealThumb = view.findViewById(R.id.mealThumb);
        category = view.findViewById(R.id.category);
        country = view.findViewById(R.id.country);
        instructions = view.findViewById(R.id.instructions);
        ingredientsRV = view.findViewById(R.id.rv_ingrediant);
        ingredientsRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        ingredientsRV.setLayoutManager(layoutManager);
        fav = view.findViewById(R.id.favorite);
        BtnAddToPlan = view.findViewById(R.id.BtnAddToPlan);
        videoView = view.findViewById(R.id.video);
        nameMeal = view.findViewById(R.id.nameMeal);
        btnlinearLayout=view.findViewById(R.id.btnLinearLayout);
        BtnAddToCal =view.findViewById(R.id.BtnAddToCal);
        String email  = YourPreference.getInstance(requireContext()).getData("email");
        if( email.equals("") ){
            btnlinearLayout.setVisibility(View.GONE);
            BtnAddToCal.setVisibility(View.GONE);
        }else {
            btnlinearLayout.setVisibility(View.VISIBLE);
            BtnAddToCal.setVisibility(View.VISIBLE);
        }

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

        BtnAddToCal.setOnClickListener(v->{

            Calendar calendarEvent = Calendar.getInstance();
            Intent i = new Intent(Intent.ACTION_EDIT);
            i.setType("vnd.android.cursor.item/event");
            i.putExtra("beginTime", calendarEvent.getTimeInMillis());
            i.putExtra("allDay", false);
            i.putExtra("rule", "FREQ=YEARLY");
            i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
            i.putExtra("title", meal.getStrMeal());
            requireContext().startActivity(i);

        });

        BtnAddToPlan.setOnClickListener(v->{


            AlertDialog.Builder builderSingle = new AlertDialog.Builder(requireContext());
            builderSingle.setIcon(R.drawable.ic_dashboard_black_24dp);
            builderSingle.setTitle("Select day:-");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add(Week.Saturday.toString());
            arrayAdapter.add(Week.Sunday.toString());
            arrayAdapter.add(Week.Monday.toString());
            arrayAdapter.add(Week.Tuesday.toString());
            arrayAdapter.add(Week.Wednesday.toString());
            arrayAdapter.add(Week.Thursday.toString());
            arrayAdapter.add(Week.Friday.toString());
            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    AlertDialog.Builder builderInnertime = new AlertDialog.Builder(requireContext());
                    builderInnertime.setTitle("Select time:-");
                    builderInnertime.setIcon(R.drawable.baseline_timer_24);

                    final ArrayAdapter<String> arrayAdapterday = new ArrayAdapter<String>(requireContext(), android.R.layout.select_dialog_singlechoice);
                    arrayAdapterday.add(Time.Breakfast.toString());
                    arrayAdapterday.add(Time.Dinner.toString());
                    arrayAdapterday.add(Time.Lunch.toString());

                    builderInnertime.setAdapter(arrayAdapterday, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strNameDay = arrayAdapterday.getItem(which);
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(requireContext());
                            builderInner.setMessage(strName + " at " + strNameDay + " to your plan");
                            builderInner.setTitle("Your add " + meal.getStrMeal() );
                            builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,int which) {
                                    WeekMeals weekMeals = Utils.converter(strName,strNameDay,meal);
                                    repository.insertdaily(weekMeals);
                                    dialog.dismiss();
                                }
                            });
                            builderInner.show();

                        }
                    });
                    builderInnertime.show();
                }
            });
            builderSingle.show();

        });

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(
                getResources().getColor(com.shashank.sony.fancywalkthroughlib.R.color.grey_600));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));

        Glide.with(mealThumb.getContext()).load(meal.getStrMealThumb()).into(mealThumb);

        collapsingToolbarLayout.setTitle(meal.getStrMeal().toString());
        category.setText(meal.getStrCategory());
        country.setText(meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
        nameMeal.setText(meal.getStrMeal());
        getLifecycle().addObserver((LifecycleObserver) videoView);

        if( !meal.getStrYoutube().isEmpty()) {
            String[] split = meal.getStrYoutube().split("=");
            videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    String videoId = split[1];
                    youTubePlayer.loadVideo(videoId, 0);
                }
            });
        }
        if (!meal.getStrIngredient1().isEmpty()&& !meal.getStrMeasure1().isEmpty()) {
            ingredients.add(meal.getStrIngredient1() + " : " + meal.getStrMeasure1());
        }
        if (!meal.getStrIngredient2().isEmpty()&& !meal.getStrMeasure2().isEmpty()) {
            ingredients.add(meal.getStrIngredient2() + " : " + meal.getStrMeasure2());
        }
        if (!meal.getStrIngredient3().isEmpty()&& !meal.getStrMeasure3().isEmpty()) {
            ingredients.add( meal.getStrIngredient3() + " : " + meal.getStrMeasure3());
        }
        if (!meal.getStrIngredient4().isEmpty()&& !meal.getStrMeasure4().isEmpty()) {
            ingredients.add( meal.getStrIngredient4() + " : " + meal.getStrMeasure4());
        }
        if (!meal.getStrIngredient5().isEmpty()&& !meal.getStrMeasure5().isEmpty()) {
            ingredients.add(meal.getStrIngredient5() + " : " + meal.getStrMeasure5());
        }
        if (!meal.getStrIngredient6().isEmpty()&& !meal.getStrMeasure6().isEmpty()) {
            ingredients.add(meal.getStrIngredient6() + " : " + meal.getStrMeasure6());
        }
        if (!meal.getStrIngredient7().isEmpty()&& !meal.getStrMeasure7().isEmpty()) {
            ingredients.add(meal.getStrIngredient7() + " : " + meal.getStrMeasure7());
        }
        if (!meal.getStrIngredient8().isEmpty()&& !meal.getStrMeasure8().isEmpty()) {
            ingredients.add(meal.getStrIngredient8() + " : " + meal.getStrMeasure8());
        }
        if (!meal.getStrIngredient9().isEmpty()&& !meal.getStrMeasure9().isEmpty()) {
            ingredients.add(meal.getStrIngredient9() + " : " + meal.getStrMeasure9());
        }
        if (!meal.getStrIngredient10().isEmpty()&& !meal.getStrMeasure10().isEmpty()) {
            ingredients.add(meal.getStrIngredient10() + " : " + meal.getStrMeasure10());
        }
        if (!meal.getStrIngredient11().isEmpty()&& !meal.getStrMeasure11().isEmpty()) {
            ingredients.add(meal.getStrIngredient11() + " : " + meal.getStrMeasure11());
        }
        if (!meal.getStrIngredient12().isEmpty()&& !meal.getStrMeasure12().isEmpty()) {
            ingredients.add( meal.getStrIngredient12() + " : " + meal.getStrMeasure12());
        }
        if (!meal.getStrIngredient13().isEmpty()&& !meal.getStrMeasure13().isEmpty()) {
            ingredients.add(meal.getStrIngredient13() + " : " + meal.getStrMeasure13());
        }
        if (!meal.getStrIngredient14().isEmpty()&& !meal.getStrMeasure14().isEmpty()) {
            ingredients.add(meal.getStrIngredient14() + " : " + meal.getStrMeasure14());
        }
        if (!meal.getStrIngredient15().isEmpty()&& !meal.getStrMeasure15().isEmpty()) {
            ingredients.add(meal.getStrIngredient15() + " : " + meal.getStrMeasure15());
        }
        if (!meal.getStrIngredient16().isEmpty()&& !meal.getStrMeasure16().isEmpty()) {
            ingredients.add( meal.getStrIngredient16() + " : " + meal.getStrMeasure16());
        }
        if (!meal.getStrIngredient17().isEmpty()&& !meal.getStrMeasure17().isEmpty()) {
            ingredients.add(meal.getStrIngredient17() + " : " + meal.getStrMeasure17());
        }
        if (!meal.getStrIngredient18().isEmpty()&& !meal.getStrMeasure18().isEmpty()) {
            ingredients.add(meal.getStrIngredient18() + " : " + meal.getStrMeasure18());
        }
        if (!meal.getStrIngredient19().isEmpty()&& !meal.getStrMeasure19().isEmpty()) {
            ingredients.add(meal.getStrIngredient19() + " : " + meal.getStrMeasure19());
        }
        if (!meal.getStrIngredient20().isEmpty()&& !meal.getStrMeasure20().isEmpty()) {
            ingredients.add(meal.getStrIngredient20() + " : " + meal.getStrMeasure20());
        }

        IngredientsAdapter ingredientsAdapter =new IngredientsAdapter(requireContext(),ingredients);
        ingredientsRV.setAdapter(ingredientsAdapter);

    }

}