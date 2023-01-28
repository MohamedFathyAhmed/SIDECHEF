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

import java.util.Calendar;

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
    TextView nameMeal;
    TextView BtnAddToPlan;
    LinearLayout btnlinearLayout;
    BottomNavigationView navBar;
    TextView BtnAddToCal;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        ingredients = view.findViewById(R.id.ingredient);
        fav = view.findViewById(R.id.favorite);
        BtnAddToPlan = view.findViewById(R.id.BtnAddToPlan);
        videoView = view.findViewById(R.id.video);
        nameMeal = view.findViewById(R.id.nameMeal);
        btnlinearLayout=view.findViewById(R.id.btnLinearLayout);
        BtnAddToCal =view.findViewById(R.id.BtnAddToCal);
        String email  = YourPreference.getInstance(requireContext()).getData("email");
        if( email.equals("") ){
            btnlinearLayout.setVisibility(View.GONE);
        }else {
            btnlinearLayout.setVisibility(View.VISIBLE);
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

}